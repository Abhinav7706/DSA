
class Solution {

    int[][] up;
    long[][] cost;
    long[] prefix;
    int[] nums;
    int LOG;
    int n;

    // Calculate minimum operations for subarray [l, r]
    long getCost(int l, int r) {

        long total = 0;
        int cur = l;

        // Skip complete segments using binary lifting
        for (int j = LOG - 1; j >= 0; j--) {

            int next = up[j][cur];

            if (next <= r) {
                total += cost[j][cur];
                cur = next;
            }
        }

        // Handle the remaining partial segment
        if (cur < r) {
            total += (long) nums[cur] * (r - cur)
                   - (prefix[r + 1] - prefix[cur + 1]);
        }

        return total;
    }

    public long countNonDecreasingSubarrays(int[] arr, int k) {

        nums = arr;
        n = nums.length;

        // Prefix sum
        prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // Binary lifting levels
        LOG = 1;

        while ((1 << LOG) <= n) {
            LOG++;
        }

        up = new int[LOG][n + 1];
        cost = new long[LOG][n + 1];

        // Find next strictly greater element
        int[] stack = new int[n];
        int top = -1;

        int[] nextGreater = new int[n];

        for (int i = n - 1; i >= 0; i--) {

            while (top >= 0 && nums[stack[top]] <= nums[i]) {
                top--;
            }

            nextGreater[i] = (top == -1) ? n : stack[top];

            stack[++top] = i;
        }

        // Build first level of binary lifting
        for (int i = 0; i < n; i++) {

            int next = nextGreater[i];

            up[0][i] = next;

            cost[0][i] =
                (long) nums[i] * (next - i - 1)
                - (prefix[next] - prefix[i + 1]);
        }

        // Build remaining binary lifting levels
        for (int j = 1; j < LOG; j++) {

            for (int i = 0; i < n; i++) {

                int mid = up[j - 1][i];

                if (mid < n) {

                    up[j][i] = up[j - 1][mid];

                    cost[j][i] =
                        cost[j - 1][i] + cost[j - 1][mid];

                } else {

                    up[j][i] = n;
                }
            }
        }

        // Binary search for each right endpoint
        long answer = 0;

        for (int r = 0; r < n; r++) {

            int left = 0;
            int right = r;

            while (left < right) {

                int mid = left + (right - left) / 2;

                if (getCost(mid, r) <= (long) k) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            answer += r - left + 1;
        }

        return answer;
    }
}
