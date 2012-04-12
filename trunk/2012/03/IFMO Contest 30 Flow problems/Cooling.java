package mypackage;

import graphalgorithms.DijkstraGraph;
import graphalgorithms.DinicGraph;
import niyazio.FastScanner;
import niyazio.FastPrinter;

public class Cooling {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        DinicGraph g = new DinicGraph(n + 2);
        int source = 0;
        int target = n + 1;
        int[] c = new int[n];
        DinicGraph.Edge[] edges = new DinicGraph.Edge[m];
        int[] low = new int[m];
        for (int i = 0; i < m; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            int capLow = in.nextInt();
            int capHigh = in.nextInt();
            edges[i] = g.addEdge(from, to, 0, capHigh - capLow);
            low[i] = capLow;
            c[from - 1] += capLow;
            c[to - 1] -= capLow;
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (c[i] < 0) {
                g.addEdge(source, i + 1, 0, -c[i]);
            } else {
                ans += c[i];
                g.addEdge(i + 1, target, 0, c[i]);
            }
        }
        if (g.getMaxFlow(source, target) != ans) {
            out.println("NO");
            return;
        }
        out.println("YES");
        for (int i = 0; i < m; i++) {
            out.println(low[i] + edges[i].flow);
        }
	}
}
