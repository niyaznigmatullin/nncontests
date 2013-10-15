package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class K1 {
    static long[] dp = new long[100001];
    static long[] dp2 = new long[100001];
    static final int MOD = 1000000009;

    static {
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        }
        dp2[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j <= 4; j++) {
                if (i - j >= 0) {
                    dp2[i] += dp2[i - j];
                    dp2[i] %= MOD;
                }
            }
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        out.println(dp[n] * dp2[n] % MOD);
    }
}
