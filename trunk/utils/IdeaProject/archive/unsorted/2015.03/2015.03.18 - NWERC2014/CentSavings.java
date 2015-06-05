package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class CentSavings {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int d = in.nextInt() + 1;
        int[] a = in.readIntArray(n);
        int[][][] dp = new int[n + 1][10][d];
        for (int[][] d1 : dp) for (int[] d2 : d1) Arrays.fill(d2, Integer.MAX_VALUE);
        dp[0][0][0] = 0;
        for (int i = 0; i < n; i++) {
            for (int mod = 0; mod < 10; mod++) {
                for (int q = 0; q < d; q++) {
                    int val = dp[i][mod][q];
                    if (val == Integer.MAX_VALUE) continue;
                    {
                        int nMod = mod + a[i] % 10;
                        int nCost = val + a[i] / 10;
                        if (nMod >= 10) {
                            nMod -= 10;
                            nCost++;
                        }
                        dp[i + 1][nMod][q] = Math.min(dp[i + 1][nMod][q], nCost);
                    }
                    if (q + 1 < d) {
                        int nMod = a[i] % 10;
                        int nCost = val + (mod + 5) / 10 + a[i] / 10;
                        dp[i + 1][nMod][q + 1] = Math.min(dp[i + 1][nMod][q + 1], nCost);
                    }
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int mod = 0; mod < 10; mod++) {
            int add = (mod + 5) / 10;
            for (int q = 0; q < d; q++) {
                int val = dp[n][mod][q];
                if (val == Integer.MAX_VALUE) continue;
                ans = Math.min(ans, val + add);
            }
        }
        out.println(ans * 10);
    }
}
