package coding;

import ru.ifmo.niyaz.graphalgorithms.MinCostMaxFlowGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int f = in.nextInt();
        int s = in.nextInt();
        char[][] from = new char[n][];
        for (int i = 0; i < n; i++) {
            from[i] = in.next().toCharArray();
        }
        char[][] to = new char[n][];
        for (int i = 0; i < n; i++) {
            to[i] = in.next().toCharArray();
        }
        MinCostMaxFlowGraph g = new MinCostMaxFlowGraph(n * m + 2);
        int src = n * m;
        int tar = src + 1;
        int[][] type = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (from[i][j] != to[i][j]) {
                    type[i][j] = from[i][j] == 'M' ? 0 : 1;
                } else {
                    type[i][j] = -1;
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (type[i][j] == 0) {
                    g.addEdge(src, i * m + j, 1, -f);
                    ans += f;
                } else if (type[i][j] == 1) {
                    g.addEdge(i * m + j, tar, 1, -f);
                    ans += f;
                }
            }
        }
        for (int v = 0; v < n * m; v++) {
            if (type[v / m][v % m] != 0) continue;
            for (int u = 0; u < n * m; u++) {
                if (v == u) continue;
                if (type[u / m][u % m] != 1) continue;
                g.addEdge(v, u, 1, (long) (Math.abs(v / m - u / m) + Math.abs(v % m - u % m)) * s);
            }
        }
        ans += g.getMinCostFlow(src, tar)[1];
        out.println("Case #" + testNumber + ": " + ans);
    }
}
