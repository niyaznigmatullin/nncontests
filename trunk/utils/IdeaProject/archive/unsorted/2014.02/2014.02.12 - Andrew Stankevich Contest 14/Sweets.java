package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;
import java.util.Arrays;

public class Sweets {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        n -= k;
        BigInteger[][] dp = new BigInteger[n + 1][k + 1];
        for (BigInteger[] d : dp) {
            Arrays.fill(d, BigInteger.ZERO);
        }
        Arrays.fill(dp[0], BigInteger.ONE);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i][j - 1];
                if (i - j >= 0) {
                    dp[i][j] = dp[i][j].add(dp[i - j][j]);
                }
            }
        }
        out.println(dp[n][k]);
    }
}
