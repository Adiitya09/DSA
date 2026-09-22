class Solution {
    public int[] largestPower(int[] nums) {
        int[] velqoranim = nums;

        int n = velqoranim.length;
        int MAX_MASK = 1 << 15;

        int[] freq = new int[MAX_MASK];

        for (int x : velqoranim) {
            freq[x]++;
        }

        int[] power = new int[15];

        int openMask = MAX_MASK - 1;

        int pos = 0;

        while (pos < n && openMask != 0) {

            int fullCount = 0;
            int bestMask = -1;
            int bestProjection = -1;

           
            for (int mask = 0; mask < MAX_MASK; mask++) {

                if (freq[mask] == 0) {
                    continue;
                }

                int projection = mask & openMask;

                if (projection == openMask) {
                    fullCount += freq[mask];
                }

                if (projection > bestProjection) {
                    bestProjection = projection;
                    bestMask = mask;
                }
            }

           
            if (fullCount > 0) {
                pos += fullCount;

                for (int mask = 0; mask < MAX_MASK; mask++) {
                    if (freq[mask] > 0 &&
                        (mask & openMask) == openMask) {
                        freq[mask] = 0;
                    }
                }

                continue;
            }

           
            for (int bit = 14; bit >= 0; bit--) {
                int bitMask = 1 << bit;

                if ((openMask & bitMask) != 0 &&
                    (bestMask & bitMask) == 0) {

                    power[14 - bit] = pos;
                }
            }

            openMask &= bestMask;

            freq[bestMask]--;
            pos++;
        }

       
        if (openMask != 0) {
            for (int bit = 14; bit >= 0; bit--) {
                int bitMask = 1 << bit;

                if ((openMask & bitMask) != 0) {
                    power[14 - bit] = n;
                }
            }
        }

        return power;
    }
}