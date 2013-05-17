package lib.test.on2013_03.on2013_03_11_Game_theory__1.Grundy;



import ru.ifmo.niyaz.graphalgorithms.GraphUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Grundy {

    static int[][] edges;
    static int[] g;

    static int dfs(int v) {
        if (g[v] >= 0) return g[v];
        int[] set = new int[edges[v].length + 1];
        for (int i : edges[v]) {
            int u = dfs(i);
            if (u < set.length) set[u] = 1;
        }
        for (int i = 0; ; i++) {
            if (set[i] == 0) {
                g[v] = i;
                break;
            }
        }
        return g[v];
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] from = new int[m];
        int[] to = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
        }
        edges = GraphUtils.getEdgesDirectedUnweighted(n, from, to);
        g = new int[n];
        Arrays.fill(g, -1);
        for (int i = 0; i < n; i++) {
            if (g[i] < 0) {
                dfs(i);
            }
        }
        for (int i = 0; i < n; i++) {
            out.println(g[i]);
        }
    }
}
