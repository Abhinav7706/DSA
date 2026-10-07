class Solution {
    public int minCut(String s) {

        int n = s.length();

        // pal[i][j] = s[i...j] palindrome hai ya nahi
        boolean[][] pal = new boolean[n][n];

        // Fill palindrome DP
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {

                if (s.charAt(i) == s.charAt(j) &&
                    (j - i <= 1 || pal[i + 1][j - 1])) {

                    pal[i][j] = true;
                }
            }
        }

        // dp[i] = minimum number of partitions
        // for substring s[i...n-1]
        int[] dp = new int[n + 1];

        dp[n] = 0;

        for (int i = n - 1; i >= 0; i--) {

            int mini = Integer.MAX_VALUE;

            for (int k = i; k < n; k++) {

                if (pal[i][k]) {

                    int x = 1 + dp[k + 1];

                    mini = Math.min(mini, x);
                }
            }

            dp[i] = mini;
        }

        return dp[0] - 1;
    }
}