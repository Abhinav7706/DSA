class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        // First and last occurrence
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Find valid intervals
        for (int c = 0; c < 26; c++) {

            if (last[c] == -1) {
                continue;
            }

            int left = first[c];
            int right = last[c];

            boolean valid = true;

            for (int i = left; i <= right; i++) {

                int x = s.charAt(i) - 'a';

                // This character occurs before our interval
                if (first[x] < left) {
                    valid = false;
                    break;
                }

                right = Math.max(right, last[x]);
            }

            if (valid) {
                intervals.add(new int[]{left, right});
            }
        }

        // Earliest ending interval first
        intervals.sort((a, b) -> a[1] - b[1]);

        List<String> ans = new ArrayList<>();

        int prevEnd = -1;

        for (int[] interval : intervals) {

            if (interval[0] > prevEnd) {

                ans.add(
                    s.substring(interval[0], interval[1] + 1)
                );

                prevEnd = interval[1];
            }
        }

        return ans;
    }
}