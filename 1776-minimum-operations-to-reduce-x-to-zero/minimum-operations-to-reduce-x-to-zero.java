class Solution {
    public int minOperations(int[] nums, int x) {

        int n = nums.length;

        HashMap<Integer, Integer> mp = new HashMap<>();

        int total = 0;

        for (int num : nums) {
            total += num;
        }

        int remainingSum = total - x;

        // Special case
        if (remainingSum == 0) {
            return n;
        }

        mp.put(0, -1);

        int sum = 0;
        int longestSubArray = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {

            sum += nums[i];

            int findSum = sum - remainingSum;

            if (mp.containsKey(findSum)) {

                int idx = mp.get(findSum);

                longestSubArray =
                    Math.max(longestSubArray, i - idx);
            }

            // Store first occurrence only
            if (!mp.containsKey(sum)) {
                mp.put(sum, i);
            }
        }

        if (longestSubArray == Integer.MIN_VALUE) {
            return -1;
        }

        return n - longestSubArray;
    }
}