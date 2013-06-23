package coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TurnOnLamps {
    static class Edge {
        int from;
        int to;
        boolean important;
        boolean on;

        Edge(int from, int to, boolean important, boolean on) {
            this.from = from;
            this.to = to;
            this.important = important;
            this.on = on;
        }
    }

    List<Edge>[] edges;
    int n;
    int[][] dp;

    void dfs(int v, int p) {
        int[][] cdp = new int[1 + edges[v].size() - (p < 0 ? 0 : 1)][n + 1];
        for (int[] e : cdp) {
            Arrays.fill(e, Integer.MAX_VALUE);
        }
        cdp[0][0] = 0;
        int q = 0;
        for (Edge e : edges[v]) {
            if (e.to == p) continue;
            dfs(e.to, v);
            ++q;
            for (int ec = 0; ec <= n; ec++) {
                int val = dp[e.to][ec];
                if (val == Integer.MAX_VALUE) continue;
                if (e.important && ((ec & 1) == 0) != e.on) {
                    continue;
                }
                for (int wc = 0; wc <= n; wc++) {
                    int val2 = cdp[q - 1][wc];
                    if (val2 == Integer.MAX_VALUE) {
                        continue;
                    }
                    for (int zc = 0; zc <= ec + wc && zc <= n; zc++) {
                        if (((ec + wc) & 1) != (zc & 1)) continue;
                        cdp[q][zc] = Math.min(cdp[q][zc], val + val2 + (wc + ec - zc) / 2);
                    }
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            cdp[q][i] = Math.min(cdp[q][i - 1], cdp[q][i]);
        }
        for (int i = 0; i <= n; i++) dp[v][i] = cdp[q][i];
    }

    public int minimize(int[] roads, String initState, String isImportant) {
        n = roads.length + 1;
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<Edge>();
        }
        for (int i = 0; i < roads.length; i++) {
            edges[i + 1].add(new Edge(i + 1, roads[i], isImportant.charAt(i) == '1', initState.charAt(i) == '1'));
            edges[roads[i]].add(new Edge(roads[i], i + 1, isImportant.charAt(i) == '1', initState.charAt(i) == '1'));
        }
        dp = new int[n][n + 1];
        for (int[] e : dp) {
            Arrays.fill(e, Integer.MAX_VALUE);
        }
        dfs(0, -1);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            int val = dp[0][i];
            if (val == Integer.MAX_VALUE) continue;
            ans = Math.min(val + i, ans);
        }
        return ans;
    }
}
