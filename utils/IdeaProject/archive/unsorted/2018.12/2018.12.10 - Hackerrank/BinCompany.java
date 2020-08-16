package coding;

import ru.ifmo.niyaz.DataStructures.FenwickMod;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class BinCompany {

    static class Edge {
        int from;
        int to;
        int w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }

    static List<Edge>[] edges;
    static int[] depth;
    static int T;
    static int[] enter, exit;
    static final int MOD = 1000000007;

    static void dfs(int v, int pv, int d) {
        depth[v] = d;
        enter[v] = T++;
        for (Edge e : edges[v]) {
            if (e.to == pv) continue;
            dfs(e.to, v, (d + e.w) % MOD);
        }
        exit[v] = T;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) edges[i] = new ArrayList<>();
        checkRange(n, 1, 100000);
        for (int i = 0; i < n - 1; i++) {
            int v = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            checkRange(v, 0, n - 1);
            checkRange(u, 0, n - 1);
            int w = in.nextInt();
            checkRange(w, 0, 1000000000);
            edges[v].add(new Edge(v, u, w));
            edges[u].add(new Edge(u, v, w));
        }
        T = 0;
        enter = new int[n];
        exit = new int[n];
        depth = new int[n];
        dfs(0, -1, 0);
        FenwickMod fSum = new FenwickMod(n, MOD);
        FenwickMod fCount = new FenwickMod(n, MOD);
        for (int i = 0; i < q; i++) {
            int type = in.nextInt();
            int v = in.nextInt() - 1;
            checkRange(type, 1, 3);
            checkRange(v, 0, n - 1);
            if (type == 1 || type == 2) {
                int d = in.nextInt();
                checkRange(d, 0, 1000000000);
                if (type == 2) {
                    d = -d;
                }
                fSum.add(enter[v], (int) ((long) depth[v] * d % MOD));
                fCount.add(enter[v], d);
            } else if (type == 3) {
                int sum = fSum.getSum(enter[v], exit[v]);
                int count = fCount.getSum(enter[v], exit[v]);
                sum -= (long) count * depth[v] % MOD;
                if (sum < 0) sum += MOD;
                out.println(sum);
            } else throw new AssertionError();
        }
    }

    static void checkRange(int n, int left, int right) {
        if (n < left || n > right) throw new AssertionError();
    }
}
