class Solution {
    public boolean isPalindrome(String s) {
       //Then repalceAll non Alphanumeric characters
        String finalStr = s.toLowerCase().replaceAll("[^a-z0-9]", "");
      int n=finalStr.length();
      //loops for 0 to n/2
        for(int i=0;i<n/2;i++){
            char ch=finalStr.charAt(i);
            if(ch !=finalStr.charAt(n-i-1)){
                return false;
            }
    }
    return true;
}
       }
    
