class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        HashSet<Integer> set = new HashSet<>();

        int n = grid.length;

        int repeated = 0;
        int missing = 0;

        long expectedSum = 0;
        long actualSum = 0;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                actualSum += grid[i][j];

                if (set.contains(grid[i][j])) {
                    repeated = grid[i][j];
                }

                set.add(grid[i][j]);
            }
        }

        expectedSum = (long)(n * n) * (n * n + 1) / 2;

        missing = (int)(expectedSum + repeated - actualSum);

        return new int[]{repeated, missing};
    }
}