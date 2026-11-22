class Solution {
    public boolean checkString(String s) {
        int n=s.length();
       int first_a=-1;
       int last_a=-1;

       for(int i=0;i<n;i++){
        if(s.charAt(i)=='a'){
            last_a=i;
        }else if(first_a==-1){
                first_a=i;
        }
       }
        if(first_a==-1  || last_a==-1)  return true;

        return last_a<first_a;
}    
}