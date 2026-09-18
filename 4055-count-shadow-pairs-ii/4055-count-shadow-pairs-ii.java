import java.util.Arrays;

class Solution {

    static final int INF = 1000000007;
    static final int NEG = -1;

    int[] mx, second, mn, cntMx, active, lazy;

    void build(int node, int l, int r) {
        mx[node] = NEG;
        second[node] = NEG;
        mn[node] = INF;
        cntMx[node] = 0;
        active[node] = 0;
        lazy[node] = INF;

        if (l == r) {
            return;
        }

        int mid = (l + r) >>> 1;

        build(node << 1, l, mid);
        build(node << 1 | 1, mid + 1, r);
    }

    void applyChmin(int node, int x) {
        if (mx[node] <= x) {
            return;
        }

        mn[node] = Math.min(mn[node], x);
        mx[node] = x;
        lazy[node] = Math.min(lazy[node], x);
    }

    void push(int node) {
        if (lazy[node] != INF) {
            applyChmin(node << 1, lazy[node]);
            applyChmin(node << 1 | 1, lazy[node]);
            lazy[node] = INF;
        }
    }

    void pull(int node) {
        int left = node << 1;
        int right = left | 1;

        mn[node] = Math.min(mn[left], mn[right]);
        active[node] = active[left] + active[right];

        if (mx[left] > mx[right]) {

            mx[node] = mx[left];
            cntMx[node] = cntMx[left];

            second[node] = Math.max(second[left], mx[right]);

        } else if (mx[left] < mx[right]) {

            mx[node] = mx[right];
            cntMx[node] = cntMx[right];

            second[node] = Math.max(mx[left], second[right]);

        } else {

            mx[node] = mx[left];
            cntMx[node] = cntMx[left] + cntMx[right];

            second[node] = Math.max(second[left], second[right]);
        }
    }

    void insert(int node, int l, int r, int pos) {

        if (l == r) {

            mx[node] = INF;
            second[node] = NEG;
            mn[node] = INF;

            cntMx[node] = 1;
            active[node] = 1;
            lazy[node] = INF;

            return;
        }

        push(node);

        int mid = (l + r) >>> 1;

        if (pos <= mid) {
            insert(node << 1, l, mid, pos);
        } else {
            insert(node << 1 | 1, mid + 1, r, pos);
        }

        pull(node);
    }

    void rangeChmin(
            int node,
            int l,
            int r,
            int ql,
            int qr,
            int x) {

        if (ql > r ||
            qr < l ||
            active[node] == 0 ||
            mx[node] <= x) {

            return;
        }

        if (ql <= l &&
            r <= qr &&
            second[node] < x) {

            applyChmin(node, x);
            return;
        }

        push(node);

        int mid = (l + r) >>> 1;

        rangeChmin(
                node << 1,
                l,
                mid,
                ql,
                qr,
                x);

        rangeChmin(
                node << 1 | 1,
                mid + 1,
                r,
                ql,
                qr,
                x);

        pull(node);
    }

    int query(
            int node,
            int l,
            int r,
            int ql,
            int qr,
            int x) {

        if (ql > r ||
            qr < l ||
            active[node] == 0 ||
            mx[node] < x) {

            return 0;
        }

        if (ql <= l &&
            r <= qr &&
            mn[node] >= x) {

            return active[node];
        }

        if (l == r) {
            return mx[node] >= x ? active[node] : 0;
        }

        push(node);

        int mid = (l + r) >>> 1;

        return query(
                    node << 1,
                    l,
                    mid,
                    ql,
                    qr,
                    x)
             +
               query(
                    node << 1 | 1,
                    mid + 1,
                    r,
                    ql,
                    qr,
                    x);
    }

    public int shadowPairs(int[] nums) {

        int n = nums.length;

        /*
         * Store:
         * upper 32 bits = value
         * lower 32 bits = original index
         */
        long[] order = new long[n];

        for (int i = 0; i < n; i++) {
            order[i] =
                    ((long) nums[i] << 32)
                    | (i & 0xffffffffL);
        }

        Arrays.sort(order);

        /*
         * rank[i] = position of index i
         * in sorted order
         */
        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            int originalIndex = (int) order[i];
            rank[originalIndex] = i;
        }

        mx = new int[4 * n];
        second = new int[4 * n];
        mn = new int[4 * n];
        cntMx = new int[4 * n];
        active = new int[4 * n];
        lazy = new int[4 * n];

        build(1, 0, n - 1);

        int res = 0;

        for (int j = 0; j < n; j++) {

            int b = nums[j];

            /*
             * Find all previous values < b.
             */
            int right = lowerBound(order, b) - 1;

            if (right >= 0) {

                res += query(
                        1,
                        0,
                        n - 1,
                        0,
                        right,
                        b);
            }

            /*
             * Add current index.
             */
            insert(
                    1,
                    0,
                    n - 1,
                    rank[j]);

            /*
             * Update previous smaller values.
             */
            int left = lowerBound(order, nums[j]) - 1;

            if (left >= 0) {

                rangeChmin(
                        1,
                        0,
                        n - 1,
                        0,
                        left,
                        nums[j]);
            }
        }

        return res;
    }

    int lowerBound(long[] order, int target) {

        int left = 0;
        int right = order.length;

        while (left < right) {

            int mid =
                    left + (right - left) / 2;

            int val =
                    (int) (order[mid] >>> 32);

            if (val < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}