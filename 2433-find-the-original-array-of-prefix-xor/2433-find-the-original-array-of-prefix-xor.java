class Solution {
    public int[] findArray(int[] pref) {
        int n = pref.length;

        //int bit = pref[0];

       for (int i = pref.length - 1; i > 0; i--) {
              pref[i] = pref[i] ^ pref[i - 1];
          }

        return pref;
    }
}