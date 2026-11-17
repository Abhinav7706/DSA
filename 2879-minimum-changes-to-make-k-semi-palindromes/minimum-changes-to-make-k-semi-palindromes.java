class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] cost = new int[n][n];

        // Step 1: Calculate cost of every substring
        for (int len = 2; len <= n; len++) {

            for (int l = 0; l + len <= n; l++) {
                int r = l + len - 1;
                cost[l][r] = Integer.MAX_VALUE;

                // Try every proper divisor
                for (int d = 1; d < len; d++) {

                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

                    // Check each group
                    for (int start = 0; start < d; start++) {
                        int left = l + start;
                        int right = l + start
                                + ((len - 1 - start) / d) * d;

                        while (left < right) {
                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    cost[l][r] = Math.min(cost[l][r], changes);
                }
            }
        }

        // Step 2: DP
        int INF = 1000000000;
        int[][] dp = new int[k + 1][n + 1];

        for (int i = 0; i <= k; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = INF;
            }
        }

        dp[0][0] = 0;

        // Step 3: Partition into exactly k substrings
        for (int p = 1; p <= k; p++) {

            for (int i = 2 * p; i <= n; i++) {

                for (int j = 2 * (p - 1); j <= i - 2; j++) {

                    if (dp[p - 1][j] == INF) {
                        continue;
                    }

                    dp[p][i] = Math.min(
                        dp[p][i],
                        dp[p - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }
}