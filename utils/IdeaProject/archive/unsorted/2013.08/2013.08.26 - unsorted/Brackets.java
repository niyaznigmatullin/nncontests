package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;
import java.util.Arrays;

public class Brackets {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int test = 0;
        while (true) {
            ++test;
            int n = in.nextInt();
            int k = in.nextInt();
            if (n == 0 && k == 0) break;
            out.print("Case " + test + ": ");
            out.println(get(n, k).subtract(get(n, k - 1)));
            out.println();
        }
    }

    static BigInteger get(int n, int k) {
        BigInteger[] dp = new BigInteger[k + 1];
        Arrays.fill(dp, BigInteger.ZERO);
        BigInteger[] next = new BigInteger[k + 1];
        dp[0] = BigInteger.ONE;
        for (int i = 0; i < 2 * n; i++) {
            for (int j = 0; j <= k; j++) {
                next[j] = BigInteger.ZERO;
                if (j > 0) next[j] = next[j].add(dp[j - 1]);
                if (j + 1 <= k) next[j] = next[j].add(dp[j + 1]);
            }
            BigInteger[] t = next;
            next = dp;
            dp = t;
        }
        return dp[0];
    }
}
