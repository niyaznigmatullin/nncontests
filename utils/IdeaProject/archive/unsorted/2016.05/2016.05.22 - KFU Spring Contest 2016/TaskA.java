package coding;

import ru.ifmo.niyaz.graphalgorithms.DinicGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int[] r = in.readIntArray(m);
        int[] c = in.readIntArray(n);
        DinicGraph g = new DinicGraph(n + m + 2);
        int src = n + m;
        int tar = src + 1;
        DinicGraph.Edge[][] edges = new DinicGraph.Edge[m][n];
        int[][] already = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = in.nextInt();
                already[i][j] = x;
                if (x < 0) {
                    edges[i][j] = g.addEdge(i, j + m, 1000000000);
                } else {
                    r[i] -= x;
                    c[j] -= x;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (r[i] < 0) {
                out.println(-1);
                return;
            }
        }
        for (int i = 0; i < n; i++) {
            if (c[i] < 0) {
                out.println(-1);
                return;
            }
        }
        for (int i = 0; i < m; i++) {
            g.addEdge(src, i, r[i]);
        }
        for (int i = 0; i < n; i++) {
            g.addEdge(i + m, tar, c[i]);
        }
        g.getMaxFlow(src, tar);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0) out.print(' ');
                if (already[i][j] >= 0) {
                    out.print(already[i][j]);
                } else {
                    out.print(edges[i][j].flow);
                }
            }
            out.println();
        }
    }
}
