package mypackage;

import graphalgorithms.MinCostMaxFlowGraph;
import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int startAll = 0;
        int start = 2 * n + 2;
        int finish = 2 * n + 1;
        MinCostMaxFlowGraph g = new MinCostMaxFlowGraph(2 * n + 3);
        int[] s = new int[n];
        int[] t = new int[n];
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = in.nextInt();
            t[i] = in.nextInt();
            c[i] = in.nextInt();
        }
        g.addEdge(startAll, start, 0, k, 0);
        MinCostMaxFlowGraph.Edge[] edges = new MinCostMaxFlowGraph.Edge[n];
        for (int i = 0; i < n; i++) {
            g.addEdge(start, 2 * i + 1, 0, 1, 0);
            edges[i] = g.addEdge(2 * i + 1, 2 * i + 2, 0, 1, -c[i]);
            g.addEdge(2 * i + 2, finish, 0, 1, 0);
        }
        for (int i = 0; i < n; i++) {
            int minimal = -1;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (minimal >= 0 && s[minimal] + t[minimal] <= s[j] + t[j]) {
                    continue;
                }
                if (s[i] + t[i] <= s[j]) {
                    minimal = j;
                }
            }
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (s[i] + t[i] <= s[j] && s[j] <= s[minimal] + t[minimal]) {
                    g.addEdge(2 * i + 2, 2 * j + 1, 0, 2 * k, 0);
                    g.addEdge(2 * i + 1, 2 * j + 1, 0, 2 * k, 0);
                }
            }
        }
        g.getMinCostFlowSlowAcyclic(startAll, finish);
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = edges[i].flow;
        }
        out.printArray(answer);
    }
}
