class Solution {
    public boolean isAnagram(String s, String t) {
        int n = s.length();
        int m = t.length();

        char[] str = s.toCharArray();
        char[] str1 = t.toCharArray();

        if(str.length != str1.length) return false;

        Arrays.sort(str);
        Arrays.sort(str1);

        for(int i=0;i<n;i++){
            if(str[i] != str1[i]) return false;
        }
        return true;

    }
}