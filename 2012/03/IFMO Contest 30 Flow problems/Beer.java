package mypackage;

import graphalgorithms.MinCostMaxFlowGraph;
import niyazio.FastScanner;
import niyazio.FastPrinter;

public class Beer {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        MinCostMaxFlowGraph g = new MinCostMaxFlowGraph(n + 1);
        int[] cost = new int[n];
        for (int i = 1; i < n; i++) {
            cost[i] = in.nextInt();
            g.addEdge(i, n, 0, Integer.MAX_VALUE, -cost[i]);
        }
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int cap = in.nextInt();
            int c = in.nextInt();
            g.addEdge(from, to, 0, cap, c);
            g.addEdge(to, from, 0, cap, c);
        }
        out.println(-g.getMinCostFlow(0, n)[1]);
	}
}
