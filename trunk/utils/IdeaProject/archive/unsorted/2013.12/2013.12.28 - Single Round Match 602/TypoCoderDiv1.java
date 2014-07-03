package coding;

import java.util.Arrays;

public class TypoCoderDiv1 {
    public int getmax(int[] d, int x) {
        int[][] dp = new int[2200][d.length + 1];
        for (int[] e : dp) {
            Arrays.fill(e, Integer.MIN_VALUE);
        }
        dp[x][0] = 0;
        int ans = Integer.MIN_VALUE;
        for (int j = 0; j < d.length; j++) {
            for (int i = 0; i < dp.length; i++) {
                int val = dp[i][j];
                if (val == Integer.MIN_VALUE) continue;
                int cur = i + d[j];
                if (cur < dp.length) {
                    dp[cur][j + 1] = Math.max(dp[cur][j + 1], val);
                } else {
                    if (j + 1 < d.length) {
                        int cur2 = Math.max(0, cur - d[j + 1]);
                        if (cur2 < dp.length) {
                            dp[cur2][j + 2] = Math.max(dp[cur2][j + 2], val + 2);
                        }
                    } else {
                        ans = Math.max(ans, val + 1);
                    }
                }
                int cur2 = Math.max(i - d[j], 0);
                dp[cur2][j + 1] = Math.max(dp[cur2][j + 1], val);
            }
        }
        for (int i = 0; i < dp.length; i++) {
            ans = Math.max(ans, dp[i][d.length]);
        }
        return ans;
    }
}
