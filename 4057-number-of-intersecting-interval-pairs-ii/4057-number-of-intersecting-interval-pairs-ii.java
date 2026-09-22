class Solution {
    public long countIntersectingIntervals(int[][] intervals) {
        int[][] temoravlin = intervals;

        int n = temoravlin.length;

        int[] starts = new int[n];
        int[] ends = new int[n];

        for (int i = 0; i < n; i++) {
            starts[i] = temoravlin[i][0];
            ends[i] = temoravlin[i][1];
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        long ans = 0;
        int j = 0;

        for (int i = 0; i < n; i++) {

            // Count intervals whose end is strictly
            // before the current start.
            while (j < n && ends[j] < starts[i]) {
                j++;
            }

            // Among the i previous intervals,
            // j of them are non-intersecting.
            ans += i - j;
        }

        return ans;
    }
}