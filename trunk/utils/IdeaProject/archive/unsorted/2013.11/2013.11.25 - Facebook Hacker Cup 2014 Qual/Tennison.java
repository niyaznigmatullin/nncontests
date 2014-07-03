package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Locale;

public class Tennison {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println("[" + testNumber + "]");
        out.print("Case #" + testNumber + ": ");
        int k = in.nextInt();
        double ps = in.nextDouble();
        double pr = in.nextDouble();
        double p0 = in.nextDouble();
        int d1 = (int) Math.round(in.nextDouble() * 1000);
        double p1 = in.nextDouble();
        int d2 = (int) Math.round(in.nextDouble() * 1000);
        double p2 = in.nextDouble();
        double[][][] dp = new double[1001][k + 1][k + 1];
        dp[(int) Math.round(p0 * 1000)][0][0] = 1;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                for (int p = 0; p <= 1000; p++) {
                    double val = dp[p][i][j];
                    if (val == 0) {
                        continue;
                    }
                    double curP = p * .001;
                    double win = ps * curP + pr * (1 - curP);
                    double lose = (1 - ps) * curP + (1 - pr) * (1 - curP);
                    dp[p][i + 1][j] += win * (1 - p1) * val;
                    dp[p][i][j + 1] += lose * (1 - p2) * val;
                    dp[Math.min(1000, p + d1)][i + 1][j] += win * p1 * val;
                    dp[Math.max(0, p - d2)][i][j + 1] += lose * p2 * val;
                }
            }
        }
        double ans = 0;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j <= 1000; j++) {
                ans += dp[j][k][i];
            }
        }
        out.printf(Locale.US, "%.6f\n", ans);
    }
}
