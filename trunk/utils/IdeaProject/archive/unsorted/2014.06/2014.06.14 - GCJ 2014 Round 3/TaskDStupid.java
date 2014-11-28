package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskDStupid {
    static int[] c;
    static int[][] a;
    static int start1, start2;
    static int[][][][] dp;
    static List<Integer>[] edges;

    static int go(int who, int finished, int v, int u) {
        if (finished == 3) return 0;
        if (dp[who][finished][v][u] != Integer.MIN_VALUE) {
            return dp[who][finished][v][u];
        }
        if (who == 0) {
            int ret = Integer.MIN_VALUE;
            int add = 0;
            if (a[start2][v] + a[u][v] != a[start2][u] || (v == u && (finished & 2) == 0)) {
                add = c[v];
            }
            if ((finished & 1) == 0) {
                ret = Math.max(ret, go(1, finished | 1, v, u) + add);
                for (int to : edges[v]) {
                    if (a[start1][to] + 1 == a[start1][v]) continue;
                    if (a[start2][v] + 1 + a[to][u] == a[start2][u] || a[start2][to] + 1 + a[v][u] == a[start2][u]) {
                        continue;
                    }
                    ret = Math.max(ret, go(1, finished, to, u) + add);
                }
            } else {
                ret = Math.max(ret, go(1, finished | 1, v, u));
            }
            return dp[who][finished][v][u] = ret;
        } else {
            int ret = Integer.MAX_VALUE;
            int add = 0;
            if (a[start1][u] + a[u][v] != a[start1][v] || (v == u && (finished & 1) == 0)) {
                add = -c[u];
            }
            if ((finished & 2) == 0) {
                ret = Math.min(ret, go(0, finished | 2, v, u) + add);
                for (int to : edges[u]) {
                    if (a[start2][to] + 1 == a[start2][u]) continue;
                    if (a[start1][u] + 1 + a[to][v] == a[start1][v] || a[start1][to] + 1 + a[u][v] == a[start1][v]) {
                        continue;
                    }
                    ret = Math.min(ret, go(0, finished, v, to) + add);
                }
            } else {
                ret = Math.min(ret, go(0, finished | 2, v, u));
            }
            return dp[who][finished][v][u] = ret;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println("[" + testNumber + "]");
        int n = in.nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) edges[i] = new ArrayList<>();
        c = new int[n];
        for (int i = 0; i < n; i++) {
            c[i] = in.nextInt();
        }
        a = new int[n][n];
        for (int[] d : a) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) a[i][i] = 0;
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            a[i][u] = a[u][i] = 1;
            edges[i].add(u);
            edges[u].add(i);
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i][k] != Integer.MAX_VALUE && a[k][j] != Integer.MAX_VALUE)
                        a[i][j] = Math.min(a[i][j], a[i][k] + a[k][j]);
                }
            }
        }
        int ans = Integer.MIN_VALUE;
        for (start1 = 0; start1 < n; start1++) {
            int cur = Integer.MAX_VALUE;
            for (start2 = 0; start2 < n; start2++) {
                dp = new int[2][4][n][n];
                for (int[][][] d1 : dp) {
                    for (int[][] d2 : d1) {
                        for (int[] d3 : d2)
                            Arrays.fill(d3, Integer.MIN_VALUE);
                    }
                }
                cur = Math.min(cur, go(0, 0, start1, start2));
            }
            ans = Math.max(ans, cur);
        }
        out.println("Case #" + testNumber + ": " + ans);
    }
}
