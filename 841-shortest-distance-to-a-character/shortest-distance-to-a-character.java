class Solution {
    public int[] shortestToChar(String s, char c) {
        List<Integer> indexes=new ArrayList<>();
        List<Integer> sol=new ArrayList<>();

        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==c){
                indexes.add(i);
            }
        } 

        for(int i=0;i<s.length();i++){
            int min_dist=10000;
            for(int j:indexes){
                min_dist=Math.min(min_dist,Math.abs(j-i));
            }
            sol.add(min_dist);
        }
        int[] result=new int[sol.size()];
        for(int i=0;i<sol.size();i++){
            result[i]=sol.get(i);
        }
        return result;
    }
}