package coding;

import ru.ifmo.niyaz.graphalgorithms.DinicGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = in.readIntArray(n);
        int[] b = in.readIntArray(n);
        int s = 0;
        for (int i : a) s += i;
        for (int i : b) s -= i;
        if (s != 0) {
            out.println("NO");
            return;
        }
        for (int i : a) s += i;
        DinicGraph g = new DinicGraph(n + n + 2);
        int src = n + n;
        int tar = n + n + 1;
        for (int i = 0; i < n; i++) g.addEdge(src, i, a[i]);
        for (int i = 0; i < n; i++) g.addEdge(i + n, tar, b[i]);
        DinicGraph.Edge[][] edges = new DinicGraph.Edge[n][n];
        for (int i = 0; i < n; i++) edges[i][i] = g.addEdge(i, i + n, 300);
        for (int i = 0; i < m; i++) {
            int v = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            edges[v][u] = g.addEdge(v, u + n, 300);
            edges[u][v] = g.addEdge(u, v + n, 300);
        }
        if (g.getMaxFlow(src, tar) != s) {
            out.println("NO");
            return;
        }
        out.println("YES");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0) out.print(' ');
                out.print(edges[i][j] == null ? 0 : edges[i][j].flow);
            }
            out.println();
        }
    }
}
