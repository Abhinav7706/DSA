import java.util.*;

class Solution {

    class Fenwick {
        int n;
        int[] count;
        long[] sum;

        Fenwick(int n) {
            this.n = n;
            count = new int[n + 1];
            sum = new long[n + 1];
        }

        void add(int index, int c, long value) {
            for (int i = index + 1; i <= n; i += i & -i) {
                count[i] += c;
                sum[i] += value;
            }
        }

        int queryCount(int index) {
            int result = 0;
            for (int i = index; i > 0; i -= i & -i) {
                result += count[i];
            }
            return result;
        }

        long querySum(int index) {
            long result = 0;
            for (int i = index; i > 0; i -= i & -i) {
                result += sum[i];
            }
            return result;
        }

        int kth(int k) {
            int index = 0;

            for (int bit = Integer.highestOneBit(n);
                 bit > 0; bit >>= 1) {

                int next = index + bit;

                if (next <= n && count[next] < k) {
                    index = next;
                    k -= count[next];
                }
            }

            return index;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        // Step 1: Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int[] values = new int[n];
        int size = 0;

        for (int num : sorted) {
            if (size == 0 || values[size - 1] != num) {
                values[size++] = num;
            }
        }

        // Step 2: Fenwick Tree
        Fenwick bit = new Fenwick(size);

        // Step 3: Calculate cost of every size-x window
        int windows = n - x + 1;
        long[] cost = new long[windows];

        for (int i = 0; i < x; i++) {
            int index = Arrays.binarySearch(values, 0, size, nums[i]);
            bit.add(index, 1, nums[i]);
        }

        for (int i = 0; i < windows; i++) {

            // Lower median
            int medianIndex = bit.kth((x + 1) / 2);
            long median = values[medianIndex];

            int leftCount = bit.queryCount(medianIndex + 1);
            long leftSum = bit.querySum(medianIndex + 1);

            long totalSum = bit.querySum(size);
            int rightCount = x - leftCount;
            long rightSum = totalSum - leftSum;

            cost[i] =
                median * leftCount - leftSum
                + rightSum - median * rightCount;

            // Slide window
            if (i < windows - 1) {
                int removeIndex =
                    Arrays.binarySearch(values, 0, size, nums[i]);

                int addIndex =
                    Arrays.binarySearch(values, 0, size, nums[i + x]);

                bit.add(removeIndex, -1, -nums[i]);
                bit.add(addIndex, 1, nums[i + x]);
            }
        }

        // Step 4: Dynamic Programming
        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {

                // Option 1: Skip current element
                curr[i] = curr[i - 1];

                // Option 2: Select a window ending at i-1
                if (i >= x && prev[i - x] != INF) {
                    curr[i] = Math.min(
                        curr[i],
                        prev[i - x] + cost[i - x]
                    );
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }
}