package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;

public class Data {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int levels = in.nextInt();
        init(n);
        int source = -1;
        int target = -1;
        for (int i = 0; i < n; i++) {
            int d = in.nextInt();
            if (d == 1) {
                source = i;
            } else if (d == levels) {
                target = i;
            }
        }
        Edge[] edges = new Edge[m];
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int c = in.nextInt();
            edges[i] = addEdge(from, to, 0, c);
        }
        getBlockingFlow(source, target);
        for (int i = 0; i < m; i++) {
            out.println(edges[i].flow);
        }
    }


    public static class Edge {
        int from;
        int to;
        public int flow;
        public int cap;
        boolean removed;

        public Edge(int from, int to, int flow, int cap) {
            super();
            this.from = from;
            this.to = to;
            this.flow = flow;
            this.cap = cap;
        }

    }

    ArrayList<Edge>[] edges;
    ArrayList<Edge>[] revEdges;
    int n;

    @SuppressWarnings({"unchecked"})
    void init(int n) {
        edges = new ArrayList[n];
        revEdges = new ArrayList[n];
        this.n = n;
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new ArrayList<Edge>();
            revEdges[i] = new ArrayList<Edge>();
        }
    }

    public Edge addEdge(int from, int to, int flow, int cap) {
        Edge e1 = new Edge(from, to, flow, cap);
        edges[from].add(e1);
        revEdges[to].add(e1);
        return e1;
    }

    int[] incoming;
    int[] outcoming;
    int[] phi;

    public long getBlockingFlow(int source, int target) {
        incoming = new int[n];
        outcoming = new int[n];
        phi = new int[n];
        removed = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (Edge e : edges[i]) {
                incoming[e.to] += e.cap - e.flow;
                outcoming[e.from] += e.cap - e.flow;
            }
        }
        countPhi(source, target);
        int[] cur = new int[n];
        int[] revCur = new int[n];
        int[] q = new int[n];
        int[] flowCame = new int[n];
        boolean[] was = new boolean[n];
        long flow = 0;
        while (true) {
            int minimal = -1;
            for (int i = 0; i < n; i++) {
                if (was[i]) {
                    continue;
                }
                if (minimal < 0 || phi[i] < phi[minimal]) {
                    minimal = i;
                }
            }
            if (minimal < 0) {
                break;
            }
//            System.err.println(minimal);
            was[minimal] = true;
            Arrays.fill(flowCame, 0);
            flowCame[minimal] = phi[minimal];
            flow += phi[minimal];
            {
                int head = 0;
                int tail = 0;
                q[tail++] = minimal;
                while (head < tail) {
                    int v = q[head++];
                    for (int i = cur[v]; i < edges[v].size(); cur[v] = ++i) {
                        Edge e = edges[v].get(i);
                        if (e.removed || e.cap == e.flow) {
                            continue;
                        }
                        int go = Math.min(flowCame[v], e.cap - e.flow);
                        if (flowCame[e.to] == 0) {
                            q[tail++] = e.to;
                        }
                        saturate(e, go);
                        flowCame[e.to] += go;
                        flowCame[v] -= go;
                        if (flowCame[v] == 0) {
                            break;
                        }
                    }
                }
            }
            flowCame[minimal] = phi[minimal];
            {
                int head = 0;
                int tail = 0;
                q[tail++] = minimal;
                while (head < tail) {
                    int v = q[head++];
                    for (int i = revCur[v]; i < revEdges[v].size(); revCur[v] = ++i) {
                        Edge e = revEdges[v].get(i);
                        if (e.removed || e.cap == e.flow) {
                            continue;
                        }
                        int go = Math.min(flowCame[v], e.cap - e.flow);
                        flowCame[v] -= go;
                        if (flowCame[e.from] == 0) {
                            q[tail++] = e.from;
                        }
                        saturate(e, go);
                        flowCame[e.from] += go;
                        if (flowCame[v] == 0) {
                            break;
                        }
                    }
                }
            }
            remove(minimal);
            countPhi(source, target);
        }
        return flow;
    }

    private void countPhi(int source, int target) {
        for (int i = 0; i < n; i++) {
            if (i == source) {
                phi[i] = outcoming[i];
            } else if (i == target) {
                phi[i] = incoming[i];
            } else {
                phi[i] = Math.min(incoming[i], outcoming[i]);
            }
        }
//        System.err.println(Arrays.toString(phi));
//        System.err.println(Arrays.toString(incoming));
//        System.err.println(Arrays.toString(outcoming));
    }

    boolean[] removed;

    void remove(int v) {
        for (Edge e : edges[v]) {
            remove(e);
        }
        for (Edge e : revEdges[v]) {
            remove(e);
        }
    }

    void remove(Edge e) {
        if (e.removed) {
            return;
        }
        e.removed = true;
        incoming[e.to] -= e.cap - e.flow;
        outcoming[e.from] -= e.cap - e.flow;
    }

    void saturate(Edge e, int z) {
//        System.err.println("SAT " + e.from + " " + e.to + " " + z);
        if (e.cap - e.flow == z) {
            remove(e);
            e.flow = e.cap;
        } else {
            e.flow += z;
            incoming[e.to] -= z;
            outcoming[e.from] -= z;
        }
    }
}
