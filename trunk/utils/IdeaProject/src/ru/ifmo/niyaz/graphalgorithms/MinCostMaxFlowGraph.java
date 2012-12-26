package ru.ifmo.niyaz.graphalgorithms;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 21.03.12
 * Time: 18:59
 * To change this template use File | Settings | File Templates.
 */
public class MinCostMaxFlowGraph {
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

    public MinCostMaxFlowGraph(int n) {
        this.n = n;
        edges = new ArrayList[n];
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new ArrayList<Edge>();
        }
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

    public long[] getMinCostFlow(int source, int target) {
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
        Edge[] lastEdge = new Edge[n];
        long[] d = new long[n];
        int flow = 0;
        long cost = 0;
        while (true) {
            dijkstra(source, lastEdge, d, h);
            if (d[target] == Long.MAX_VALUE) {
                break;
            }
            long curCost = d[target] + h[target] - h[source];
            if (curCost >= 0) {
                break;
            }
            int addFlow = Integer.MAX_VALUE;
            for (int v = target; v != source; ) {
                Edge e = lastEdge[v];
                addFlow = Math.min(addFlow, e.cap - e.flow);
                v = e.from;
            }
            cost += curCost * addFlow;
            flow += addFlow;
            for (int v = target; v != source; ) {
                Edge e = lastEdge[v];
                e.flow += addFlow;
                e.rev.flow -= addFlow;
                v = e.from;
            }
            for (int i = 0; i < n; i++) {
                h[i] += d[i] == Long.MAX_VALUE ? 0 : d[i];
            }
        }
        return new long[]{flow, cost};
    }

    public long[] getMinCostFlowSlowAcyclic(int source, int target) {
        long[] h = getPotentialsAcyclic();
        boolean[] was = new boolean[n];
        Edge[] lastEdge = new Edge[n];
        long[] d = new long[n];
        int flow = 0;
        long cost = 0;
        while (true) {
            dijkstraSlow(source, was, lastEdge, d, h);
            if (d[target] == Long.MAX_VALUE) {
                break;
            }
            int addFlow = Integer.MAX_VALUE;
            int v = target;
            while (v != source) {
                addFlow = Math.min(addFlow, lastEdge[v].cap - lastEdge[v].flow);
                v = lastEdge[v].from;
            }
            long curCost = d[target] + h[target] - h[source];
            if (curCost >= 0) {
                break;
            }
            cost += curCost * addFlow;
            flow += addFlow;
            v = target;
            while (v != source) {
                lastEdge[v].flow += addFlow;
                lastEdge[v].rev.flow -= addFlow;
                v = lastEdge[v].from;
            }
            for (int i = 0; i < n; i++) {
                h[i] += d[i] == Long.MAX_VALUE ? 0 : d[i];
            }
        }
        return new long[]{flow, cost};
    }

    private long[] getPotentialsAcyclic() {
        long[] h = new long[n];
        {
            List<Integer> topSort = new ArrayList<Integer>();
            boolean[] was = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (!was[i]) {
                    dfs(i, was, topSort);
                }
            }
            Collections.reverse(topSort);
            for (int i : topSort) {
                for (Edge e : edges[i]) {
                    if (e.rev.cap > 0) {
                        h[i] = Math.min(h[i], h[e.to] + e.rev.cost);
                    }
                }
            }
        }
        return h;
    }

    void dijkstraSlow(int source, boolean[] was, Edge[] lastEdge, long[] d, long[] h) {
        Arrays.fill(d, Long.MAX_VALUE);
        Arrays.fill(was, false);
        d[source] = 0;
        while (true) {
            int min = -1;
            for (int i = 0; i < d.length; i++) {
                if (d[i] == Long.MAX_VALUE || was[i]) {
                    continue;
                }
                if (min < 0 || d[min] > d[i]) {
                    min = i;
                }
            }
            if (min < 0) {
                break;
            }
            was[min] = true;
            for (int i = 0; i < edges[min].size(); i++) {
                Edge e = edges[min].get(i);
                if (e.cap == e.flow) {
                    continue;
                }
                long val = d[e.from] + h[e.from] - h[e.to] + e.cost;
                if (d[e.to] > val) {
                    d[e.to] = val;
                    lastEdge[e.to] = e;
                }
            }
        }
    }


    void dfs(int v, boolean[] was, List<Integer> topSort) {
        was[v] = true;
        for (Edge e : edges[v]) {
            if (e.cap == e.flow || was[e.to]) {
                continue;
            }
            dfs(e.to, was, topSort);
        }
        topSort.add(v);
    }

    public long[] getMinCostFlowAcyclic(int source, int target) {
        long[] h = getPotentialsAcyclic();
        Edge[] lastEdge = new Edge[n];
        long[] d = new long[n];
        int flow = 0;
        long cost = 0;
        while (true) {
            dijkstra(source, lastEdge, d, h);
            if (d[target] == Long.MAX_VALUE) {
                break;
            }
            int addFlow = Integer.MAX_VALUE;
            for (int v = target; v != source; ) {
                Edge e = lastEdge[v];
                addFlow = Math.min(addFlow, e.cap - e.flow);
                v = e.from;
            }
            cost += (d[target] + h[target] - h[source]) * addFlow;
            flow += addFlow;
            for (int v = target; v != source; ) {
                Edge e = lastEdge[v];
                e.flow += addFlow;
                e.rev.flow -= addFlow;
                v = e.from;
            }
            for (int i = 0; i < n; i++) {
                h[i] += d[i] == Long.MAX_VALUE ? 0 : d[i];
            }
        }
        return new long[]{flow, cost};
    }

    public long[] getMinCostMaxFlow(int source, int target) {
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
        Edge[] lastEdge = new Edge[n];
        long[] d = new long[n];
        int flow = 0;
        long cost = 0;
        while (true) {
            dijkstra(source, lastEdge, d, h);
            if (d[target] == Long.MAX_VALUE) {
                break;
            }
            int addFlow = Integer.MAX_VALUE;
            for (int v = target; v != source; ) {
                Edge e = lastEdge[v];
                addFlow = Math.min(addFlow, e.cap - e.flow);
                v = e.from;
            }
            cost += (d[target] + h[target] - h[source]) * addFlow;
            flow += addFlow;
            for (int v = target; v != source; ) {
                Edge e = lastEdge[v];
                e.flow += addFlow;
                e.rev.flow -= addFlow;
                v = e.from;
            }
            for (int i = 0; i < n; i++) {
                h[i] += d[i] == Long.MAX_VALUE ? 0 : d[i];
            }
        }
        return new long[]{flow, cost};
    }

    void dijkstra(int source, Edge[] lastEdge, final long[] d, long[] h) {
        TreeSet<Integer> ts = new TreeSet<Integer>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                if (d[o1] != d[o2]) {
                    return d[o1] < d[o2] ? -1 : 1;
                }
                return o1 - o2;
            }
        });
        Arrays.fill(d, Long.MAX_VALUE);
        d[source] = 0;
        ts.add(source);
        while (!ts.isEmpty()) {
            int v = ts.pollFirst();
            for (Edge e : edges[v]) {
                if (e.flow >= e.cap) {
                    continue;
                }
                if (d[e.to] == Long.MAX_VALUE
                        || d[e.to] > d[e.from] + e.cost + h[e.from]
                        - h[e.to]) {
                    if (e.cost + h[e.from] - h[e.to] < 0) {
                        throw new AssertionError();
                    }
                    ts.remove(e.to);
                    d[e.to] = d[e.from] + e.cost + h[e.from] - h[e.to];
                    lastEdge[e.to] = e;
                    ts.add(e.to);
                }
            }
        }
    }
}
