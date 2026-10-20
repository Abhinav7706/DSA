class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean valid = true;
            int preMax = Integer.MIN_VALUE;

            for (int start = 0; start < n; start += k) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                int decent = 0;

                for (int j = start; j < start + k; j++) {
                    min = Math.min(min, nums[j]);
                    max = Math.max(max, nums[j]);

                    if (j > start && nums[j - 1] > nums[j]) {
                        decent++;
                    }
                }

                if (decent > 1 ||
                    (decent == 1 && nums[start + k - 1] > nums[start]) ||
                    preMax > min) {
                    valid = false;
                    break;
                }

                preMax = max;
            }

            if (valid) {
                ans += k;
            }
        }

        return ans;
    }
}