package mypackage;

import graphalgorithms.DinicGraph;
import graphalgorithms.MinCostMaxFlowGraph;
import niyazio.FastScanner;
import niyazio.FastPrinter;

public class Gas {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        MinCostMaxFlowGraph g = new MinCostMaxFlowGraph(n + 2);
        int source = 0;
        int target = n + 1;
        int[] c = new int[n];
        MinCostMaxFlowGraph.Edge[] edges = new MinCostMaxFlowGraph.Edge[m];
        int[] low = new int[m];
        long all = 0;
        for (int i = 0; i < m; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            int capLow = in.nextInt();
            all += capLow;
            edges[i] = g.addEdge(from, to, 0, Integer.MAX_VALUE, 1);
            low[i] = capLow;
            c[from - 1] += capLow;
            c[to - 1] -= capLow;
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (c[i] < 0) {
                g.addEdge(source, i + 1, 0, -c[i], 0);
            } else {
                ans += c[i];
                g.addEdge(i + 1, target, 0, c[i], 0);
            }
        }
        long[] answer = g.getMinCostMaxFlow(source, target);
        if (answer[0] != ans) {
            out.println("-1");
            return;
        }
        out.println(answer[1] + all);
        for (int i = 0; i < m; i++) {
            out.println(low[i] + edges[i].flow);
        }
	}
}
