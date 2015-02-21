package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Choco {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int w = in.nextInt();
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = in.nextInt();
            }
        }
        int[][] b = a.clone();
        for (int i = 0; i < n; i++) b[i] = b[i].clone();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) b[i][j] += b[i][j - 1];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) b[i][j] += b[i - 1][j];
        }

        int[][][][][] dp = new int[2][n][m][n][m];
        int[][][][] dSecond = dp[1];
        int[][][][] dFirst = dp[0];
        for (int len1 = 1; len1 <= n; len1++) {
            for (int len2 = 1; len2 <= m; len2++) {
                for (int i1 = 0; i1 + len1 <= n; i1++) {
                    int i2 = i1 + len1 - 1;
                    for (int j1 = 0; j1 + len2 <= m; j1++) {
                        int j2 = j1 + len2 - 1;
                        if (len1 == 1 && len2 == 1) {
                            dFirst[i1][j1][i1][j1] = dSecond[i1][j1][i1][j1] = a[i1][j1];
                        } else {
                            int first = Integer.MIN_VALUE;
                            int second = Integer.MAX_VALUE;
                            for (int i3 = i1 + 1; i3 <= i2; i3++) {
                                int sum = getSum(b, i1, j1, i3 - 1, j2);
                                int sum2 = getSum(b, i3, j1, i2, j2);
                                if (sum <= w) {
                                    first = Math.max(first, sum + dSecond[i3][j1][i2][j2]);
                                    second = Math.min(second, dFirst[i3][j1][i2][j2]);
                                }
                                if (sum2 <= w) {
                                    first = Math.max(first, sum2 + dSecond[i1][j1][i3 - 1][j2]);
                                    second = Math.min(second, dFirst[i1][j1][i3 - 1][j2]);
                                }
                            }
                            for (int j3 = j1 + 1; j3 <= j2; j3++) {
                                int sum = getSum(b, i1, j1, i2, j3 - 1);
                                int sum2 = getSum(b, i1, j3, i2, j2);
                                if (sum <= w) {
                                    first = Math.max(first, sum + dSecond[i1][j3][i2][j2]);
                                    second = Math.min(second, dFirst[i1][j3][i2][j2]);
                                }
                                if (sum2 <= w) {
                                    first = Math.max(first, sum2 + dSecond[i1][j1][i2][j3 - 1]);
                                    second = Math.min(second, dFirst[i1][j1][i2][j3 - 1]);
                                }
                            }
                            dFirst[i1][j1][i2][j2] = first == Integer.MIN_VALUE ? getSum(b, i1, j1, i2, j2) : first;
                            dSecond[i1][j1][i2][j2] = second == Integer.MAX_VALUE ? getSum(b, i1, j1, i2, j2) : second;
                        }
                    }
                }
            }
        }
        int ans = dp[0][0][0][n - 1][m - 1];
        out.println(ans + " " + (b[n - 1][m - 1] - ans));
    }

    static int getSum(int[][] a, int x1, int y1, int x2, int y2) {
        int ret = a[x2][y2];
        if (x1 > 0) ret -= a[x1 - 1][y2];
        if (y1 > 0) ret -= a[x2][y1 - 1];
        if (x1 > 0 && y1 > 0) ret += a[x1 - 1][y1 - 1];
        return ret;
    }
}
