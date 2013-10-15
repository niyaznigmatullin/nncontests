package lib.test.on2013_06.on2013_06_08_IPSC_2013.K2;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class K2 {
    static long[] dp = new long[100001];
    static final int MOD = 1000000009;
    static long[] z = {0, 1, 2, 3, 5};

    static {
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j <= 4; j++) {
                if (i - j >= 0) {
                    dp[i] = (dp[i] + dp[i - j] * z[j]) % MOD;
                }
            }
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        out.println(dp[n]);
    }
}
