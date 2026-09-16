import java.util.*;

class Solution {

    static List<Long>[] palindromes = new ArrayList[2];

    static {
        palindromes[0] = new ArrayList<>();
        palindromes[1] = new ArrayList<>();

        for (int i = 1; i <= 100000; i++) {

            String s = String.valueOf(i);

            String rev = new StringBuilder(s).reverse().toString();
            long evenPal = Long.parseLong(s + rev);

            palindromes[(int) (evenPal % 2)].add(evenPal);

            String half = s.substring(0, s.length() - 1);
            String revHalf = new StringBuilder(half)
                    .reverse()
                    .toString();

            long oddPal = Long.parseLong(s + revHalf);

            palindromes[(int) (oddPal % 2)].add(oddPal);
        }

        palindromes[0].sort(Long::compare);
        palindromes[1].sort(Long::compare);
    }

    public long minOperations(int[] nums) {

        long ans = 0;

        for (int x : nums) {

            List<Long> list = palindromes[x % 2];

            int left = 0;
            int right = list.size();

            while (left < right) {
                int mid = (left + right) >>> 1;

                if (list.get(mid) < x) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            long minDiff = Long.MAX_VALUE;

            if (left < list.size()) {
                minDiff = list.get(left) - x;
            }

            if (left > 0) {
                minDiff = Math.min(
                    minDiff,
                    (long) x - list.get(left - 1)
                );
            }

            ans += minDiff / 2;
        }

        return ans;
    }
}