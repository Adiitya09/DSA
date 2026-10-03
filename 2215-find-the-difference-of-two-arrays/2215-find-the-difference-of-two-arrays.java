class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
         Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for(int nums : nums1){
            set1.add(nums);
        }

        for(int nums: nums2){
            set2.add(nums);
        }

         List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for(int num : set1){
            if(!set2.contains(num)){
                list1.add(num);
            }
        }

        for(int num : set2){
            if(!set1.contains(num)){
                list2.add(num);
            }
        }
        
          List<List<Integer>> result = new ArrayList<>();

        result.add(list1);
        result.add(list2);

        return result;
    }
}