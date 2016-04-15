package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) dp[i][i] = 1;
        for (int i = 0; i + 1 < n; i++) {
            dp[i][i + 1] = (a[i] == a[i + 1] ? 1 : 2);
        }
        for (int len = 2; len < n; len++) {
            for (int i = 0, j = len; j < n; i++, j++) {
                int[] dpi = dp[i];
                dpi[j] = Math.min(dp[i + 1][j], dpi[j - 1]) + 1;
                if (a[i] == a[j]) {
                    dpi[j] = Math.min(dp[i + 1][j - 1], dpi[j]);
                }
                for (int k = i; k < j; k++) {
                    dpi[j] = Math.min(dpi[j], dpi[k] + dp[k + 1][j]);
                }
            }
        }
        out.println(dp[0][n - 1]);
    }
}
