package coding;

public class MaxMinTreeGame {
    public int findend(int[] edges, int[] costs) {
        int n = costs.length;
        int[] deg = new int[n];
        for (int i = 0; i + 1 < n; i++) {
            deg[edges[i]]++;
            deg[i + 1]++;
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (deg[i] != 1) continue;
            ans = Math.max(ans, costs[i]);
        }
        return ans;
    }
}
