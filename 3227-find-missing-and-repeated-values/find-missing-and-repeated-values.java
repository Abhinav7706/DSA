class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        HashSet<Integer> s=new HashSet<>();
        int n=grid.length;
        int repeated=-1;
        int sum=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int num=grid[i][j];
                sum+=num;

                if(s.contains(num)){
                    repeated=num;

                }else{
                    s.add(num);
                }
            }
        }

            int total=n*n;
            int expectedSum=total*(total+1)/2;
            int missing=expectedSum-(sum-repeated);

            return new int[]{repeated,missing};
        
    }
}