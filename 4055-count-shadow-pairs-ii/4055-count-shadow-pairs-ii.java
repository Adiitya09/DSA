class Solution {
    int[] a, sv, lstack, hstack;

    public int shadowPairs(int[] nums) {
        int n = nums.length;
        a = nums;
        long[] keys = new long[n];
        for (int i = 0; i < n; i++)
            keys[i] = ((long) nums[i] << 17) | i;
        Arrays.sort(keys);
        sv = new int[n];
        for (int i = 0; i < n; i++)
            sv[i] = (int) (keys[i] & 131071);
        int[] byIndex = new int[n];
        for (int i = 0; i < n; i++)
            byIndex[i] = i;
        lstack = new int[n];
        hstack = new int[n];
        return (int) solve(byIndex, 0, n);
    }

    private long solve(int[] cur, int low, int high) {
        if (high - low <= 1)
            return 0;
        int mid = (low + high) >>> 1;
        int p1 = mid;
        while (p1 > low && a[sv[p1 - 1]] == a[sv[p1]])
            p1--;
        int p2 = mid;
        while (p2 < high && a[sv[p2 - 1]] == a[sv[p2]])
            p2++;
        boolean ok1 = p1 > low, ok2 = p2 < high;
        int split;
        if (ok1 && ok2)
            split = Math.max(p1 - low, high - p1) <= Math.max(p2 - low, high - p2) ? p1 : p2;
        else if (ok1)
            split = p1;
        else if (ok2)
            split = p2;
        else
            return 0;

        int v = a[sv[split - 1]];
        int[] low0 = new int[split - low], high0 = new int[high - split];
        int x = 0, y = 0;
        for (int e : cur) {
            if (a[e] <= v)
                low0[x++] = e;
            else
                high0[y++] = e;
        }
        long res = count(low0, high0);
        res += solve(low0, low, split);
        res += solve(high0, split, high);
        return res;
    }

    private long count(int[] low, int[] high) {
        int ltop = 0, htop = 0, p = 0;
        long res = 0;
        for (int q = 0; q < high.length; q++) {
            int j = high[q];
            while (p < low.length && low[p] < j) {
                int idx = low[p++];
                while (ltop > 0 && a[lstack[ltop - 1]] < a[idx])
                    ltop--;
                lstack[ltop++] = idx;
            }
            while (htop > 0 && a[hstack[htop - 1]] >= a[j])
                htop--;
            int prev = htop > 0 ? hstack[htop - 1] : -1;
            hstack[htop++] = j;
            int s = 0, e = ltop;
            while (s < e) {
                int m = (s + e) >>> 1;
                if (lstack[m] <= prev)
                    s = m + 1;
                else
                    e = m;
            }

            res += ltop - s;

        }
        return res;
    }
}