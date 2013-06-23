package coding;

import java.util.Arrays;

public class WallGameDiv1 {
    public int play(String[] costs) {
        int m = costs[0].length();
        int[][] dp = solve(costs);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            ans = Math.min(ans, dp[0][i]);
        }
        return ans;
    }

    static int[][] solve(String[] costs) {
        int n = costs.length;
        int m = costs[0].length();
        int[][] dp = new int[n][m];
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = costs[i].charAt(j) - '0';
            }
        }
        int[][] b = new int[n][m];
        for (int i = 0; i < n; i++) {
            b[i][0] = a[i][0];
            for (int j = 1; j < m; j++) {
                b[i][j] = b[i][j - 1] + a[i][j];
            }
        }
        for (int i = 0; i < m; i++) {
            dp[n - 1][i] = a[n - 1][i];
        }
        int[][][] dp2 = new int[2][m][m];
        for (int i = n - 2; i >= 0; i--) {
            for (int[][] d1 : dp2) {
                for (int[] d2 : d1) {
                    Arrays.fill(d2, Integer.MIN_VALUE);
                }
            }
            for (int len = m - 1; len >= 0; len--) {
                for (int left = 0; left + len < m; left++) {
                    int right = left + len;
                    if (left == 0 && right == m - 1) {
                        continue;
                    }
                    for (int wh = 0; wh < 2; wh++) {
                        int index = wh == 0 ? left - 1 : right + 1;
                        if (index < 0 || index >= m) {
                            continue;
                        }
                        int cur = dp[i + 1][index];
                        int hec = Integer.MAX_VALUE;
                        int nLeft = left;
                        int nRight = right;
                        if (wh == 0) --nLeft;
                        else ++nRight;
                        for (int nWh = 0; nWh < 2; nWh++) {
                            int nIndex = nWh == 0 ? nLeft - 1 : nRight + 1;
                            if (nIndex < 0 || nIndex >= m) {
                                continue;
                            }
                            int add = b[i][Math.max(nIndex, index)];
                            if (Math.min(nIndex, index) > 0) {
                                add -= b[i][Math.min(nIndex, index) - 1];
                            }
                            add -= a[i][index];
                            hec = Math.min(hec, dp2[nWh][nLeft][nRight] + add);
                        }
                        if (hec != Integer.MAX_VALUE) {
                            cur = Math.max(cur, hec);
                        }
                        dp2[wh][left][right] = cur;
                    }
                }
            }
            for (int j = 0; j < m; j++) {
                int best = Integer.MAX_VALUE;
                if (j > 0) {
                    best = Math.min(best, dp2[0][j][j] + a[i][j - 1]);
                }
                if (j + 1 < m) {
                    best = Math.min(best, dp2[1][j][j] + a[i][j + 1]);
                }
                dp[i][j] = a[i][j] + Math.max(dp[i + 1][j], best);
            }
        }
        return dp;
    }
}
