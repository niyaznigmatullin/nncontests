package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;

public class B1 {
    static BigInteger[] dp = new BigInteger[1000001];
    static {
        dp[1] = BigInteger.ZERO;
        for (int i = 2; i < dp.length; i++){
            if (i % 3 == 0) {
                dp[i] = dp[i / 3].add(dp[2 * i / 3]).add(BigInteger.valueOf(i / 3).multiply(BigInteger.valueOf(2 * i / 3)));
            } else if (i % 2 == 0) {
                dp[i] = dp[i / 2].add(dp[i / 2]).add(BigInteger.valueOf(i / 2).multiply(BigInteger.valueOf(i / 2)));
            } else {
                dp[i] = dp[1].add(dp[i - 1]).add(BigInteger.valueOf(i - 1));
            }
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        out.println(dp[n]);
    }
}
