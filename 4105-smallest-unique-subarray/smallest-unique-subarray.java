import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        Integer[] sa = new Integer[n];
        int[] rank = new int[n];

        // Step 1: Initialize suffix array and ranks
        for (int i = 0; i < n; i++) {
            sa[i] = i;
            rank[i] = nums[i];
        }

        // Step 2: Build Suffix Array
        for (int k = 1; k < n; k *= 2) {
            final int len = k;
            final int[] r = rank;

            Arrays.sort(sa, (a, b) -> {
                if (r[a] != r[b]) {
                    return Integer.compare(r[a], r[b]);
                }

                int ra = (a + len < n) ? r[a + len] : -1;
                int rb = (b + len < n) ? r[b + len] : -1;

                return Integer.compare(ra, rb);
            });

            int[] newRank = new int[n];
            newRank[sa[0]] = 0;

            for (int i = 1; i < n; i++) {
                int a = sa[i - 1];
                int b = sa[i];

                boolean different = rank[a] != rank[b];

                int a2 = (a + k < n) ? rank[a + k] : -1;
                int b2 = (b + k < n) ? rank[b + k] : -1;

                if (a2 != b2) {
                    different = true;
                }

                newRank[b] = newRank[a] + (different ? 1 : 0);
            }

            rank = newRank;

            if (rank[sa[n - 1]] == n - 1) {
                break;
            }
        }

        // Step 3: Build inverse suffix array
        int[] pos = new int[n];

        for (int i = 0; i < n; i++) {
            pos[sa[i]] = i;
        }

        // Step 4: Calculate LCP using Kasai's algorithm
        int[] lcp = new int[n];
        int h = 0;

        for (int i = 0; i < n; i++) {
            int r = pos[i];

            if (r == 0) {
                continue;
            }

            int j = sa[r - 1];

            while (i + h < n && j + h < n &&
                   nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[r] = h;

            if (h > 0) {
                h--;
            }
        }

        // Step 5: Find minimum unique subarray length
        int ans = n;

        for (int i = 0; i < n; i++) {
            int r = pos[i];

            int maxLcp = lcp[r];

            if (r + 1 < n) {
                maxLcp = Math.max(maxLcp, lcp[r + 1]);
            }

            int required = maxLcp + 1;

            if (required <= n - i) {
                ans = Math.min(ans, required);
            }
        }

        return ans;
    }
}