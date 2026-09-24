class Solution {
    public int smallestIndex(int[] nums) {
        int n = nums.length;
        
        for(int i=0;i<n;i++){
            int sum=0;
            int v= nums[i];
            while(v>0){
                int rem=v%10;
                v = v/10;
                sum+=rem;
            }
            if(i==sum) return i;
        }
        return -1;
    }
}