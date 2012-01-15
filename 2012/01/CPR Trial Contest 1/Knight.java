package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class Knight {
    static class Edge {
        int from, to, flow, cap;
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

    static class Vertex implements Comparable<Vertex> {
        int num;
        Edge last;
        long d;

        public Vertex(int num) {
            this.num = num;
        }

        public int compareTo(Vertex o) {
            return d < o.d ? -1 : d > o.d ? 1 : num - o.num;
        }
    }

    static class Graph {
        int n;
        ArrayList<Edge>[] edges;

        public Graph(int n) {
            this.n = n;
            edges = new ArrayList[n];
            for (int i = 0; i < edges.length; i++) {
                edges[i] = new ArrayList<Edge>();
            }
        }

        void addEdge(int from, int to, int flow, int cap, long cost) {
            Edge e1 = new Edge(from, to, flow, cap, cost);
            Edge e2 = new Edge(to, from, flow, 0, -cost);
            e1.rev = e2;
            e2.rev = e1;
            edges[from].add(e1);
            edges[to].add(e2);
        }

        long[] getMinCostMaxFlow(int source, int target) {
            long[] h = new long[n];
            for (boolean changed = true; changed; ) {
                changed = false;
                for (int i = 0; i < n; i++) {
                    for (Edge e : edges[i]) {
                        if (e.cap > 0 && h[e.to] > h[e.from] + e.cost) {
                            h[e.to] = h[e.from] + e.cost;
                            changed = true;
                        }
                    }
                }
            }
            Vertex[] vertices = new Vertex[n];
            long[] d = new long[n];
            for (int i = 0; i < vertices.length; i++) {
                vertices[i] = new Vertex(i);
            }
            int flow = 0;
            long cost = 0;
            while (true) {
                dijkstra(source, vertices, d, h);
                if (d[target] == Long.MAX_VALUE) {
                    break;
                }
                int addFlow = 1;
                cost += (d[target] + h[target] - h[source]) * addFlow;
                flow += addFlow;
                Vertex v = vertices[target];
                while (v != vertices[source]) {
                    v.last.flow += addFlow;
                    v.last.rev.flow -= addFlow;
                    v = vertices[v.last.from];
                }
                for (int i = 0; i < n; i++) {
                    h[i] += d[i] == Long.MAX_VALUE ? 0 : d[i];
                }
            }
            return new long[]{flow, cost};
        }

        void dijkstra(int source, Vertex[] vertices, long[] d, long[] h) {
            TreeSet<Vertex> ts = new TreeSet<Vertex>();
            Arrays.fill(d, Long.MAX_VALUE);
            for (int i = 0; i < vertices.length; i++) {
                vertices[i].d = Long.MAX_VALUE;
            }
            d[source] = 0;
            vertices[source].d = 0;
            ts.add(vertices[source]);
            while (!ts.isEmpty()) {
                Vertex v = ts.pollFirst();
                for (Edge e : edges[v.num]) {
                    if (e.flow >= e.cap) {
                        continue;
                    }
                    if (d[e.to] == Long.MAX_VALUE
                            || d[e.to] > d[e.from] + e.cost + h[e.from]
                            - h[e.to]) {
                        if (e.cost + h[e.from] - h[e.to] < 0) {
                            throw new AssertionError();
                        }
                        if (ts.contains(vertices[e.to])) {
                            ts.remove(vertices[e.to]);
                        }
                        d[e.to] = d[e.from] + e.cost + h[e.from] - h[e.to];
                        vertices[e.to].d = d[e.to];
                        vertices[e.to].last = e;
                        ts.add(vertices[e.to]);
                    }
                }
            }
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Graph g = new Graph(2 * n + 2);
        g.addEdge(0, 1, 0, 2, 0);
        g.addEdge((2 * n - 2) + 1, 2 * n + 1, 0, 2, 0);
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int w = in.nextInt();
            g.addEdge(2 * from + 2, 2 * to + 1, 0, 100, w);
            g.addEdge(2 * to + 2, 2 * from + 1, 0, 100, w);
        }
        boolean[] canGoTwice = new boolean[n];
        Arrays.fill(canGoTwice, true);
        int k = in.nextInt();
        for (int i = 0; i < k; i++) {
            canGoTwice[in.nextInt() - 1] = false;
        }
        for (int i = 0; i < n; i++) {
            g.addEdge(2 * i + 1, 2 * i + 2, 0, canGoTwice[i] ? 100 : 1, 0);
        }
        long[] answer = g.getMinCostMaxFlow(0, 2 * n + 1);
        if (answer[0] != 2) {
            out.println(-1);
            return;
        }
        out.println(answer[1]);
    }
}
