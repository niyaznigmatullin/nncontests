package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Aarelia {
    static class Edge {
        int from;
        int to;
        int cap;
        int flow;
        int cost;
        Edge rev;

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", cap=" + cap +
                    ", flow=" + flow +
                    ", cost=" + cost +
                    '}';
        }

        Edge(int from, int to, int cap, int flow, int cost) {
            this.from = from;
            this.to = to;
            this.cap = cap;
            this.flow = flow;
            this.cost = cost;
        }
    }

    static class Graph {
        List<Edge>[] edges;
        int n;

        Graph(int n) {
            this.n = n;
            edges = new List[n];
            for (int i = 0; i < n; i++) {
                edges[i] = new ArrayList<>();
            }
        }

        long minCostFlow(int source, int target) {
            long[] phi = new long[n];
//            while (true) {
//                boolean changed = false;
//                for (List<Edge> f : edges) {
//                    for (Edge e : f) {
//                        if (e.cap != e.flow && phi[e.to] > phi[e.from] + e.cost) {
//                            phi[e.to] = phi[e.from] + e.cost;
//                            changed = true;
//                        }
//                    }
//                }
//                if (!changed) break;
//            }
            long[] d = new long[n];
            boolean[] used = new boolean[n];
            Edge[] last = new Edge[n];
            long ans = 0;
            while (true) {
                Arrays.fill(d, Long.MAX_VALUE);
                Arrays.fill(used, false);
                d[source] = 0;
                while (true) {
                    int min = -1;
                    for (int i = 0; i < n; i++) {
                        if (used[i] || d[i] == Long.MAX_VALUE) continue;
                        if (min < 0 || d[min] > d[i]) min = i;
                    }
                    if (min < 0) break;
                    used[min] = true;
                    for (int i = 0; i < edges[min].size(); i++) {
                        Edge e = edges[min].get(i);
                        if (e.cap != e.flow && d[e.to] > d[e.from] + phi[e.from] - phi[e.to] + e.cost) {
                            d[e.to] = d[e.from] + phi[e.from] - phi[e.to] + e.cost;
                            last[e.to] = e;
                        }
                    }
                }
                if (!used[target]) break;
                long cost = d[target] + phi[target] - phi[source];
                int push = Integer.MAX_VALUE;
                for (int v = target; v != source; v = last[v].from) {
                    push = Math.min(push, last[v].cap - last[v].flow);
                }
                for (int v = target; v != source;) {
                    Edge e = last[v];
                    e.flow += push;
                    e.rev.flow -= push;
                    v = e.from;
                }
                ans += cost * push;
                for (int i = 0; i < n; i++) phi[i] += d[i];
            }
            return ans;
        }

        Edge addEdge(int from, int to, int cap, int cost) {
            Edge e1 = new Edge(from, to, cap, 0, cost);
            Edge e2 = new Edge(to, from, 0, 0, -cost);
            e1.rev = e2;
            e2.rev = e1;
            edges[from].add(e1);
            edges[to].add(e2);
            return e1;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Graph g = new Graph(n + 4);
        int source = n + 1;
        int target = n;
        int source2 = n + 2;
        int target2 = n + 3;
        final int INF = 100000000;
        long sum = 0;
        Edge[] all = new Edge[n];
        for (int i = 0; i < n; i++) {
            int h = in.nextInt() + 10000000;
            all[i] = g.addEdge(source2, i + 1, h, 0);
            g.addEdge(i, target2, h, 0);
            sum += h;
        }
        g.addEdge(target, source, INF, 0);
        g.addEdge(source, target, INF, 0);
        for (int i = 0; i < n; i++) {
            g.addEdge(source, i, INF, 0);
        }
        for (int i = 0; i < m; i++) {
            String type = in.next();
            int len = in.nextInt();
            int cost = in.nextInt();
            for (int j = 0; j + len <= n; j++) {
                if (type.equals("+")) {
                    g.addEdge(j, j + len, INF, cost);
                } else {
                    g.addEdge(j + len, j, INF, cost);
                }
            }
        }
        long ans = g.minCostFlow(source2, target2);
        long gotSum = 0;
        for (int i = 0; i < n; i++) gotSum += all[i].flow;
        if (gotSum != sum) {
            out.println("-1");
            return;
        }
        out.println(ans);
    }
}
