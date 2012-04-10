package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = in.nextInt();
            }
        }
        int[][] b = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                b[i][j] = a[i][j];
                if (j > 0) {
                    b[i][j] += b[i][j - 1];
                }
                if (i > 0) {
                    b[i][j] += b[i - 1][j];
                }
                if (i > 0 && j > 0) {
                    b[i][j] -= b[i - 1][j - 1];
                }
            }
        }
        int[][] dp1 = new int[n][m];
        int[][] dp2 = new int[n][m];
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp1[i][j] = a[i][j];
            }
        }
        for (int i = 0; i + 2 < n; i++) {
            for (int j = 0; j + 2 < m; j++) {
                dp2[i][j] = sum(b, i, j, i, j + 2) + sum(b, i + 2, j, i + 2, j + 2) + a[i + 1][j + 2];
                ans = Math.max(ans, dp2[i][j]);
            }
        }
        for (int size = 5; size <= Math.max(n, m) + 1; size += 2) {
            int[][] next = new int[n][m];
            for (int i = 0; i + size <= n; i++) {
                for (int j = 0; j + size <= m; j++) {
                    next[i][j] = dp1[i + 2][j + 2] + sum(b, i, j, i, j + size - 1) + sum(b, i + size - 1, j, i + size - 1, j + size - 1)
                            + sum(b, i + 2, j, i + size - 2, j) + sum(b, i + 1, j + size - 1, i + size - 2, j + size - 1) + a[i + 2][j + 1];
                    ans = Math.max(ans, next[i][j]);
                }
            }
            dp1 = dp2;
            dp2 = next;
        }
        out.println(ans);
    }

    static int sum(int[][] a, int x1, int y1, int x2, int y2) {
        int ret = a[x2][y2];
        if (x1 > 0) {
            ret -= a[x1 - 1][y2];
        }
        if (y1 > 0) {
            ret -= a[x2][y1 - 1];
        }
        if (x1 > 0 && y1 > 0) {
            ret += a[x1 - 1][y1 - 1];
        }
        return ret;
    }
}
