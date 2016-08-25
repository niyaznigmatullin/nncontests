package coding;

import ru.ifmo.niyaz.graphalgorithms.MinCostMaxFlowGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Circulation g = new Circulation(n);
        long ans = 0;
        final int INF = 100000000;
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int cap = in.nextInt();
            int flow = in.nextInt();
            if (flow <= cap) {
                g.addEdge(from, to, flow, flow, 0);
                g.addEdge(from, to, 0, cap - flow, 1);
                g.addEdge(from, to, 0, INF, 2);
                g.addEdge(to, from, 0, flow, 1);
            } else {
                ans += flow - cap;
                g.addEdge(from, to, flow, flow, 0);
                g.addEdge(from, to, 0, INF, 2);
                g.addEdge(to, from, 0, flow - cap, 0);
                g.addEdge(to, from, 0, cap, 1);
            }
        }
        g.addEdge(n - 1, 0, 0, INF, 0);
        ans += g.getCost();
        out.println(ans);
    }

    static class Circulation extends MinCostMaxFlowGraph {
        int src;
        int tar;

        public Circulation(int n) {
            super(n + 2);
            src = n;
            tar = n + 1;
        }

        public void addEdge(int from, int to, int lowCap, int highCap, int cost) {
            if (lowCap > 0) {
                addEdge(src, to, lowCap, cost);
                addEdge(from, tar, lowCap, cost);
            }
            addEdge(from, to, highCap - lowCap, cost);
        }

        public long getCost() {
            long[] ret = getMinCostMaxFlowSlow(src, tar);
            return ret[1];
        }
    }
}
