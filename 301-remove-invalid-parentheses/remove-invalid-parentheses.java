class Solution {

    Set<String> ans = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {

        int left = 0;
        int right = 0;

        // Extra '(' and ')' count karo
        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                left++;
            } 
            else if (ch == ')') {

                if (left > 0) {
                    left--;
                } 
                else {
                    right++;
                }
            }
        }

        helper(s, 0, left, right, 0, "");

        return new ArrayList<>(ans);
    }

    void helper(String s, int index,
                int leftRemove, int rightRemove,
                int balance, String current) {

        // Invalid
        if (balance < 0) {
            return;
        }

        // String complete
        if (index == s.length()) {

            if (leftRemove == 0 &&
                rightRemove == 0 &&
                balance == 0) {

                ans.add(current);
            }

            return;
        }

        char ch = s.charAt(index);

        // '('
        if (ch == '(') {

            // Remove it
            if (leftRemove > 0) {
                helper(s, index + 1,
                       leftRemove - 1,
                       rightRemove,
                       balance,
                       current);
            }

            // Keep it
            helper(s, index + 1,
                   leftRemove,
                   rightRemove,
                   balance + 1,
                   current + ch);
        }

        // ')'
        else if (ch == ')') {

            // Remove it
            if (rightRemove > 0) {
                helper(s, index + 1,
                       leftRemove,
                       rightRemove - 1,
                       balance,
                       current);
            }

            // Keep it
            if (balance > 0) {
                helper(s, index + 1,
                       leftRemove,
                       rightRemove,
                       balance - 1,
                       current + ch);
            }
        }

        // Normal character
        else {
            helper(s, index + 1,
                   leftRemove,
                   rightRemove,
                   balance,
                   current + ch);
        }
    }
}
