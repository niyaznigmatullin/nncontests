package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int p = in.nextInt();
        int sum = 0;
        for (int i : a) {
            sum += i;
        }
        if (sum <= p) {
            out.println(n);
            return;
        }
        double[] fact = new double[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i;
        }
        double ans = 0;
        for (int last = 0; last < n; last++) {
            double[][] dp = new double[n][p + 1];
            dp[0][0] = 1;
            for (int i = 0; i < n; i++) {
                if (i == last) {
                    continue;
                }
                int w = a[i];
                for (int j = n - 2; j >= 0; j--) {
                    for (int k = 0; k + w <= p; k++) {
                        dp[j + 1][k + w] += dp[j][k];
                    }
                }
            }
            for (int w = 0; w <= p; w++) {
                if (w + a[last] > p) {
                    for (int cnt = 0; cnt < n; cnt++) {
                        ans += fact[cnt] * fact[n - cnt - 1] * dp[cnt][w] * cnt;
                    }
                }
            }
        }
        out.println(ans / fact[n]);
    }
}
