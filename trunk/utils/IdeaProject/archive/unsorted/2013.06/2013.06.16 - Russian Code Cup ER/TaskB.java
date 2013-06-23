package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[] a = in.readIntArray(n);
        double[] dp = new double[n];
        for (int i = 0; i < n; i++) {
            dp[i] = k;
        }
        int[][] per = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                per[i][j] = in.nextInt();
            }
        }
        for (int i = 0; i < m; i++) {
            double maximal = 0;
            for (int j = 0; j < n; j++) {
                dp[j] *= (100 + per[j][i]) * .01;
                maximal = Math.max(maximal, Math.max(0, dp[j] - a[j]));
            }
            for (int j = 0; j < n; j++) {
                dp[j] = Math.max(dp[j], Math.max(0, maximal - a[j]));
            }
        }
        double ans = 0;
        for (double e : dp) ans = Math.max(ans, e);
        out.println(ans);
    }
}
