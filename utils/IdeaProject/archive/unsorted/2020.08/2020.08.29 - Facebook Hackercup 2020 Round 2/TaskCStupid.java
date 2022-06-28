package coding;

import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskCStupid {
    static int[] readArray(FastScanner in, int n, int k, int mod) {
        int[] ret = new int[n];
        for (int i = 0; i < k; i++) {
            ret[i] = in.nextInt();
        }
        long a = in.nextInt();
        long b = in.nextInt();
        int c = in.nextInt();
        for (int i = k; i < n; i++) {
            ret[i] = (int) ((ret[i - 2] * a + ret[i - 1] * b + c) % mod);
        }
        return ret;
    }

    static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static long mst(int n, Edge[] edges) {
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        Arrays.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.weight, o2.weight);
            }
        });
        long ans = 0;
        for (Edge e : edges) {
            if (dsu.union(e.from, e.to)) {
                ans += e.weight;
            }
        }
        return ans;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println("Test #" + testNumber);
        int n = in.nextInt();
        int m = in.nextInt();
        int events = in.nextInt();
        int k = in.nextInt();
        int[] x = readArray(in, n, k, m);
        int[] y = readArray(in, n, k, m);
        int[] vx = readArray(in, events, k, n * m + n);
        int[] w = readArray(in, events, k, 1000000000);
        int[] weights = new int[n * m + n];
        Arrays.fill(weights, 1);
        long ans = 1;
        final int MOD = 1000000007;
        for (int cq = 0; cq < events; cq++) {
            weights[vx[cq]] = w[cq];
            Edge[] edges = new Edge[n * m + n];
            for (int i = 0; i < n * m; i++) {
                int circle = i / m;
                int inside = i % m;
                edges[i] = new Edge(circle * m + inside, circle * m + (inside + 1) % m, weights[i]);
            }
            for (int i = 0; i < n; i++) {
                edges[n * m + i] = new Edge(y[i] + i * m, x[(i + 1) % n] + (i + 1) % n * m, weights[n * m + i]);
            }
            ans = ans * (mst(n * m, edges) % MOD) % MOD;
        }
        out.println("Case #" + testNumber + ": " + ans);
    }
}
