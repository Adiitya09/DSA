class Solution {
    public int findGCD(int[] nums) {
        int  n =nums.length;
        int min =Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            min = Math.min(nums[i],min);
            max = Math.max(nums[i],max);
        }

        int c = Math.abs(min-max);

        for(int i =c;i>0;i--){
            if(max % i ==0 && min%i==0){
                return i;
            }
        }
        return nums[0];
    }

}