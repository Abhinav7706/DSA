class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {

        HashMap<String, String> mp = new HashMap<>();

        for (List<String> vec : knowledge) {
            mp.put(vec.get(0), vec.get(1));
        }

        StringBuilder result = new StringBuilder();

        int i = 0;

        while (i < s.length()) {

            if (s.charAt(i) == '(') {

                i++;

                StringBuilder temp = new StringBuilder();

                while (i < s.length() && s.charAt(i) != ')') {
                    temp.append(s.charAt(i));
                    i++;
                }

                String key = temp.toString();

                if (mp.containsKey(key)) {
                    result.append(mp.get(key));
                } else {
                    result.append("?");
                }

                i++; // skip ')'

            } else {

                result.append(s.charAt(i));
                i++;
            }
        }

        return result.toString();
    }
}