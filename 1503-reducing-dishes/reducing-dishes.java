class Solution {
     int dp[][];

    int solve(int[] nums,int i,int time){
        if(i==nums.length) return 0;
        if(dp[i][time] != -1) return dp[i][time];
        int take=nums[i]*time+solve(nums,i+1,time+1);
        int not_take=solve(nums,i+1,time);
        return dp[i][time]=Math.max(take,not_take);
    }

    public int maxSatisfaction(int[] nums) {
       Arrays.sort(nums);
       dp=new int[nums.length+1][501];
       for(int i=0;i<=nums.length;i++){
        Arrays.fill(dp[i],-1);
       }
       return solve(nums,0,1);

    }
}