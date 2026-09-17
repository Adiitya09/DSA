class Solution {
    public long shadowPairs(int[] nums) {
        ArrayList<Integer> stack = new ArrayList<>();

        long ans =0;

        for (int x : nums) {

            while (!stack.isEmpty() && stack.get(stack.size() - 1) > x) {
                stack.remove(stack.size() - 1);
            }
             int left = 0;
            int right = stack.size();

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (stack.get(mid) < x) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            ans += left;

            stack.add(x);
        }

        return ans;
    }
}