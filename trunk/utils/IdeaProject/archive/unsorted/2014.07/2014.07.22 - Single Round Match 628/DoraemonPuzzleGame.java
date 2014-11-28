package coding;

import java.util.Arrays;

public class DoraemonPuzzleGame {
    public double solve(int[] X, int[] Y, int m) {
        int n = X.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Y[i] > Y[j]) {
                    int t = X[i];
                    X[i] = X[j];
                    X[j] = t;
                    t = Y[i];
                    Y[i] = Y[j];
                    Y[j] = t;
                }
            }
        }
        m -= n;
        double[] dp = new double[m + 1];
        double[] dp2 = new double[m + 1];
        dp2[0] = 1;
        for (int i = 0; i < n; i++) {
            double[] next = new double[m + 1];
            double[] next2 = new double[m + 1];
            double e2 = 0;
            double e1 = 0;
            {
                double p = (1000 - Y[i]) * .001;
                e2 = 1 / (p - 1) / (p - 1);
            }
            {
                double p = (1000 - X[i] - Y[i]) * .001;
                e1 = 1 / (p - 1) / (p - 1);
            }
            for (int done = 0; done <= m; done++) {
                double val = dp[done];
                double valP = dp2[done];
                if (valP == 0) continue;
                if (done + n - i - 1 < m) {
                    double p2 = Y[i] * .001;
                    next[done + 1] += val + valP * p2 * e2;
                    next2[done + 1] += valP;
                } else {
                    double p1 = X[i] * .001;
                    double p2 = Y[i] * .001;
                    next[Math.min(m, done + 1)] += val * p2 / (p1 + p2) + valP * e1 * p2;
                    next2[Math.min(m, done + 1)] += valP * p2 / (p1 + p2);
                    next[done] += val * p1 / (p1 + p2) + e1 * p1 * valP;
                    next2[done] += valP * p1 / (p1 + p2);
                }
            }
            dp = next;
            dp2 = next2;
        }
        return dp[m];
    }
}
