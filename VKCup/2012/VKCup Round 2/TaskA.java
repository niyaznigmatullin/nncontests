package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskA {

    static final int MOD = 1000000007;

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        char[] c1 = in.next().toCharArray();
        char[] c2 = in.next().toCharArray();
        int[][] dp = new int[c1.length + 1][c2.length + 1];
        for (int i = 0; i <= c1.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i <= c1.length; i++) {
            for (int j = 0; j <= c2.length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (j > 0) {
                    dp[i][j] += dp[i][j - 1];
                    if (dp[i][j] >= MOD) {
                        dp[i][j] -= MOD;
                    }
                }
                if (i > 0 && j > 0 && c1[i - 1] == c2[j - 1]) {
                    dp[i][j] += dp[i - 1][j - 1];
                    if (dp[i][j] >= MOD) {
                        dp[i][j] -= MOD;
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= c1.length; i++) {
            ans += dp[i][c2.length];
            if (ans >= MOD) {
                ans -= MOD;
            }
        }
        out.println((ans + MOD - c1.length) % MOD);
	}
}
