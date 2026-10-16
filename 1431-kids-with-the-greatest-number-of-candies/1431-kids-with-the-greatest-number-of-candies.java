class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        
        ArrayList<Boolean> list = new ArrayList<>();
        int n = candies.length;
        int max =Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            max = Math.max(max,candies[i]);
        }
        for(int i=0;i<n;i++){
            int sum = candies[i] + extraCandies;
            if(max<= sum)  list.add(true);
            else list.add(false);
        }
        return list;
    }
}