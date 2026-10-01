class Solution {
    public int matrixSum(int[][] nums) {
        int ans=0;
        int m=nums.length;
        int n=nums[0].length;

        for(int[] row:nums){
            Arrays.sort(row);
        }
        for(int j=0;j<n;j++){
            int x=0;
            for(int i=0;i<m;i++){
                x=Math.max(x,nums[i][j]);
            }
            ans+=x;
        }
        return ans;
    }
}