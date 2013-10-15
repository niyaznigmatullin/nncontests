package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Attack {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int a = in.nextInt() - 1;
        int b = in.nextInt() - 1;
        DinicGraph g = new DinicGraph(n);
        for (int i = 0; i < m; i++) {
            int v = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            int c = in.nextInt();
            g.addUndirectedEdge(v, u, c);
        }
        long ans = g.getMaxFlow(a, b);
        boolean[] cut = g.getCut(a, b);
        boolean[] cut2 = g.getCut2(a, b);
        out.println(Arrays.equals(cut, cut2) ? "UNIQUE" : "AMBIGUOUS");
    }

    static class DinicGraph {

        public static class Edge {
            public int from;
            public int to;
            public int flow;
            public int cap;
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
            cur = new int[n];
            q = new int[n];
            d = new int[n];
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

        public List<List<Edge>> decompose(int source, int target) {
            List<List<Edge>> ret = new ArrayList<List<Edge>>();
            while (true) {
                List<Edge> list = new ArrayList<Edge>();
                if (getPath(source, target, list, Integer.MAX_VALUE) == 0) {
                    break;
                }
                Collections.reverse(list);
                ret.add(list);
            }
            return ret;
        }

        int getPath(int v, int t, List<Edge> list, int cMin) {
            if (v == t) {
                return cMin;
            }
            for (Edge e : edges[v]) {
                if (e.flow > 0) {
                    int got = getPath(e.to, t, list, Math.min(cMin, e.flow));
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

        public boolean[] getCut2(int source, int target) {
            int head = 0;
            int tail = 0;
            q[tail++] = target;
            boolean[] ret = new boolean[n];
            ret[target] = true;
            while (head < tail) {
                int v = q[head++];
                for (Edge f : edges[v]) {
                    Edge e = f.rev;
                    if (ret[e.from]) continue;
                    if (e.cap - e.flow > 0) {
                        q[tail++] = e.from;
                        ret[e.from] = true;
                    }
                }
            }
            for (int i = 0; i < n; i++) ret[i] = !ret[i];
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

}
