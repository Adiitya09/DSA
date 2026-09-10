class Solution {
    public int totalWaviness(int num1, int num2) {
        int count = 0;

        for (int i = num1; i <= num2; i++) {
            String s = String.valueOf(i);

            for (int j = 1; j < s.length() - 1; j++) {
                int prev = s.charAt(j - 1) - '0';
                int curr = s.charAt(j) - '0';
                int next = s.charAt(j + 1) - '0';

                if ((prev < curr && curr > next) ||
                    (prev > curr && curr < next)) {
                    count++;
                }
            }
        }

        return count;
    }
}