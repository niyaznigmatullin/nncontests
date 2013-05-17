package coding;

import ru.ifmo.niyaz.graphalgorithms.GraphUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class F {

    static int[][] edges;
    static double[] down, dp;
    static double[] pa, pb, pc;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        pa = new double[n];
        pb = new double[n];
        pc = new double[n];
        for (int i = 0; i < n; i++) {
            pa[i] = in.nextInt() * .01;
            pb[i] = in.nextInt() * .01;
            pc[i] = in.nextInt() * .01;
        }
        int[] from = new int[n - 1];
        int[] to = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
        }
        edges = GraphUtils.getEdgesUndirectedUnweighted(n, from, to);
        down = new double[n];
        dfs(0, -1);
        dp = new double[n];
        dfs2(0, -1, 1);
        double ans = 0;
        for (int i = 0; i < n; i++) {
            ans += dp[i];
        }
        out.println(ans);
    }

    static void dfs2(int v, int p, double top) {
        double[] a = new double[edges[v].length + 1];
        double[] b = new double[edges[v].length + 1];
        a[0] = 1;
        b[edges[v].length] = 1;
        for (int i = 0; i < edges[v].length; i++) {
            int e = edges[v][i];
            if (e == p) {
                a[i + 1] = 1;
                b[i] = 1;
            } else {
                a[i + 1] = down[e];
                b[i] = down[e];
            }
        }
        for (int i = 1; i < a.length; i++) {
            a[i] *= a[i - 1];
        }
        for (int i = b.length - 2; i >= 0; i--) {
            b[i] *= b[i + 1];
        }
        dp[v] = (down[v] - pa[v]) * top;
        for (int i = 0; i < edges[v].length; i++) {
            int e = edges[v][i];
            if (e == p) {
                continue;
            }
            dfs2(e, v, top * a[i] * b[i + 1] * pc[v] + pa[v]);
        }
    }

    static void dfs(int v, int p) {
        double cur = 1;
        for (int i : edges[v]) {
            if (i == p) {
                continue;
            }
            dfs(i, v);
            cur *= down[i];
        }
        cur *= pc[v];
        cur += pa[v];
        down[v] = cur;
    }
}
