package coding;

import ru.ifmo.niyaz.graphalgorithms.MinCostMaxFlowGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Assignment {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        MinCostMaxFlowGraph g = new MinCostMaxFlowGraph(n + n + 2);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = in.nextInt();
                g.addEdge(i, j + n, 1, x);
            }
        }
        for (int i = 0; i < n; i++) {
            g.addEdge(n + n, i, k, 0);
            g.addEdge(i + n, n + n + 1, k, 0);
        }
        out.println(g.getMinCostMaxFlowSlow(n + n, n + n + 1)[1]);
    }
}
