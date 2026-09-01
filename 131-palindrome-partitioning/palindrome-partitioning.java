class Solution {
    boolean isPalin(String s){
         int st = 0;
        int end = s.length() - 1;

        while (st < end) {

            if (s.charAt(st) != s.charAt(end)) {
                return false;
            }

            st++;
            end--;
        }

        return true;
    }
    void getAllParts(String s,List<String>partition,List<List<String>>ans){
         if (s.length() == 0) {
            ans.add(new ArrayList<>(partition));
            return;
        }


        for(int i=0;i<s.length();i++){
            String part=s.substring(0,i+1);

            if(isPalin(part)){
                partition.add(part);
                getAllParts(s.substring(i+1),partition,ans);
                partition.remove(partition.size()-1);
            }
        }
    }
    public List<List<String>> partition(String s) {
        List<List<String>> ans=new ArrayList<>();
        List<String> partition=new ArrayList<>();
        getAllParts(s,partition,ans);
        return ans;
    }
}