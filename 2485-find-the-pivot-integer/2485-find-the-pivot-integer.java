class Solution {
    public int pivotInteger(int n) {
        
         if( n==1)  return n;

        int sum =0;
        for(int i=0;i<=n;i++){
            sum+=i;
        }
        int left=0;
       
        for(int i=0;i<=n;i++){
            left+=i;
            int right= sum-left+i;
            if(right == left) return i;
        }
        return -1;
    }
}