package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Random;

public class TaskD {
    static void test() {
        Random rand = new Random(12342L);
        while (true) {
            int n = rand.nextInt(120) + 1;
            int m = rand.nextInt(120) + 1;
            int ans1 = solve(n, m);
            int ans2 = solveStupid(n, m);
            if (ans1 != ans2) {
                System.err.println(n + " " + m);
                System.err.println(ans1 + " " + ans2);
                throw new AssertionError();
            }
            System.err.println("OK");
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
//        test();
        int n = in.nextInt();
        int m = in.nextInt();
        out.println(solve(n, m));
//        if (solve(n, m) != solveStupid(n, m)) {
//            throw new AssertionError();
//        }
    }

    static final int MOD = 1000000007;

    static int solve(int n, int m) {
        int[][] dp = new int[m][n + 1];
        int[][] dp2 = new int[m][n + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int s = 0;
            int ss = 0;
            dp[0][i] = 1;
            for (int j = 1; j < m; j++) {
                dp2[j][i] = (ss + s + 1) % MOD;
                s += dp[j][i - 1];
                if (s >= MOD) {
                    s -= MOD;
                }
                ss += s;
                if (ss >= MOD) {
                    ss -= MOD;
                }
                dp[j][i] = (ss + 1) % MOD;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int cur = 0;
            for (int j = 1; j < m; j++) {
                cur = (int) ((cur + (long) dp[j][i + 1] * dp2[j][n - i] % MOD * (m - j)) % MOD);
            }
            ans += cur;
            if (ans >= MOD) {
                ans -= MOD;
            }
        }
        return ans;
    }

    static int solveStupid(int m, int n) {
        int[][][] dp = new int[2][n][n];
        int[][][] next = new int[2][n][n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                ans += m;
                if (ans >= MOD) {
                    ans -= MOD;
                }
                dp[0][i][j] = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int a = 0; a < 2; a++) {
                makePart(dp[a], n, n);
                for (int[] d : next[a]) {
                    Arrays.fill(d, 0);
                }
            }
            {
                for (int c = 0; c < n; c++) {
                    for (int d = c + 1; d < n; d++) {
                        next[0][c][d] = getSum(dp[0], c, d + 1, c, d + 1);
                        int dd = getSum(dp[1], 0, c + 1, d, n);
                        dd += getSum(dp[0], 0, c + 1, d + 1, n);
                        if (dd >= MOD) {
                            dd -= MOD;
                        }
                        dd += getSum(dp[0], 0, c, d, n);
                        if (dd >= MOD) {
                            dd -= MOD;
                        }
                        dd -= getSum(dp[0], 0, c, d + 1, n);
                        if (dd < 0) {
                            dd += MOD;
                        }
                        next[1][c][d] = dd;
                    }
                }
            }
            for (int a = 0; a < 2; a++) {
                int[][] nab = next[a];
                for (int c = 0; c < n; c++) {
                    for (int d = c + 1; d < n; d++) {
                        ans = (int) ((ans + (long) nab[c][d] * (m - i)) % MOD);
                    }
                }
            }
            int[][][] t = dp;
            dp = next;
            next = t;
        }
        return ans;
    }

    static void makePart(int[][] a, int b, int c) {
        for (int i = 1; i < b; i++) {
            for (int j = 0; j < c; j++) {
                a[i][j] += a[i - 1][j];
                if (a[i][j] >= MOD) {
                    a[i][j] -= MOD;
                }
            }
        }
        for (int i = 0; i < b; i++) {
            for (int j = 1; j < c; j++) {
                a[i][j] += a[i][j - 1];
                if (a[i][j] >= MOD) {
                    a[i][j] -= MOD;
                }
            }
        }
    }

    static int getSum(int[][] a, int x1, int x2, int y1, int y2) {
        if (x1 >= x2 || y1 >= y2) return 0;
        int ret = a[x2 - 1][y2 - 1];
        if (x1 > 0) {
            ret -= a[x1 - 1][y2 - 1];
            if (ret < 0) ret += MOD;
        }
        if (y1 > 0) {
            ret -= a[x2 - 1][y1 - 1];
            if (ret < 0) ret += MOD;
        }
        if (x1 > 0 && y1 > 0) {
            ret += a[x1 - 1][y1 - 1];
            if (ret >= MOD) ret -= MOD;
        }
        return ret;
    }
}