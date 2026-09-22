class Solution {

    int k;
    long[][] tree;
    int[] product;

    class Node {
        long[] cnt;
        int prod;

        Node() {
            cnt = new long[k];
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.k = k;

        int n = nums.length;

        tree = new long[4 * n][k];
        product = new int[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Update
            update(1, 0, n - 1, index, value);

            // Query [start, n - 1]
            Node res = query(1, 0, n - 1, start, n - 1);

            ans[i] = (int) res.cnt[x];
        }

        return ans;
    }

    // Build Segment Tree
    private void build(int node, int l, int r, int[] nums) {

        if (l == r) {

            int rem = nums[l] % k;

            product[node] = rem;
            tree[node][rem] = 1;

            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        mergeInto(node, node * 2, node * 2 + 1);
    }

    // Update
    private void update(int node, int l, int r,
                        int index, int value) {

        if (l == r) {

            Arrays.fill(tree[node], 0);

            int rem = value % k;

            product[node] = rem;
            tree[node][rem] = 1;

            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        mergeInto(node, node * 2, node * 2 + 1);
    }

    // Merge two segment tree nodes
    private void mergeInto(int parent, int left, int right) {

        Arrays.fill(tree[parent], 0);

        // Prefixes completely inside LEFT
        for (int r = 0; r < k; r++) {
            tree[parent][r] += tree[left][r];
        }

        // Complete LEFT + prefix of RIGHT
        for (int r = 0; r < k; r++) {

            int newRem =
                (int) ((long) product[left] * r % k);

            tree[parent][newRem] += tree[right][r];
        }

        // Product of complete segment
        product[parent] =
            (int) ((long) product[left] * product[right] % k);
    }

    // Range Query
    private Node query(int node, int l, int r,
                       int ql, int qr) {

        if (ql <= l && r <= qr) {

            Node res = new Node();

            for (int i = 0; i < k; i++) {
                res.cnt[i] = tree[node][i];
            }

            res.prod = product[node];

            return res;
        }

        int mid = (l + r) / 2;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        Node left =
            query(node * 2, l, mid, ql, qr);

        Node right =
            query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    // Merge queried nodes
    private Node merge(Node left, Node right) {

        Node res = new Node();

        // Prefixes completely inside LEFT
        for (int r = 0; r < k; r++) {
            res.cnt[r] += left.cnt[r];
        }

        // Complete LEFT + prefix of RIGHT
        for (int r = 0; r < k; r++) {

            int newRem =
                (int) ((long) left.prod * r % k);

            res.cnt[newRem] += right.cnt[r];
        }

        // Complete product
        res.prod =
            (int) ((long) left.prod * right.prod % k);

        return res;
    }
}