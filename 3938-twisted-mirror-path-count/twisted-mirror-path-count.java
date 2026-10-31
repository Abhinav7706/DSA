
class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        long[][] dp = new long[m][n];

        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (dp[i][j] == 0) continue;

                long ways = dp[i][j];

                // Try moving right
                int x = i;
                int y = j + 1;

                while (x < m && y < n && grid[x][y] == 1) {
                    x++;
                    if (x >= m) break;
                    if (grid[x][y] == 1) {
                        y++;
                    }
                }

                if (x < m && y < n) {
                    dp[x][y] = (dp[x][y] + ways) % MOD;
                }

                // Try moving down
                x = i + 1;
                y = j;

                while (x < m && y < n && grid[x][y] == 1) {
                    y++;
                    if (y >= n) break;
                    if (grid[x][y] == 1) {
                        x++;
                    }
                }

                if (x < m && y < n) {
                    dp[x][y] = (dp[x][y] + ways) % MOD;
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}
