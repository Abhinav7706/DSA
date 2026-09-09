class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        PriorityQueue<Integer> heap = new PriorityQueue<>(
            (a, b) -> {
                int da = Math.abs(a - x);
                int db = Math.abs(b - x);

                if (da != db) {
                    return db - da;
                }

                return b - a;
            }
        );

        for (int t : arr) {
            heap.add(t);

            if (heap.size() > k) {
                heap.poll();
            }
        }

        List<Integer> ans = new ArrayList<>(heap);
        Collections.sort(ans);

        return ans;
    }
}