package mypackage;

import math.MathUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskB {

    static final int MOD = 7340033;
    static int[][] C;

    static {
        C = new int[9000][5000];
        for (int i = 0; i < C.length; i++) {
            int[] ci = C[i];
            ci[0] = 1;
            for (int j = 1; j <= i && j < ci.length; j++) {
                ci[j] = C[i - 1][j - 1] + C[i - 1][j];
                if (ci[j] >= MOD) {
                    ci[j] -= MOD;
                }
            }
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        if (k > n) {
            out.println(0);
            return;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int j = i - k;
            for (int z = 0; z <= j; z++) {
                dp[i] += (int) ((long) dp[z] * C[j - z + k - 1][k - 1] % MOD);
                if (dp[i] >= MOD) {
                    dp[i] -= MOD;
                }
            }
        }
        out.println(dp[n]);
    }
}
