class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack=new Stack<>();
        stack.push(0);

        for(char ch:s.toCharArray()){
            if(ch=='('){
                stack.push(0);
            }else{
                int top=stack.pop();
                int a=Math.max(2*top,1);
                int b=stack.pop();
                stack.push(a+b);

            }
        }
        return stack.pop();
    }
}