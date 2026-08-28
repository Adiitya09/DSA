class Solution {
    public int calPoints(String[] operations) {
        int n = operations.length;

        Stack<Integer> stk = new Stack<>();

        for(int i=0;i<n;i++){
            String ch = operations[i];

            if(ch.equals("C")){
                stk.pop();
            }
            else if( ch.equals("D")){
                int x = stk.peek();
                stk.push(2 * x);
            }
            else if(ch.equals("+")){
                int a = stk.pop();
                int b = stk.peek();
                stk.push(a);
                stk.push(a+b);
            }
            else{
                int m = Integer.parseInt(operations[i]);
                stk.push(m);
            }
        }
        int sum=0;
        for(int num:stk){
                sum+=num;
        }
        return sum;
    }
}