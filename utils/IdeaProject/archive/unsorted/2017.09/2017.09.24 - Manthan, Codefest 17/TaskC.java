package coding;

import ru.ifmo.niyaz.graphalgorithms.GraphUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

import java.util.Arrays;

public class TaskC {

    static int[][] g;
    static int m;
    static int k;
    static int x;

    static final int MOD = 1000000007;

    static int add(int a, int b) {
        a += b;
        if (a >= MOD) a -= MOD;
        return a;
    }

    static int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }

    static int[][][] dp;

    static int any(int v, int e) {
        return add(add(dp[0][v][e], dp[1][v][e]), dp[2][v][e]);
    }

    static int less(int v, int e) {
        return dp[0][v][e];
    }

    static int nonequal(int v, int e) {
        return add(dp[0][v][e], dp[2][v][e]);
    }

    static void dfs(int v, int pv) {
        for (int to : g[v]) {
            if (to == pv) continue;
            dfs(to, v);
        }
        int[] less = new int[x + 1];
        less[0] = k - 1;
        int[] nless = new int[x + 1];
        int[] nequal = new int[x + 1];
        int[] ngreater = new int[x + 1];
        int[] equal = new int[x + 1];
        equal[1] = 1;
        int[] greater = new int[x + 1];
        greater[0] = m - k;
        for (int to : g[v]) {
            if (to == pv) continue;
            Arrays.fill(nless, 0);
            Arrays.fill(ngreater, 0);
            Arrays.fill(nequal, 0);
            for (int have = x; have >= 0; have--) {
                int value = less[have];
                if (value == 0) continue;
                for (int get = 0; get + have <= x; get++) {
                    nless[get + have] = add(nless[get + have], mul(value, any(to, get)));
                }
            }
            for (int have = x; have >= 0; have--) {
                int value = equal[have];
                if (value == 0) continue;
                for (int get = 0; get + have <= x; get++) {
                    nequal[get + have] = add(nequal[get + have], mul(value, less(to, get)));
                }
            }
            for (int have = x; have >= 0; have--) {
                int value = greater[have];
                if (value == 0) {
                    continue;
                }
                for (int get = 0; get + have <= x; get++) {
                    ngreater[get + have] = add(ngreater[get + have], mul(value, nonequal(to, get)));
                }
            }
            {
                int[] t = nequal;
                nequal = equal;
                equal = t;
            }
            {
                int[] t = nless;
                nless = less;
                less = t;
            }
            {
                int[] t = ngreater;
                ngreater = greater;
                greater = t;
            }
        }
        dp[0][v] = less;
        dp[1][v] = equal;
        dp[2][v] = greater;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        m = in.nextInt();
        int[] from = new int[n - 1];
        int[] to = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
        }
        g = GraphUtils.getEdgesUndirectedUnweighted(n, from, to);
        k = in.nextInt();
        x = in.nextInt();
        dp = new int[3][n][];
        dfs(0, -1);
        int ans = 0;
        for (int i = 0; i <= x; i++) {
            ans = add(ans, any(0, i));
        }
        out.println(ans);
    }
}
