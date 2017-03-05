package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] t = in.readIntArray(n);
        int prev1440 = 0;
        int prev90 = 0;
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            while (t[prev1440] + 1440 <= t[i]) ++prev1440;
            while (t[prev90] + 90 <= t[i]) ++prev90;
            dp[i + 1] = dp[i] + 20;
            if (dp[prev1440] + 120 < dp[i + 1]) {
                dp[i + 1] = dp[prev1440] + 120;
            }
            if (dp[prev90] + 50 < dp[i + 1]) {
                dp[i + 1] = dp[prev90] + 50;
            }
            out.println(dp[i + 1] - dp[i]);
        }
    }
}
