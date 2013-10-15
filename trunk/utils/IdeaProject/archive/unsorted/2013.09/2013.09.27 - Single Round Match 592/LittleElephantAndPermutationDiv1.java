package coding;

import java.util.Arrays;

public class LittleElephantAndPermutationDiv1 {
    public int getNumber(int n, int k) {
        final int MOD = 1000000007;
        int all = 0;
        for (int i = 0; i < n; i++) {
            all += 2 * i;
        }
        k -= n;
        int[][][] dp = new int[n + 1][n + 1][all + 1];
        int[][][] next = new int[n + 1][n + 1][all + 1];
        dp[0][0][0] = 1;
        int ss = 0;
        for (int i = 0; i < n; i++) {
            for (int[][] d1 : next) {
                for (int[] d2 : d1) {
                    Arrays.fill(d2, 0);
                }
            }
            for (int out = 0; out <= i; out++) {
                for (int in = 0; in <= i; in++) {
                    for (int sum = 0; sum <= ss; sum++) {
                        int val = dp[out][in][sum];
                        if (val == 0) continue;
                        {
                            next[out][in][sum + i] += val;
                            if (next[out][in][sum + i] >= MOD) {
                                next[out][in][sum + i] -= MOD;
                            }
                        }
                        {

                            next[out + 1][in + 1][sum] = val + next[out + 1][in + 1][sum];
                            if (next[out + 1][in + 1][sum] >= MOD) {
                                next[out + 1][in + 1][sum] -= MOD;
                            }
                        }
                        if (out > 0) {
                            next[out][in][sum + i] = (int) ((next[out][in][sum + i] + (long) out * val) % MOD);
                        }
                        if (in > 0) {
                            next[out][in][sum + i] = (int) ((next[out][in][sum + i] + (long) in * val) % MOD);
                        }
                        if (out > 0 && in > 0) {
                            next[out - 1][in - 1][sum + i + i] = (int) ((next[out - 1][in - 1][sum + i + i] + (long) in * out * val) % MOD);
                        }
                    }
                }
            }
            ss += i * 2;
            int[][][] t = dp;
            dp = next;
            next = t;
        }
        int ans = 0;
        for (int i = 0; i <= all; i++) {
            if (i >= k) {
                ans = (ans + dp[0][0][i]) % MOD;
            }
        }
        for (int i = 2; i <= n; i++) {
            ans = (int) (((long) ans * i) % MOD);
        }
        return ans;
    }
}
