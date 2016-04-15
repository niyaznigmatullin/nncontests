package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class CarnivalCoins {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println(testNumber);
        int n = in.nextInt();
        int k = in.nextInt();
        double p = in.nextDouble();
        double[][] dp = new double[n + 1][k + 1];
        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                double val = dp[i][j];
                if (val < 1e-50) continue;
                dp[i + 1][Math.min(j + 1, k)] += val * p;
                dp[i + 1][j] += val * (1 - p);
            }
        }
        double[] f = new double[n + 1];
        f[0] = 0.;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                f[i] = Math.max(f[i], f[i - j] + dp[j][k]);
            }
        }
        out.println("Case #" + testNumber + ": " + f[n]);
    }
}
