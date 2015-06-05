package coding;

import ru.ifmo.niyaz.graphalgorithms.DinicGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Chefvote {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        int s = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            s += a[i];
        }
        if (s != n) {
            out.println(-1);
            return;
        }
        DinicGraph g = new DinicGraph(n + n + 2);
        int src = 2 * n;
        int tar = src + 1;
        for (int i = 0; i < n; i++) {
            g.addEdge(src, i, 1);
            g.addEdge(i + n, tar, a[i]);
        }
        DinicGraph.Edge[][] edges = new DinicGraph.Edge[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    edges[i][j] = g.addEdge(i, j + n, 1);
                }
            }
        }
        if (g.getMaxFlow(src, tar) != n) {
            out.println("-1");
            return;
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) if (i != j) {
                if (edges[i][j].flow > 0) {
                    ans[i] = j + 1;
                }
            }
        }
        out.printArray(ans);
    }
}
