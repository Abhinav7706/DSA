class Solution {

    public List<String> braceExpansionII(String expression) {

        Set<String> result = solve(expression, 0, expression.length());

        List<String> ans = new ArrayList<>(result);

        Collections.sort(ans);

        return ans;
    }

    public Set<String> solve(String s, int start, int end) {

        Set<String> result = new HashSet<>();

        Set<String> current = new HashSet<>();
        current.add("");

        int i = start;

        while (i < end) {

            char ch = s.charAt(i);

            // Union
            if (ch == ',') {

                result.addAll(current);

                current = new HashSet<>();
                current.add("");

                i++;
            }

            // Braces
            else if (ch == '{') {

                int balance = 1;
                int j = i + 1;

                while (j < end && balance > 0) {

                    if (s.charAt(j) == '{') {
                        balance++;
                    }
                    else if (s.charAt(j) == '}') {
                        balance--;
                    }

                    j++;
                }

                Set<String> inside =
                    solve(s, i + 1, j - 1);

                current = concat(current, inside);

                i = j;
            }

            // Normal character
            else {

                Set<String> temp = new HashSet<>();

                for (String str : current) {
                    temp.add(str + ch);
                }

                current = temp;

                i++;
            }
        }

        result.addAll(current);

        return result;
    }

    // Cartesian product / concatenation
    public Set<String> concat(Set<String> a, Set<String> b) {

        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}