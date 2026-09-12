import java.util.*;

class Solution {

    static class State {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        // start, end, weight, original index
        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        // Sort by ending point
        Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        // Find previous non-overlapping interval
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {

            int low = 0;
            int high = i - 1;
            int ans = -1;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                if (arr[mid][1] < arr[i][0]) {
                    ans = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            prev[i] = ans;
        }

        /*
         * dp[i][k]
         * First i intervals me se at most k intervals choose karne ka
         * maximum score + indices
         */

        State[][] dp = new State[n + 1][5];

        // IMPORTANT:
        // Har state ko initially empty answer do.
        // Isse koi bhi dp cell null nahi rahega.
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new State(
                    0L,
                    new ArrayList<>()
                );
            }
        }

        for (int i = 1; i <= n; i++) {

            int idx = i - 1;

            for (int k = 1; k <= 4; k++) {

                // -------------------------
                // 1. Don't take interval
                // -------------------------

                State notTake = dp[i - 1][k];

                State best = new State(
                    notTake.score,
                    new ArrayList<>(notTake.indices)
                );

                // -------------------------
                // 2. Take interval
                // -------------------------

                int p = prev[idx];

                State before = dp[p + 1][k - 1];

                long newScore =
                    before.score + (long) arr[idx][2];

                List<Integer> newList =
                    new ArrayList<>(before.indices);

                newList.add(arr[idx][3]);

                // Lexicographically smallest indices
                Collections.sort(newList);

                if (newScore > best.score ||
                    (newScore == best.score &&
                     isLexicographicallySmaller(
                         newList,
                         best.indices
                     ))) {

                    best = new State(newScore, newList);
                }

                dp[i][k] = best;
            }
        }

        List<Integer> result = dp[n][4].indices;

        int[] answer = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    private boolean isLexicographicallySmaller(
            List<Integer> a,
            List<Integer> b) {

        int min = Math.min(a.size(), b.size());

        for (int i = 0; i < min; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }

        return a.size() < b.size();
    }
}