package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int r = in.nextInt();
        int s = in.nextInt();
        int p = in.nextInt();
        double[][][] dp = new double[r + 1][s + 1][p + 1];
        dp[r][s][p] = 1.;
        double ans1 = 0;
        double ans2 = 0;
        double ans3 = 0;
        for (int i = r; i >= 0; i--) {
            for (int j = s; j >= 0; j--) {
                for (int k = p; k >= 0; k--) {
                    int all = i * j + j * k + i * k;
                    if (i == 0 && j == 0) ans3 += dp[i][j][k];
                    if (i == 0 && k == 0) ans2 += dp[i][j][k];
                    if (j == 0 && k == 0) ans1 += dp[i][j][k];
                    if (all == 0) continue;
                    if (i > 0) {
                        dp[i - 1][j][k] += dp[i][j][k] * i * k / all;
                    }
                    if (j > 0) {
                        dp[i][j - 1][k] += dp[i][j][k] * i * j / all;
                    }
                    if (k > 0) {
                        dp[i][j][k - 1] += dp[i][j][k] * j * k / all;
                    }
                }
            }
        }
        out.println(ans1 + " " + ans2 + " " + ans3);
    }
}
