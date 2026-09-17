class Solution {

    public long shadowPairs(int[] nums) {
        int n = nums.length;
        int value[] = new int [n];
        int count[] = new int [n];
        int top = -1;
        long ans = 0;
        int total =0;
        for(int i=0 ; i<n ; i++){
            int temp = nums[i];
            while(top >=0 && temp < value[top]){
                total -= count[top];
                top--;
            }
            if (top>=0){
                if(value[top] < temp){
                    ans += total;
                    top++;
                    value[top] = temp;
                    count[top] = 1;
                }
                else{ // case temp == value[top]
                    ans += total - count[top];
                    count[top]++;
                }
            }
            else { // top < 0 (== -1)
                top++;
                value[top] = temp;
                count[top] = 1;
            }
            total++;
        }
        return ans;
    }
}