class Solution {
    public int maxDistinct(String s) {
        HashSet<Character> map = new HashSet<>();
        int n = s.length();
        for(int i=0;i<n;i++){
            map.add(s.charAt(i));
        }
        return map.size();
    }
}