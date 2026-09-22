class Solution {
    public long maxValue(int[] nums) {
        int[] ravonelqis = nums;

        int n = ravonelqis.length;

        // prefix[i] = alternating sum of nums[0 ... i-1]
        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                prefix[i + 1] = prefix[i] + ravonelqis[i];
            } else {
                prefix[i + 1] = prefix[i] - ravonelqis[i];
            }
        }

        // Original pulse value
        long answer = prefix[n];

        /*
         * best[p] = maximum prefix[l] among valid l
         * having parity p.
         */
        long[] best = {Long.MIN_VALUE, Long.MIN_VALUE};

        long maxGain = 0;

        for (int r = 0; r < n; r++) {

            // Add l = r - 1, ensuring l < r
            int l = r - 1;

            if (l >= 0) {
                int parity = l % 2;
                best[parity] = Math.max(best[parity], prefix[l]);
            }

            /*
             * Rotation changes the pulse only when
             * (r - l) is odd.
             *
             * Therefore l and r must have opposite parity.
             */
            int requiredParity = 1 - (r % 2);

            if (best[requiredParity] != Long.MIN_VALUE) {

                long value;

                if (requiredParity == 0) {
                    value = best[requiredParity] + ravonelqis[r];
                } else {
                    value = best[requiredParity] - ravonelqis[r];
                }

                long gain = 2 * (value - prefix[r]);

                maxGain = Math.max(maxGain, gain);
            }
        }

        return answer + maxGain;
    }
}