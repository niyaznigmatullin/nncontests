package ru.ifmo.niyaz.graphalgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 14.01.12
 * Time: 17:48
 * To change this template use File | Settings | File Templates.
 */
public class DinicGraph {

    public static class Edge {
        public int from;
        public int to;
        public int flow;
        public int cap;
        public int preflow;
        public Edge rev;

        public Edge(int from, int to, int flow, int cap) {
            super();
            this.from = from;
            this.to = to;
            this.flow = flow;
            this.cap = cap;
        }

    }

    public ArrayList<Edge>[] edges;
    int[] cur;
    int[] q;
    public int[] d;
    int n;

    public DinicGraph(int n) {
        edges = new ArrayList[n];
        this.n = n;
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new ArrayList<Edge>();
        }
        q = new int[n];
        d = new int[n];
        cur = new int[n];
    }

    public Edge addEdge(int from, int to, int cap) {
        Edge e1 = new Edge(from, to, 0, cap);
        Edge e2 = new Edge(to, from, 0, 0);
        e1.rev = e2;
        e2.rev = e1;
        edges[from].add(e1);
        edges[to].add(e2);
        return e1;
    }

    public Edge addUndirectedEdge(int from, int to, int cap) {
        Edge e1 = new Edge(from, to, 0, cap);
        Edge e2 = new Edge(to, from, 0, cap);
        e1.rev = e2;
        e2.rev = e1;
        edges[from].add(e1);
        edges[to].add(e2);
        return e1;
    }

    boolean bfs(int source, int target) {
        int head = 0;
        int tail = 1;
        Arrays.fill(d, Integer.MAX_VALUE);
        d[source] = 0;
        q[0] = source;
        while (head < tail) {
            int x = q[head++];
            for (int i = 0; i < edges[x].size(); i++) {
                Edge e = edges[x].get(i);
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

    public int dfs(int x, int target, int cMin) {
        if (x == target) {
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
            return add;
        }
        return 0;
    }

    public long getMaxFlow(int source, int target) {
        long flow = 0;
        while (bfs(source, target)) {
            Arrays.fill(cur, 0);
            while (true) {
                int add = dfs(source, target, Integer.MAX_VALUE);
                if (add == 0) {
                    break;
                }
                flow += add;
            }
        }
        return flow;
    }

    public long findBlockingFlowPreflow(int source, int target, boolean[] blocked, long[] excess) {
        long flow = 0;
        Arrays.fill(blocked, false);
        Arrays.fill(cur, 0);
        Arrays.fill(excess, 0);
        excess[source] = Long.MAX_VALUE;
        while (true) {
            boolean changed = false;
            for (int it = 0; it < n; it++) {
                int v = q[it];
                if (excess[v] == 0 || blocked[v]) continue;
                pushFlow(blocked, excess, v, true);
                if (excess[v] > 0) {
                    if (v == target) {
                        flow += excess[v];
                    } else {
                        blocked[v] = true;
                        changed = true;
                        cur[v] = 0;
                    }
                }
            }
            if (!changed) {
                break;
            }
            for (int it = n - 1; it >= 0; it--) {
                int v = q[it];
                if (excess[v] == 0 || !blocked[v]) continue;
                pushFlow(blocked, excess, v, false);
                if (v != source && excess[v] > 0) throw new AssertionError();
            }
        }
        return flow;
    }

    private void pushFlow(boolean[] blocked, long[] excess, int v, boolean forward) {
        for (int i = cur[v]; i < edges[v].size(); i = ++cur[v]) {
            Edge e = edges[v].get(i);
            if (d[e.from] == Integer.MAX_VALUE || d[e.to] != d[e.from] + (forward ? 1 : -1) || (forward && blocked[e.to]) ||
                    (forward ? (e.cap == e.flow + e.preflow) : e.preflow >= 0)) {
                continue;
            }
            long push = Math.min(excess[v], (forward ? e.cap - e.flow - e.preflow : -e.preflow));
            e.preflow += push;
            e.rev.preflow -= push;
            excess[e.to] += push;
            excess[e.from] -= push;
            if (excess[v] == 0) break;
        }
    }

    public long getMaxFlowPreflow(int source, int target) {
        long flow = 0;
        boolean[] blocked = new boolean[n];
        long[] excess = new long[n];
        if (bfs(source, target)) {
            flow += findBlockingFlowPreflow(source, target, blocked, excess);
            for (int v = 0; v < n; v++) if (d[v] != Integer.MAX_VALUE) {
                for (int i = 0; i < edges[v].size(); i++) {
                    Edge e = edges[v].get(i);
                    e.flow += e.preflow;
                    e.preflow = 0;
                }
            }
        }
        return flow;
    }

    public List<List<Edge>> decompose(int source, int target) {
        List<List<Edge>> ret = new ArrayList<List<Edge>>();
        boolean[] was = new boolean[n];
        while (true) {
            List<Edge> list = new ArrayList<Edge>();
            Arrays.fill(was, false);
            if (getPath(source, target, list, Integer.MAX_VALUE, was) == 0) {
                break;
            }
            Collections.reverse(list);
            ret.add(list);
        }
        return ret;
    }

    int getPath(int v, int t, List<Edge> list, int cMin, boolean[] was) {
        if (v == t) {
            return cMin;
        }
        was[v] = true;
        for (Edge e : edges[v]) {
            if (was[e.to]) continue;
            if (e.flow > 0) {
                int got = getPath(e.to, t, list, Math.min(cMin, e.flow), was);
                if (got == 0) {
                    continue;
                }
                list.add(e);
                e.flow -= got;
                return got;
            }
        }
        return 0;
    }

    public boolean[] getCut(int source, int target) {
        if (bfs(source, target)) {
            return null;
        }
        boolean[] ret = new boolean[n];
        for (int i = 0; i < n; i++) {
            ret[i] = d[i] != Integer.MAX_VALUE;
        }
        return ret;
    }

    public void clear() {
        for (List<Edge> f : edges) {
            for (Edge e : f) {
                e.flow = 0;
            }
        }
    }


}
