package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Random;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
//        test();
        int n = in.nextInt();
        int m = in.nextInt();
        out.println(solve(n, m));
    }

    static void test() {
        Random rand = new Random(58);
        while (true) {
            int n = rand.nextInt(40) + 1;
            int m = rand.nextInt(40) + 1;
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

    static final int MOD = 1000000007;

    static int solveStupid(int n, int m) {
        int[][][][][] dp = new int[m][2][2][n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[0][0][0][i][j] = 1;
            }
        }
        for (int s = 1; s < m; s++) {
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    dp[s][0][0][i][j] = 1;
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    for (int k = 0; k <= j; k++) {
                        for (int l = Math.max(k, i); l < n; l++) {
                            for (int a = 0; a < 2; a++) {
                                for (int b = 0; b < 2; b++) {
                                    if (a == 1 && i < k) continue;
                                    if (b == 1 && j > l) continue;
                                    int na = a == 1 ? 1 : (i > k ? 1 : 0);
                                    int nb = b == 1 ? 1 : (j < l ? 1 : 0);
                                    dp[s][na][nb][i][j] += dp[s - 1][a][b][k][l];
                                    if (dp[s][na][nb][i][j] >= MOD) {
                                        dp[s][na][nb][i][j] -= MOD;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int[][][][] d : dp) {
            for (int[][][] d1 : d) {
                for (int[][] d2 : d1) {
                    for (int[] d3 : d2) {
                        for (int d4 : d3) {
                            ans += d4;
                            if (ans >= MOD) ans -= MOD;
                        }
                    }
                }
            }
        }
        return ans;
    }

    static int solve(int n, int m) {
        int[][][][] dp = new int[2][2][n][n];
        int[][][][] next = new int[2][2][n][n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int a = 0; a < 2; a++) {
                for (int b = 0; b < 2; b++) {
                    makePart(dp[a][b], n, n);
                    for (int[] d : next[a][b]) {
                        Arrays.fill(d, 0);
                    }
                }
            }
            {
                int[][] n00 = next[0][0];
                int[][] n01 = next[0][1];
                int[][] n10 = next[1][0];
                int[][] n11 = next[1][1];
                int[][] d00 = dp[0][0];
                int[][] d11 = dp[1][1];
                int[][] d10 = dp[1][0];
                int[][] d01 = dp[0][1];
                for (int c = 0; c < n; c++) {
                    for (int d = c; d < n; d++) {
                        n00[c][d] = getSum(d00, c, d + 1, c, d + 1);
                        n01[c][d] = getSum(d00, c, d + 1, d + 1, n) + getSum(d01, c, d + 1, d, n);
                        if (n01[c][d] >= MOD) {
                            n01[c][d] -= MOD;
                        }
                        n10[c][d] = getSum(d00, 0, c, c, d + 1) + getSum(d10, 0, c + 1, c, d + 1);
                        if (n10[c][d] >= MOD) {
                            n10[c][d] -= MOD;
                        }
                        int z = getSum(d00, 0, c, d + 1, n) + getSum(d01, 0, c, d, n);
                        if (z >= MOD) {
                            z -= MOD;
                        }
                        z += getSum(d10, 0, c + 1, d + 1, n);
                        if (z >= MOD) {
                            z -= MOD;
                        }
                        z += getSum(d11, 0, c + 1, d, n);
                        if (z >= MOD) {
                            z -= MOD;
                        }
                        n11[c][d] = z;
                    }
                }
            }
            int[][] n00 = next[0][0];
            for (int c = 0; c < n; c++) {
                for (int d = c; d < n; d++) {
                    n00[c][d]++;
                    if (n00[c][d] >= MOD) {
                        n00[c][d] -= MOD;
                    }
                }
            }
            for (int a = 0; a < 2; a++) {
                for (int b = 0; b < 2; b++) {
                    int[][] nab = next[a][b];
                    for (int c = 0; c < n; c++) {
                        for (int d = 0; d < n; d++) {
                            ans += nab[c][d];
                            if (ans >= MOD) {
                                ans -= MOD;
                            }
                        }
                    }
                }
            }
            int[][][][] t = dp;
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
