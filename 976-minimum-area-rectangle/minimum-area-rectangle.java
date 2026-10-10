class Solution {
    public int minAreaRect(int[][] points) {
        Map<Integer,Set<Integer>> mp=new HashMap<>();
        for(int[] p:points){
            if(!mp.containsKey(p[0])){
                mp.put(p[0],new HashSet<>());
            }
            mp.get(p[0]).add(p[1]);
        }
        int n=points.length;
        int a=Integer.MAX_VALUE;
        boolean b=false;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(points[i][0]==points[j][0] || points[i][1]==points[j][1]) continue;
                if(mp.get(points[i][0]).contains(points[j][1])&& mp.get(points[j][0]).contains(points[i][1])){
                    b=true;
                    a=Math.min(a,Math.abs(points[i][0]-points[j][0])*Math.abs(points[i][1]-points[j][1]));
                }
            }
        }
        if(b) return a;
        else return 0;
    }
}