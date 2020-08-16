package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TaskB3 {
    public static class DinicGraph {

        public static class Edge {
            public int from;
            public int to;
            public long flow;
            public long cap;
            public Edge rev;

            public Edge(int from, int to, int flow, long cap) {
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

        public Edge addEdge(int from, int to, long cap) {
            Edge e1 = new Edge(from, to, 0, cap);
            Edge e2 = new Edge(to, from, 0, 0);
            e1.rev = e2;
            e2.rev = e1;
            edges[from].add(e1);
            edges[to].add(e2);
            return e1;
        }

        public Edge addUndirectedEdge(int from, int to, long cap) {
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


    static class Ship {
        int x;
        int a;
        int f;
        int p;

        public Ship(int x, int a, int f, int p) {
            this.x = x;
            this.a = a;
            this.f = f;
            this.p = p;
        }
    }

    static class Base {
        int x;
        int d;
        int g;

        public Base(int x, int d, int g) {
            this.x = x;
            this.d = d;
            this.g = g;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] a = new int[n][n];
        for (int[] e : a) Arrays.fill(e, Integer.MAX_VALUE);
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            a[from][to] = a[to][from] = 1;
        }
        for (int i = 0; i < n; i++) {
            a[i][i] = 0;
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i][k] != Integer.MAX_VALUE && a[k][j] != Integer.MAX_VALUE) {
                        a[i][j] = Math.min(a[i][j], a[i][k] + a[k][j]);
                    }
                }
            }
        }
        int s = in.nextInt();
        int b = in.nextInt();
        int k = in.nextInt();
        Ship[] ships = new Ship[s];
        for (int i = 0; i < s; i++) {
            ships[i] = new Ship(in.nextInt() - 1, in.nextInt(), in.nextInt(), in.nextInt());
        }
        Base[] bases = new Base[b];
        for (int i = 0; i < b; i++) {
            bases[i] = new Base(in.nextInt() - 1, in.nextInt(), in.nextInt());
        }
        Arrays.sort(bases, new Comparator<Base>() {
            @Override
            public int compare(Base o1, Base o2) {
                return Integer.compare(o1.g, o2.g);
            }
        });
        List<Base>[] bestInVertex = new List[n];
        for (int i = 0; i < n; i++) bestInVertex[i] = new ArrayList<>();
        for (Base e : bases) {
            int v = e.x;
            while (bestInVertex[v].size() > 0 && bestInVertex[v].get(bestInVertex[v].size() - 1).d >= e.d) {
                bestInVertex[v].remove(bestInVertex[v].size() - 1);
            }
            bestInVertex[v].add(e);
        }
        DinicGraph g = new DinicGraph(s + 2);
        long INF = 1L << 60;
        long MAX = 1L << 40;
        int src = s;
        int tar = src + 1;
        for (int i = 0; i < s; i++) {
            Ship e = ships[i];
            int best = Integer.MIN_VALUE;
            for (int v = 0; v < n; v++) {
                if (a[e.x][v] > e.f) continue;
                int left = -1;
                List<Base> list = bestInVertex[v];
                int right = list.size();
                while (left < right - 1) {
                    int mid = left + right >>> 1;
                    if (list.get(mid).d > e.a) {
                        right = mid;
                    } else {
                        left = mid;
                    }
                }
                if (left >= 0) {
                    best = Math.max(best, list.get(left).g);
                }
            }
            if (best == Integer.MIN_VALUE) {
                g.addEdge(i, tar, INF);
                g.addEdge(src, i, MAX);
            } else {
                g.addEdge(i, tar, MAX + (e.p - best));
                g.addEdge(src, i, MAX);
            }
        }
        for (int i = 0; i < k; i++) {
            int s1 = in.nextInt() - 1;
            int s2 = in.nextInt() - 1;
            g.addEdge(s1, s2, INF);
        }
        long have = g.getMaxFlow(src, tar);
        out.println(-(have - MAX * s));
    }
}
