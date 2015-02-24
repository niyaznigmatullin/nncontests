package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        long[][] dp = new long[n][m];
        int[][] t = new int[n][m];
        for (int i = 0; i < n; i++) {
            t[i] = in.readIntArray(m);
        }
        long[] ans = new long[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                long prev1 = i > 0 ? dp[i - 1][j] : 0;
                long prev2 = j > 0 ? dp[i][j - 1] : 0;
                dp[i][j] = Math.max(prev1, prev2) + t[i][j];
                ans[i] = Math.max(ans[i], dp[i][j]);
            }
        }
        out.printArray(ans);
    }
}
