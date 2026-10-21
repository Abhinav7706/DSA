class Solution {
    public int findDuplicate(int[] nums) {
        HashSet<Integer> s=new HashSet<>();
        int n=nums.length;
        for(int i=0;i<n;i++){
            if(s.contains(nums[i])){
                return nums[i];
            }else{
                s.add(nums[i]);
            }
        }
        return -1;
    }
}