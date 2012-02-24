package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class Dominoes {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        long[][] dp = new long[n + 1][1 << 2 * n];
        long[] answer = new long[1 << 2 * n];
        int allMask = (1 << 2 * n) - 1;
        answer[allMask] = 1;
        for (int i = 0; i < m; i++) {
            for (long[] d : dp) {
                Arrays.fill(d, 0);
            }
            dp[0] = answer.clone();
            for (int j = 0; j < n; j++) {
                for (int mask = 0; mask <= allMask; mask++) {
                    if (dp[j][mask] == 0) {
                        continue;
                    }
                    if (((mask >> (n - 1)) & 1) == 0 && ((mask >> 2 * n - 1) & 1) == 0) {
                        dp[j + 1][allMask & ((mask << 1) | (1 << n) | 1)] += dp[j][mask];
                    }
                    if (j + 3 <= n && ((mask >> (2 * n - 1)) & 1) == 1 && ((mask >> (2 * n - 2)) & 1) == 1
                            && ((mask >> (2 * n - 3)) & 1) == 1) {
                        dp[j + 3][allMask & ((mask << 3) | 7)] += dp[j][mask];
                    }
                    if (((mask >> (2 * n - 1)) & 1) == 1) {
                        dp[j + 1][allMask & (mask << 1)] += dp[j][mask];
                    }
                }
            }
            answer = dp[n].clone();
        }
        out.println(answer[(1 << 2 * n) - 1]);
    }
}
