package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        double p = in.nextDouble();
        int t = in.nextInt();
        double[][] dp = new double[t + 1][n + 1];
        dp[0][0] = 1.;
        for (int i = 0; i < t; i++) {
            for (int j = 0; j <= n; j++) {
                double val = dp[i][j];
                if (val == 0) continue;
                if (j == n) {
                    dp[i + 1][j] += val;
                } else {
                    dp[i + 1][j + 1] += val * p;
                    dp[i + 1][j] += val * (1 - p);
                }
            }
        }
        double ans = 0;
        for (int i = 0; i <= n; i++) {
            ans += dp[t][i] * i;
        }
        out.println(ans);
    }
}
