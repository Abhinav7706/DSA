
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        // Count frequency of each factor
        int[] freq = new int[1501];

        for (int[] item : items) {
            freq[item[0]]++;
        }

        // Calculate weight = purchased item + free items
        int[] weight = new int[n];

        for (int i = 0; i < n; i++) {
            int factor = items[i][0];

            int free = 0;

            for (int j = factor; j <= 1500; j += factor) {
                free += freq[j];
            }

            // Exclude the item itself
            free--;

            weight[i] = 1 + free;
        }

        // Group items by price
        List<Integer>[] groups = new ArrayList[1501];

        for (int i = 1; i <= 1500; i++) {
            groups[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            groups[items[i][1]].add(i);
        }

        // dp[c] = maximum weight using exact cost c
        int NEG = -1000000000;

        int[] dp = new int[budget + 1];
        Arrays.fill(dp, NEG);
        dp[0] = 0;

        int ans = 0;

        // Minimum selected item price
        for (int price = 1500; price >= 1; price--) {

            if (groups[price].isEmpty()) {
                continue;
            }

            // Items with strictly higher prices
            int[] base = dp.clone();

            // Must select at least one item of current price
            int[] cur = new int[budget + 1];
            Arrays.fill(cur, NEG);

            for (int idx : groups[price]) {
                int w = weight[idx];

                for (int c = budget; c >= price; c--) {

                    // Select an item of current price
                    if (base[c - price] != NEG) {
                        cur[c] = Math.max(
                            cur[c],
                            base[c - price] + w
                        );
                    }

                    // Select another item of current price
                    if (cur[c - price] != NEG) {
                        cur[c] = Math.max(
                            cur[c],
                            cur[c - price] + w
                        );
                    }

                    // Normal 0/1 knapsack update
                    if (dp[c - price] != NEG) {
                        dp[c] = Math.max(
                            dp[c],
                            dp[c - price] + w
                        );
                    }
                }
            }

            // Calculate purchased + free + extra copies
            for (int c = 0; c <= budget; c++) {
                if (cur[c] != NEG) {
                    int total = cur[c] + (budget - c) / price;
                    ans = Math.max(ans, total);
                }
            }
        }

        return ans;
    }
}
