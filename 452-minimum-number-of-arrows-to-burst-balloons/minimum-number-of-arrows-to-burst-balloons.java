class Solution {
    public int findMinArrowShots(int[][] points) {
        int n=points.length;
        if(n==1) return 1;

        Arrays.sort(points,new Comparator<int[]>(){
            public int compare(int a1[], int a2[]){
                if(a1[0]==a2[0]) return 0;
                if(a1[0]<a2[0]) return -1;
                return 1;
            }
        });

        int arrows=1;
        int commonInterval[]=points[0];
        for(int i=1;i<n;i++){
            int currS=points[i][0];
            int currE=points[i][1];

            if(currS<=commonInterval[1]){
                commonInterval[0]=Math.max(commonInterval[0],currS);
                commonInterval[1]=Math.min(commonInterval[1],currE);
            }else{
                commonInterval[0]=currS;
                commonInterval[1]=currE;
                arrows++;
            }
        }
        return arrows;
    }
}