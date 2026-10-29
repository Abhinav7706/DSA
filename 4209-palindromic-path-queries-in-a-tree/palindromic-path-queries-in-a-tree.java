
import java.util.*;

class Solution {
    private List<Integer>[] graph;
    private int[][] up;
    private int[] tin, tout, depth;
    private int LOG, timer;
    private Fenwick bit;
    private int[] mask;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        up = new int[LOG][n];
        tin = new int[n];
        tout = new int[n];
        depth = new int[n];

        int[] parent = new int[n];
        int[] next = new int[n];
        int[] stack = new int[n];
        Arrays.fill(parent, -1);

        int top = 0;
        stack[0] = 0;
        parent[0] = 0;
        tin[0] = ++timer;

        while (top >= 0) {
            int u = stack[top];

            if (next[u] < graph[u].size()) {
                int v = graph[u].get(next[u]++);

                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                up[0][v] = u;

                for (int k = 1; k < LOG; k++) {
                    up[k][v] = up[k - 1][up[k - 1][v]];
                }

                tin[v] = ++timer;
                stack[++top] = v;
            } else {
                tout[u] = timer;
                top--;
            }
        }

        bit = new Fenwick(n + 1);
        mask = new int[n];

        for (int i = 0; i < n; i++) {
            mask[i] = 1 << (s.charAt(i) - 'a');
            rangeUpdate(tin[i], tout[i], mask[i]);
        }

        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");

            int u = Integer.parseInt(p[1]);

            if (p[0].equals("update")) {
                char c = p[2].charAt(0);
                int newMask = 1 << (c - 'a');
                int delta = mask[u] ^ newMask;

                if (delta != 0) {
                    rangeUpdate(tin[u], tout[u], delta);
                    mask[u] = newMask;
                }
            } else {
                int v = Integer.parseInt(p[2]);
                int a = lca(u, v);

                int pathMask = bit.query(tin[u])
                             ^ bit.query(tin[v])
                             ^ mask[a];

                answer.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return answer;
    }

    private void rangeUpdate(int l, int r, int value) {
        bit.update(l, value);
        bit.update(r + 1, value);
    }

    private boolean isAncestor(int u, int v) {
        return tin[u] <= tin[v] && tout[v] <= tout[u];
    }

    private int lca(int u, int v) {
        if (isAncestor(u, v)) return u;
        if (isAncestor(v, u)) return v;

        for (int k = LOG - 1; k >= 0; k--) {
            if (!isAncestor(up[k][u], v)) {
                u = up[k][u];
            }
        }

        return up[0][u];
    }

    private static class Fenwick {
        int[] tree;
        int n;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        void update(int i, int value) {
            for (; i <= n; i += i & -i) {
                tree[i] ^= value;
            }
        }

        int query(int i) {
            int result = 0;
            for (; i > 0; i -= i & -i) {
                result ^= tree[i];
            }
            return result;
        }
    }
}
