class Solution {
    public int minSwaps(String s) {

        int balance = 0;
        int maxImbalance = 0;

        for (char c : s.toCharArray()) {

            if (c == '[') {
                balance++;
            } else {
                balance--;
            }

            maxImbalance =
                Math.max(maxImbalance, -balance);
        }

        return (maxImbalance + 1) / 2;
    }
}