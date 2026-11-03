class Solution {
    public int minMoves(int[] nums) {
        int max=0;
        for(int num:nums){
            max=Math.max(num,max);
        }
        int moves=0;
        for(int num:nums){
            moves+=(max-num);
        }
        return moves;
    }
}