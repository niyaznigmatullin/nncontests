package coding;

import java.util.Arrays;

public class WheelofFortune {

    public double maxExpectedValue(int n, int[] s) {
        int k = s.length;
        double[] f = new double[k + 1];
        for (int pos = 1; pos < n; pos++) {
            double[] dp = new double[k + 1];
            double[] pp = new double[k + 1];
            pp[0] = 1.;
            for (int i : s) {
                double[] next = new double[k + 1];
                double[] nextP = new double[k + 1];
                double none = 1. * (Math.max(0, pos - i) + Math.max(0, n - pos - i)) / n;
                double onlyOne = 1. * Math.max(0, Math.min(0, pos - i) - Math.max(0 - i + 1, pos - n + 1) + 1) / n;
                double both = 1. - none - 2 * onlyOne;
                for (int j = 0; j < k; j++) {
                    double val = pp[j];
                    if (val == 0) continue;
                    next[j + 1] += dp[j] * onlyOne;
                    nextP[j + 1] += val * onlyOne;
                    next[j + 1] += (dp[j] * both) + val * both;
                    nextP[j + 1] += val * both;
                    next[j] += dp[j] * none;
                    nextP[j] += val * none;
                    next[j] += dp[j] * onlyOne + val * onlyOne;
                    nextP[j] += val * onlyOne;
                }
                dp = next;
                pp = nextP;
            }
            for (int i = 0; i <= k; i++) {
                if (pp[i] != 0)
                    f[i] = Math.max(f[i], dp[i] / pp[i]);
            }
        }
        double ans = 0;
        double[] ff = new double[k + 1];
        ff[0] = 1.;
        for (int i : s) {
            double[] next = new double[k + 1];
            for (int j = 0; j < k; j++) {
                next[j + 1] += ff[j] * i / n;
                next[j] += ff[j] * (n - i) / n;
            }
            ff = next;
        }
        for (int i = 0; i <= k; i++) {
            ans += i * ff[i] + ff[i] * f[i];
        }
        return ans;
    }
}
