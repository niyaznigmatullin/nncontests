package coding;

import ru.ifmo.niyaz.graphalgorithms.DinicGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Data {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int L = in.nextInt();
        DinicGraph g = new DinicGraph(n);
        int[] d = in.readIntArray(n);
        int source = -1;
        int target = -1;
        for (int i = 0; i < n; i++) {
            if (d[i] == 1) source = i;
            if (d[i] == L) target = i;
        }
        DinicGraph.Edge[] edges = new DinicGraph.Edge[m];
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int cap = in.nextInt();
            edges[i] = g.addEdge(from, to, cap);
        }
//        long got = g.findBlockingFlowPreflow(source, target, new boolean[n], new long[n]);
        g.getMaxFlowPreflow(source, target);
        for (int i = 0; i < m; i++) {
            out.println(edges[i].flow);
        }
    }
}
