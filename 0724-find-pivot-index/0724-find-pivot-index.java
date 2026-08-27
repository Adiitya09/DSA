class Solution {
    public int pivotIndex(int[] nums) {
        int n = nums.length;

        // for(int i=0;i<n;i++){
        //     int sum =0;
        //     int sum2=0;
        //     for(int j=0;j<i;j++){
        //          sum+=nums[j];
        //     }
        //     for(int k=i+1;k<n;k++){
        //         sum2+=nums[k];
        //     }
        //     if(sum == sum2) return i;
            int total =0;
        for(int x: nums){
            total += x;
        }
        int leftsum =0;
        for(int i=0;i<n;i++){
             int rightsum = total - leftsum - nums[i];
             if( leftsum == rightsum)  return i;
             leftsum+=nums[i];
        }    
        return -1;
    }
}