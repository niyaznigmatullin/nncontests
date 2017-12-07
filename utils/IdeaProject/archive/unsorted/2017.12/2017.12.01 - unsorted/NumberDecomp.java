package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;

public class NumberDecomp {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        String s = in.next();
        BigInteger[][] dp = new BigInteger[n + 1][k + 1];
        dp[0][0] = BigInteger.ZERO;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (s.charAt(j) == '0' && i - j > 1) continue;
                for (int e = 1; e <= k; e++) {
                    BigInteger from = dp[j][e - 1];
                    if (from == null) continue;
                    dp[i][e] = (dp[i][e] == null ? BigInteger.ZERO : dp[i][e]).max(dp[j][e - 1].add(new BigInteger(s.substring(j, i))));
                }
            }
        }
        out.println(dp[n][k]);
    }
}
