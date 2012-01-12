package mypackage;

import niyazio.FastScanner;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class TaskC {
    static class Edge {
        int from;
        int to;
        int flow;
        int cap;
        Edge rev;

        public Edge(int from, int to, int flow, int cap) {
            super();
            this.from = from;
            this.to = to;
            this.flow = flow;
            this.cap = cap;
        }

    }

    static class Graph {
        ArrayList<Edge>[] edges;
        int[] cur;
        int[] q;
        int[] d;
        int n;

        public Graph(int n) {
            edges = new ArrayList[n];
            this.n = n;
            for (int i = 0; i < edges.length; i++) {
                edges[i] = new ArrayList<Edge>();
            }
        }

        void addEdge(int from, int to, int flow, int cap) {
            Edge e1 = new Edge(from, to, flow, cap);
            Edge e2 = new Edge(to, from, -flow, 0);
            e1.rev = e2;
            e2.rev = e1;
            edges[from].add(e1);
            edges[to].add(e2);
        }

        boolean bfs(int source, int target) {
            int head = 0;
            int tail = 1;
            Arrays.fill(d, Integer.MAX_VALUE);
            d[source] = 0;
            q[0] = source;
            while (head < tail) {
                int x = q[head++];
                for (Edge e : edges[x]) {
                    if (e.cap - e.flow > 0 && d[e.to] == Integer.MAX_VALUE) {
                        d[e.to] = d[x] + 1;
                        q[tail++] = e.to;
                        if (e.to == target) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        int dfs(int x, int target, int cMin) {
            if (x == target) {
//                System.err.println("cmin = " + cMin);
//                System.err.println(target);
                return cMin;
            }
            for (int i = cur[x]; i < edges[x].size(); cur[x] = ++i) {
                Edge e = edges[x].get(i);
                if (d[e.to] != d[x] + 1 || e.cap - e.flow == 0) {
                    continue;
                }
                int add = dfs(e.to, target, Math.min(cMin, e.cap - e.flow));
                if (add == 0) {
                    continue;
                }
                e.flow += add;
                e.rev.flow -= add;
//                System.err.println(x);
                return add;
            }
            return 0;
        }

        long getMaxFlow(int source, int target) {
            cur = new int[n];
            q = new int[n];
            d = new int[n];
            long flow = 0;
            while (bfs(source, target)) {
                Arrays.fill(cur, 0);
                while (true) {
//                    System.err.println("now path : ");
                    int add = dfs(source, target, Integer.MAX_VALUE);
                    if (add == 0) {
                        break;
                    }
                    flow += add;
                }
            }
            return flow;
        }
    }

    static final int INF = 1 << 28;

    public void solve(int testNumber, FastScanner in, PrintWriter out) {
//        System.err.println("curtest # " + testNumber);
        int n = in.nextInt();
        int m = in.nextInt();
        int start = in.nextInt() - 1;
        int finish = in.nextInt() - 1;
        Graph g = new Graph(2 + n * 2);
        for (int i = 0; i < n; i++) {
            g.addEdge(2 * i + 1, 2 * i + 2, 0, 1);
        }
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            g.addEdge(2 * from + 2, 2 * to + 1, 0, INF);
            g.addEdge(2 * to + 2, 2 * from + 1, 0, INF);
        }
        g.addEdge(0, 2 * start + 2, 0, INF);
        g.addEdge(2 * finish + 1, n * 2 + 1, 0, INF);
        long maxFlow = g.getMaxFlow(0, n * 2 + 1);
        if (maxFlow > m) {
            out.println(0);
        } else {
            out.println(maxFlow);
        }
    }
}
