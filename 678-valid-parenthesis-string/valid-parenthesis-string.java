class Solution {
    public boolean checkValidString(String s) {
        Stack<Integer> openBraket=new Stack<>();
        Stack<Integer> estrix=new Stack<>();
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch=='('){
                openBraket.push(i);
            }else if(ch=='*'){
                estrix.push(i);
            }else{
                if(!openBraket.isEmpty()){
                    openBraket.pop();
                }else if(!estrix.isEmpty()){
                    estrix.pop();
                }else{
                    return false;
                }
            }
        }
        while(!openBraket.isEmpty()){
            if(estrix.isEmpty()){
                return false;
            }
            int openIdx=openBraket.pop();
            int closeIdx=estrix.pop();
            if(openIdx>closeIdx){
                return false;
            }
        }
        return openBraket.isEmpty();
    }
}