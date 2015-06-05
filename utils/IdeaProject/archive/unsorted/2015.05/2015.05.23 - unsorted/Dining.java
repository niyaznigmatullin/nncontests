package coding;

import ru.ifmo.niyaz.graphalgorithms.MinCostMaxFlowGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Dining {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int d = in.nextInt();
        int k = in.nextInt();
        double[][] p = new double[n][d];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < d; j++) {
                p[i][j] = in.nextDouble();
            }
        }
        MinCostMaxFlowGraph g = new MinCostMaxFlowGraph(d + n + 2 + 2);
        int src1 = d + n;
        int tar1 = src1 + 1;
        int src2 = tar1 + 1;
        int tar2 = src2 + 1;
        g.addEdge(tar1, src1, 300, 0);
        final long MUL = 1000000000000000L;
        MinCostMaxFlowGraph.Edge[][] edges = new MinCostMaxFlowGraph.Edge[d][n];
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < n; j++) {
                edges[i][j] = g.addEdge(i, j + d, 1, Math.round(-Math.log(p[j][i]) * MUL));
            }
        }
        for (int i = 0; i < d; i++) {
            g.addEdge(src1, i, k - 1, 0);
            g.addEdge(src2, i, 1, 0);
            g.addEdge(src1, tar2, 1, 0);
        }
        for (int i = 0; i < n; i++) {
            g.addEdge(i + d, tar2, 1, 0);
            g.addEdge(src2, tar1, 1, 0);
        }
        long[] f = g.getMinCostMaxFlowSlow(src2, tar2);
//        if (f[0] != n + d) {
//            throw new AssertionError();
//        }
        double ans = f[1];
        ans /= MUL;
        ans = -ans;
        ans = Math.exp(ans);
        out.println(ans);
        for (int i = 0; i < n; i++) {
            if (i > 0) out.print(' ');
            int day = -1;
            for (int j = 0; j < d; j++) {
                if (edges[j][i].flow > 0) {
                    day = j;
                    break;
                }
            }
//            if (day < 0) throw new AssertionError();
            out.print(day + 1);
        }
        out.println();
    }
}
