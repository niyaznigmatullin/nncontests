package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskF {
    static final int MOD = 998244353;

    static int add(int a, int b) {
        a += b;
        if (a >= MOD) a -= MOD;
        return a;
    }

    static int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }

    static List<Integer>[] edges;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) edges[i] = new ArrayList<>();
        int[] deg = new int[n];
        for (int i = 1; i < n; i++) {
            int p = in.nextInt() - 1;
            edges[p].add(i);
            deg[p]++;
        }
        int[] dp = new int[n];
        int[] dpHave = new int[n];
        for (int v = n - 1; v >= 0; v--) {
            if (edges[v].size() == 0) {
                dp[v] = 1;
                dpHave[v] = 1;
                continue;
            }
            int z = 1;
            int y = 1;
            int[] b = new int[edges[v].size()];
            int[] c = new int[edges[v].size()];
            for (int it = 0; it < edges[v].size(); it++) {
                int j = edges[v].get(it);
                b[it] = dp[j];
                c[it] = dpHave[j];
            }
            int[] d = b.clone();
            for (int it = 0; it < b.length; it++) {
                int cur = b[it];
                z = mul(z, cur);
                cur = add(cur, c[it]);
                y = mul(y, cur);
            }
            for (int i = 1; i < d.length; i++) {
                d[i] = mul(d[i], d[i - 1]);
            }
            int x = 0;
            int rightC = 1;
            for (int i = d.length - 1; i >= 0; i--) {
                int cur = mul(c[i], rightC);
                if (i > 0) {
                    cur = mul(cur, d[i - 1]);
                }
                x = add(x, cur);
                rightC = mul(rightC, b[i]);
            }
            dp[v] = y;
            dp[v] = add(dp[v], MOD - x);
            dpHave[v] = y;
            dpHave[v] = add(dpHave[v], MOD - z);
        }
        out.println(dp[0]);
    }
}
