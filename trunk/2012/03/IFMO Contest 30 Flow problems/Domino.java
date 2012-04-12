package mypackage;

import graphalgorithms.MinCostMaxFlowGraph;
import niyazio.FastScanner;
import niyazio.FastPrinter;
import sun.jvm.hotspot.debugger.dbx.x86.DbxX86Thread;

public class Domino {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = in.nextInt();
            }
        }
        MinCostMaxFlowGraph g = new MinCostMaxFlowGraph(n * m + 3);
        int source = 0;
        int target = n * m + 1;
        final int[] DX = {1, 0, -1, 0};
        final int[] DY = {0, 1, 0, -1};
        g.addEdge(target, target + 1, 0, k, 0);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((i + j & 1) == 1) {
                    g.addEdge(i * m + j + 1, target, 0, 1, 0);
                    continue;
                }
                g.addEdge(source, i * m + j + 1, 0, 1, 0);
                for (int dir = 0; dir < 4; dir++) {
                    int x = i + DX[dir];
                    int y = j + DY[dir];
                    if (x < 0 || y < 0 || x >= n || y >= m) {
                        continue;
                    }
                    g.addEdge(i * m + j + 1, x * m + y + 1, 0, 1, -a[i][j] * a[x][y]);
                }
            }
        }
        out.println(-g.getMinCostMaxFlow(source, target + 1)[1]);
    }
}
