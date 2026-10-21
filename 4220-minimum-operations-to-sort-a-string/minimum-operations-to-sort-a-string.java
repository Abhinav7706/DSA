
class Solution {
    public int minOperations(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();

        boolean sorted = true;
        char min = arr[0];
        char max = arr[0];

        for (int i = 1; i < n; i++) {
            if (arr[i] < arr[i - 1]) {
                sorted = false;
            }

            min = (char) Math.min(min, arr[i]);
            max = (char) Math.max(max, arr[i]);
        }

        if (sorted) return 0;
        if (n == 2) return -1;

        if (arr[0] == min || arr[n - 1] == max) {
            return 1;
        }

        for (int i = 1; i < n - 1; i++) {
            if (arr[i] == min || arr[i] == max) {
                return 2;
            }
        }

        return 3;
    }
}
