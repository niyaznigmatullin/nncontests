package coding;

import ru.ifmo.niyaz.graphalgorithms.DinicGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Locale;

public class SPCLN {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] a = in.readInt2DArray(n, m);
        DinicGraph g = new DinicGraph(2 + n * (m + 1));
        final int INF = 1 << 25;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                g.addEdge(i * (m + 1) + j + 1, i * (m + 1) + j, INF);
            }
        }
        int src = n * (m + 1);
        int tar = src + 1;
        for (int i = 0; i < n; i++) {
            g.addEdge(src, i * (m + 1), INF);
            g.addEdge(i * (m + 1) + m, tar, INF);
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int mx = 100;
//            for (int j : a[i]) mx = Math.max(mx, j);
            sum += mx;
            for (int j = 0; j < m; j++) {
                if (a[i][j] < 0) {
                    g.addEdge(i * (m + 1) + j, i * (m + 1) + j + 1, INF);
                } else {
                    g.addEdge(i * (m + 1) + j, i * (m + 1) + j + 1, mx - a[i][j]);
                }
            }
        }
        int k = in.nextInt();
        for (int i = 0; i < k; i++) {
            int v = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            for (int j = 0; j < m; j++) {
                g.addEdge(v * (m + 1) + j, u * (m + 1) + j + 1, INF);
            }
        }
        out.println(String.format(Locale.US, "%.2f", (sum - g.getMaxFlow(src, tar)) * 1. / n));
    }
}
