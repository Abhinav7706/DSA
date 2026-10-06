class Solution {
    public int minAddToMakeValid(String s) {
        Stack<Character> st=new Stack<>();
        int needOpen=0;

        for(char currentBr:s.toCharArray()){
            if(currentBr=='('){
                st.push(currentBr);
            }else{
                if(!st.isEmpty()){
                    st.pop();
                }else{
                    needOpen++;
                }
            }
        }
        return st.size()+needOpen;
    }
}