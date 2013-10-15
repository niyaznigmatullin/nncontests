package lib.test.on2013_06.on2013_06_12_ABBYY_Cup_3_0.C1___Tidying_Up__30_points_;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class TaskC1 {
    static class MinCostMaxFlowGraph {
        public static class Edge {
            int from;
            int to;
            public int flow;
            int cap;
            long cost;
            Edge rev;

            public Edge(int from, int to, int flow, int cap, long cost) {
                this.from = from;
                this.to = to;
                this.flow = flow;
                this.cap = cap;
                this.cost = cost;
            }

            @Override
            public String toString() {
                return "Edge [from=" + from + ", to=" + to + ", flow=" + flow
                        + ", cap=" + cap + ", cost=" + cost + "]";
            }

        }

        int n;
        ArrayList<Edge>[] edges;
        int[] q;
        boolean[] inQueue;

        public MinCostMaxFlowGraph(int n) {
            this.n = n;
            edges = new ArrayList[n];
            for (int i = 0; i < edges.length; i++) {
                edges[i] = new ArrayList<Edge>();
            }
            q = new int[n + 1];
            inQueue = new boolean[n];
        }

        public Edge addEdge(int from, int to, int flow, int cap, long cost) {
            Edge e1 = new Edge(from, to, flow, cap, cost);
            Edge e2 = new Edge(to, from, flow, 0, -cost);
            e1.rev = e2;
            e2.rev = e1;
            edges[from].add(e1);
            edges[to].add(e2);
            return e1;
        }

        public long[] getMinCostFlowAcyclic(int source, int target) {
            Edge[] lastEdge = new Edge[n];
            long[] d = new long[n];
            int flow = 0;
            long cost = 0;
            while (true) {
                dijkstra(source, target, lastEdge, d);
                if (d[target] == Long.MAX_VALUE) {
                    break;
                }
                int addFlow = Integer.MAX_VALUE;
                for (int v = target; v != source; ) {
                    Edge e = lastEdge[v];
                    addFlow = Math.min(addFlow, e.cap - e.flow);
                    v = e.from;
                }
                cost += (d[target]) * addFlow;
                flow += addFlow;
                for (int v = target; v != source; ) {
                    Edge e = lastEdge[v];
                    e.flow += addFlow;
                    e.rev.flow -= addFlow;
                    v = e.from;
                }
//                for (int i = 0; i < n; i++) {
//                    h[i] += d[i] == Long.MAX_VALUE ? 0 : d[i];
//                }
            }
            return new long[]{flow, cost};
        }

        void dijkstra(int source, int target, Edge[] lastEdge, final long[] d) {
            Arrays.fill(d, Long.MAX_VALUE);
            d[source] = 0;
            Arrays.fill(inQueue, false);
            inQueue[source] = true;
            int head = 0;
            int tail = 1;
            q[head] = source;
            while (head != tail) {
                int v = q[head++];
                if (head == q.length) head = 0;
                inQueue[v] = false;
                for (Edge e : edges[v]) {
                    if (e.flow >= e.cap) {
                        continue;
                    }
                    if (d[e.to] == Long.MAX_VALUE
                            || d[e.to] > d[e.from] + e.cost) {
                        if (!inQueue[e.to]) q[tail++] = e.to;
                        if (tail == q.length) tail = 0;
                        d[e.to] = d[e.from] + e.cost;
                        lastEdge[e.to] = e;
                    }
                }
            }
        }
    }
    final int[] DX = {1, 0, -1, 0};
    final int[] DY = {0, 1, 0, -1};

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
//        int n = 80;
//        int m = 80;
        int[][] a = new int[n][];
        Random rand = new Random();
        int[] z = new int[n * m / 2];
        Arrays.fill(z, 2);
        for (int i = 0; i < n; i++) {
//            a[i] = new int[m];
//            for (int j = 0; j < m; j++) {
//                int x;
//                do {
//                    x = rand.nextInt(n * m / 2);
//                } while (z[x] == 0);
//                a[i][j] = x + 1;
//            }
            a[i] = in.readIntArray(m);
        }
        MinCostMaxFlowGraph g = new MinCostMaxFlowGraph(n * m + 2);
        int source = n * m;
        int target = source + 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((i + j & 1) == 1) {
                    g.addEdge(i * m + j, target, 0, 1, 0);
                } else {
                    g.addEdge(source, i * m + j, 0, 1, 0);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((i + j & 1) == 1) continue;
                for (int dir = 0; dir < 4; dir++) {
                    int x = i + DX[dir];
                    int y = j + DY[dir];
                    if (x < 0 || y < 0 || x >= n || y >= m) {
                        continue;
                    }
                    g.addEdge(i * m + j, x * m + y, 0, 1, a[i][j] == a[x][y] ? 0 : 1);
                }
            }
        }
        out.println(g.getMinCostFlowAcyclic(source, target)[1]);
    }
}
