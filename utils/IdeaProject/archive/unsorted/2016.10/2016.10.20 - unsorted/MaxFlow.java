package coding;

import ru.ifmo.niyaz.graphalgorithms.DinicGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class MaxFlow {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        DinicGraph g = new DinicGraph(n);
        for (int i = 0; i < m; i++) {
            int v = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            int cap = in.nextInt();
            g.addUndirectedEdge(v, u, cap);
        }
        out.println(g.getMaxFlowPreflow(0, n - 1));
    }
}
