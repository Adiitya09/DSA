class Solution {
    public int reverseDegree(String s) {
        
        int n = s.length();
        int idx =1;
        int sum =0;

        for(int i=0;i<n;i++){
            int ch = s.charAt(i);
            int rev = idx*(123-ch);
            sum+=rev;
            idx++;  
        }

        return sum;
    }
}