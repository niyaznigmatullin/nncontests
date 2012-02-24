package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.math.BigInteger;
import java.util.Arrays;

public class Brackets {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        if (n == 0 && k == 0) {
            throw new UnknownError();
        }
        out.println("Case " + testNumber + ": " + get(n, k).subtract(get(n, k - 1)));
        out.println();
	}

    static BigInteger get(int n, int k) {
        BigInteger[][] dp = new BigInteger[2 * n + 1][k + 1];
        for (BigInteger[] d : dp) {
            Arrays.fill(d, BigInteger.ZERO);
        }
        dp[0][0] = BigInteger.ONE;
        for (int i = 1; i <= 2 * n; i++) {
            for (int j = 0; j <= k; j++) {
                if (j > 0) {
                    dp[i][j] = dp[i][j].add(dp[i - 1][j - 1]);
                }
                if (j + 1 <= k) {
                    dp[i][j] = dp[i][j].add(dp[i - 1][j + 1]);
                }
            }
        }
        return dp[2 * n][0];
    }
}
