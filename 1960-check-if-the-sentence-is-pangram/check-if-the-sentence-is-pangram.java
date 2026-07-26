class Solution {
    public boolean checkIfPangram(String sentence) {
        
        int n = sentence.length();
        HashSet<Character> map = new HashSet<>();
        for(int i=0;i<n;i++){
            char ch = sentence.charAt(i);
            map.add(ch);
        }
        return map.size() ==26;
    }
}