class Solution {

    boolean isSafe(List<String> board, int row, int col, int n) {

        // Column
        for (int i = 0; i < row; i++) {
            if (board.get(i).charAt(col) == 'Q') {
                return false;
            }
        }

        // Upper-left diagonal
        for (int i = row - 1, j = col - 1;
             i >= 0 && j >= 0;
             i--, j--) {

            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }

        // Upper-right diagonal
        for (int i = row - 1, j = col + 1;
             i >= 0 && j < n;
             i--, j++) {

            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }

        return true;
    }

    void nQueens(List<String> board, int row,
                 int n, List<List<String>> ans) {

        if (row == n) {
            ans.add(new ArrayList<>(board));
            return;
        }

        for (int col = 0; col < n; col++) {

            if (isSafe(board, row, col, n)) {

                char[] chars = board.get(row).toCharArray();

                // Place
                chars[col] = 'Q';
                board.set(row, new String(chars));

                // Next row
                nQueens(board, row + 1, n, ans);

                // Backtrack
                chars[col] = '.';
                board.set(row, new String(chars));
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {

        List<List<String>> ans = new ArrayList<>();
        List<String> board = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            board.add(".".repeat(n));
        }

        nQueens(board, 0, n, ans);

        return ans;
    }
}