class Solution {
    public int superPow(int a, int[] b) {
         int result = 1;
        a = a % 1337;

        for (int digit : b) {

            result = power(result, 10);

            for (int i = 0; i < digit; i++) {
                result = (result * a) % 1337;
            }
        }

        return result;
    }

    private int power(int a, int n) {

        int result = 1;

        for (int i = 0; i < n; i++) {
            result = (result * a) % 1337;
        }

        return result;
    }
}