class Solution {
    public String reverseWords(String s) {
    //String str = s.trim();

    String [] sr = s.trim().split("\\s+");

    StringBuilder sb = new StringBuilder();

    for(int i=sr.length-1;i>=0;i--){
        sb.append(sr[i]);
        if(i>0){
            sb.append(" ");
        }
    }
  
    String result = sb.toString();
    return result;
    }
}