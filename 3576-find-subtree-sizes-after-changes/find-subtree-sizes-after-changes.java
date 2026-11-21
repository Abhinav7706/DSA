
class Solution {
    List<Integer>[] adj;
    int[] newParent;
    int[] ans;
    int[] last;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        // Build the original tree
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        newParent = parent.clone();
        last = new int[26];
        Arrays.fill(last, -1);

        // Find the closest matching ancestor
        dfs1(0, s);

        // Build the modified tree
        List<Integer>[] modified = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            modified[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            modified[newParent[i]].add(i);
        }

        ans = new int[n];

        // Calculate subtree sizes
        dfs2(0, modified);

        return ans;
    }

    void dfs1(int node, String s) {
        int ch = s.charAt(node) - 'a';

        int old = last[ch];

        if (old != -1) {
            newParent[node] = old;
        }

        last[ch] = node;

        for (int child : adj[node]) {
            dfs1(child, s);
        }

        // Backtrack to restore the nearest ancestor
        last[ch] = old;
    }

    int dfs2(int node, List<Integer>[] tree) {
        int size = 1;

        for (int child : tree[node]) {
            size += dfs2(child, tree);
        }

        ans[node] = size;
        return size;
    }
}
