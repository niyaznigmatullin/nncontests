package mypackage;

import graphalgorithms.MinCostMaxFlowGraph;
import niyazio.FastScanner;
import niyazio.FastPrinter;

public class RentACar {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        MinCostMaxFlowGraph g = new MinCostMaxFlowGraph(2 + n);
        int source = 0;
        int target = n + 1;
        long all = 0;
        for (int i = 0; i < n; i++) {
            int v = in.nextInt();
            all += v;
            g.addEdge(source, i + 1, 0, v, 0);
            g.addEdge(i + 1, target, 0, in.nextInt(), 0);
        }
        for (int i = 0; i < m; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            int w = in.nextInt();
            g.addEdge(from, to, 0, Integer.MAX_VALUE, w);
            g.addEdge(to, from, 0, Integer.MAX_VALUE, w);
        }
        long[] ans = g.getMinCostMaxFlow(source, target);
        if (ans[0] != all) {
            out.println(-1);
            return;
        }
        out.println(ans[1]);
	}
}
