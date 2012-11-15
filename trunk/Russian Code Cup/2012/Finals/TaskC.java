package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt() << 1;
        char[][] c = new char[n][];
        for (int i = 0; i < n; i++) {
            c[i] = in.next().toCharArray();
        }
        int[][] edges = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                edges[i][j] = edges[j][i] = getIt(c[i], c[j]);
            }
        }
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < 1 << n; i++) {
            int j = Integer.numberOfTrailingZeros(~i);
            for (int k = j + 1; k < n; k++) {
                if (((i >> k) & 1) == 1) {
                    continue;
                }
                int newMask = i ^ (1 << j) ^ (1 << k);
                dp[newMask] = Math.min(dp[newMask], Math.max(dp[i], edges[j][k]));
            }
        }
        out.println(dp[(1 << n) - 1]);
    }

    static int getIt(char[] c, char[] d) {
        int m = d.length;
        int n = c.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int[] e : dp) {
            Arrays.fill(e, Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i < n && j < m && c[i] == d[j]) {
                    dp[i + 1][j + 1] = Math.min(dp[i + 1][j + 1], dp[i][j] + 1);
                }
                if (i < n) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }
                if (j < m) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }
            }
        }
        return dp[n][m];
    }
}
