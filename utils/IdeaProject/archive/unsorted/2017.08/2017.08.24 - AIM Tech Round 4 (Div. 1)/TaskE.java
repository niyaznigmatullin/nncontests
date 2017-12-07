package coding;

import ru.ifmo.niyaz.graphalgorithms.DinicGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int src = in.nextInt() - 1;
        int tar = in.nextInt() - 1;
        DinicGraph g = new DinicGraph(n);
        int[] from = new int[m];
        int[] to = new int[m];
        int[] w = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
            w[i] = in.nextInt();
        }
        final int INF = 1000000000;
        for (int i = 0; i < m; i++) {
            g.addEdge(from[i], to[i], w[i] == 0 ? INF : 1);
            if (w[i] == 1) {
                g.addEdge(to[i], from[i], INF);
            }
        }
        long flow = g.getMaxFlow(src, tar);
//        if (flow >= INF) {
//            while (true);
//        }
        boolean[] cut = g.getCut(src, tar);
        DinicGraph.Edge[] edges = new DinicGraph.Edge[m];
        Circulation h = new Circulation(n);
        int count = 0;
        for (int i = 0; i < m; i++) {
            if (w[i] == 0) continue;
            edges[i] = h.addEdge(from[i], to[i], 1, 2000);
            count++;
        }
        h.addEdge(tar, src, 0, INF);
        long got = h.getCirculation();
//        if (got != count) throw new AssertionError();
        out.println(flow);
//        count = 0;
//        g = new DinicGraph(n);
//        long cutValue = 0;
//        long[] deg = new long[n];
        for (int i = 0; i < m; i++) {
            int curFlow = w[i] == 0 ? 0 : edges[i].flow + 1;
//            deg[from[i]] -= curFlow;
//            deg[to[i]] += curFlow;
            out.print(curFlow + " ");
            if (cut[from[i]] && !cut[to[i]]) {
//                g.addEdge(from[i], to[i], curFlow);
//                cutValue += curFlow;
                out.println(curFlow);
                count++;
            } else {
//                g.addEdge(from[i], to[i], curFlow + 1);
                out.println(curFlow + 1);
            }
        }
//        for (int i = 0; i < n; i++) {
//            if (i != src && i != tar) {
//                if (deg[i] != 0) throw new AssertionError();
//            } else {
////                if (Math.abs(deg[i]) != cutValue) {
////                    throw new AssertionError();
////                }
//            }
//        }
//        if (cutValue != g.getMaxFlow(src, tar)) {
//            throw new AssertionError();
//        }
//        if (count != flow) {
//            int[][] f = new int[10][];
//            for (int i = 0; i < 10; i++) {
//                f[i] = new int[10000000];
//            }
//        }
    }

    static class Circulation extends DinicGraph {
        int src;
        int tar;

        public Circulation(int n) {
            super(n + 2);
            src = n;
            tar = n + 1;
        }

        public DinicGraph.Edge addEdge(int from, int to, int lowCap, int highCap) {
            if (lowCap > 0) {
                addEdge(src, to, lowCap);
                addEdge(from, tar, lowCap);
            }
            return addEdge(from, to, highCap - lowCap);
        }

        public long getCirculation() {
            return getMaxFlow(src, tar);
        }

    }
}
