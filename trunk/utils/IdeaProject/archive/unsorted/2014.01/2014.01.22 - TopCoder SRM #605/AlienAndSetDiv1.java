package coding;

import java.util.Arrays;

public class AlienAndSetDiv1 {

    final int MOD = 1000000007;

    int add(int a, int b) {
        a += b;
        if (a >= MOD) a -= MOD;
        return a;
    }

    public int getNumber(int n, int k) {
        n *= 2;
        int[][] dp = new int[n + 1][1 << k];
        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            int[][] next = new int[n + 1][1 << k];
            for (int have = 0; have <= n; have++) {
                for (int mask = 0; mask < 1 << k; mask++) {
                    int val = dp[have][mask];
                    if (val == 0) continue;
                    if (mask == 0 && have == 0) {
                        next[0][1] = add(next[0][1], val);
                        next[0][1] = add(next[0][1], val);
                    } else {
                        int nHave = have + ((mask >> k - 1) & 1);
                        {
                            int nMask = ((mask << 1) & ((1 << k) - 1)) | 1;
                            next[nHave][nMask] = add(next[nHave][nMask], val);
                        }
                        if (nHave > 0) {
                            --nHave;
                            int nMask = ((mask << 1) & ((1 << k) - 1));
                            next[nHave][nMask] = add(next[nHave][nMask], val);
                        }
                    }
                }
            }
            dp = next;
        }
        return dp[0][0];
    }
}
