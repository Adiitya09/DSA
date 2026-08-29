class Solution {
    public boolean lemonadeChange(int[] bills) {
        int n = bills.length;
        int [] change =new int[3];
        for(int i=0;i<n;i++){
            if(bills[i] == 5){
                change[0] += 1;
            }
            else if(bills[i] == 10){
                 if (change[0] == 0) return false;
                change[0] -= 1;
                change[1] += 1;
                
            }
            else {
                 if (change[1] >= 1 && change[0] >= 1) {
                    change[1] -= 1;
                    change[0] -= 1;
                    change[2] += 1;
                } else if (change[0] >= 3) {
                    change[0] -= 3;
                    change[2] += 1;
                } else {
                    return false;
            }
        }
        }
        return true;
    }
}
