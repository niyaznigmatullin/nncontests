package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }
        double[][] dist = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int dx = x[i] - x[j];
                int dy = y[i] - y[j];
                dist[i][j] = Math.sqrt(dx * dx + dy * dy);
            }
        }
        double[][] dp = new double[n][n];
        for (int len = 3; len < n; len++) {
            for (int i = 0; i < n; i++) {
                int j = (i + len) % n;
                double ans = Double.POSITIVE_INFINITY;
                for (int k = (i + 2) % n; k != j; k = (k + 1) % n) {
                    ans = Math.min(ans, dist[i][k] + dist[j][k] + dp[i][k] + dp[k][j]);
                }
                int next = (i + 1) % n;
                int prev = (i + len - 1) % n;
                ans = Math.min(ans, dist[next][j] + dp[next][j]);
                ans = Math.min(ans, dist[i][prev] + dp[i][prev]);
                dp[i][j] = ans;
            }
        }
        double ans = Double.POSITIVE_INFINITY;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, dp[(i + 1) % n][i]);
        }
        out.println(ans);
    }
}
