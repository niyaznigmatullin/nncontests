package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;

public class AppleMarket {
    static final int K = 6;

    public static class Edge {
        public int from;
        public int to;
        public long flow;
        public long cap;
        public Edge rev;

        public Edge(int from, int to, long flow, long cap) {
            super();
            this.from = from;
            this.to = to;
            this.flow = flow;
            this.cap = cap;
        }

    }

    static class DinicGraph {


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

        public Edge addEdge(int from, int to, long cap) {
            Edge e1 = new Edge(from, to, 0, cap);
            Edge e2 = new Edge(to, from, 0, 0);
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

        public long dfs(int x, int target, long cMin) {
            if (x == target) {
                return cMin;
            }
            for (int i = cur[x]; i < edges[x].size(); cur[x] = ++i) {
                Edge e = edges[x].get(i);
                if (d[e.to] != d[x] + 1 || e.cap - e.flow == 0) {
                    continue;
                }
                long add = dfs(e.to, target, Math.min(cMin, e.cap - e.flow));
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
                    long add = dfs(source, target, Long.MAX_VALUE);
                    if (add == 0) {
                        break;
                    }
                    flow += add;
                }
            }
            return flow;
        }


    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[][] a = in.readInt2DArray(n, m);
        DinicGraph g = new DinicGraph(k + n * m * K * K + 2);
        int src = k + n * m * K * K;
        int tar = src + 1;
        final long INF = (long) 1e18;
        for (int k1 = 0; k1 < K; k1++) {
            for (int k2 = 0; k2 < K; k2++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        int from = k + getCell(n, m, k1, k2, i, j);
                        if (k1 == 0 && k2 == 0) {
                            g.addEdge(from, tar, a[i][j]);
                        } else if (k1 > 0) {
                            g.addEdge(from, k + getCell(n, m, k1 - 1, k2, i, j), INF);
                            if (i + (1 << (k1 - 1)) < n) {
                                g.addEdge(from, k + getCell(n, m, k1 - 1, k2, i + (1 << (k1 - 1)), j), INF);
                            }
                        } else if (k2 > 0) {
                            g.addEdge(from, k + getCell(n, m, k1, k2 - 1, i, j), INF);
                            if (j + (1 << (k2 - 1)) < m) {
                                g.addEdge(from, k + getCell(n, m, k1, k2 - 1, i, j + (1 << (k2 - 1))), INF);
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < k; i++) {
            int t = in.nextInt() - 1;
            int b = in.nextInt();
            int l = in.nextInt() - 1;
            int r = in.nextInt();
            int x = in.nextInt();
            g.addEdge(src, i, x);
            int k1 = 0;
            int k2 = 0;
            while (1 << (k1 + 1) <= b - t) ++k1;
            while (1 << (k2 + 1) <= r - l) ++k2;
            g.addEdge(i, k + getCell(n, m, k1, k2, t, l), INF);
            g.addEdge(i, k + getCell(n, m, k1, k2, t, r - (1 << k2)), INF);
            g.addEdge(i, k + getCell(n, m, k1, k2, b - (1 << k1), l), INF);
            g.addEdge(i, k + getCell(n, m, k1, k2, b - (1 << k1), r - (1 << k2)), INF);
        }
        out.println(g.getMaxFlow(src, tar));
    }

    private static int getCell(int n, int m, int k1, int k2, int i, int j) {
        return j + m * (i + n * (k2 + K * k1));
    }
}
