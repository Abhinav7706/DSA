class Solution {
    public boolean hasValidPath(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // Path length must be even
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // First character must be '('
        if (grid[0][0] == ')') {
            return false;
        }

        Queue<int[]> queue = new LinkedList<>();

        // {row, col, balance}
        queue.offer(new int[]{0, 0, 1});

        boolean[][][] visited =
            new boolean[m][n][m + n];

        visited[0][0][1] = true;

        int[][] directions = {
            {1, 0},
            {0, 1}
        };

        while (!queue.isEmpty()) {

            int[] curr = queue.poll();

            int row = curr[0];
            int col = curr[1];
            int balance = curr[2];

            // Balance can never become negative
            if (balance < 0) {
                continue;
            }

            // Reached destination
            if (row == m - 1 && col == n - 1) {

                if (balance == 0) {
                    return true;
                }

                continue;
            }

            for (int[] dir : directions) {

                int nr = row + dir[0];
                int nc = col + dir[1];

                if (nr < 0 || nr >= m ||
                    nc < 0 || nc >= n) {
                    continue;
                }

                int newBalance = balance;

                if (grid[nr][nc] == '(') {
                    newBalance++;
                } else {
                    newBalance--;
                }

                if (newBalance < 0) {
                    continue;
                }

                if (!visited[nr][nc][newBalance]) {

                    visited[nr][nc][newBalance] = true;

                    queue.offer(
                        new int[]{nr, nc, newBalance}
                    );
                }
            }
        }

        return false;
    }
}