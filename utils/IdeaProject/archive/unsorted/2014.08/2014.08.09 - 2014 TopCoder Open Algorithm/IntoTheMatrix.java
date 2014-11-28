package coding;

import java.util.Arrays;

public class IntoTheMatrix {
    public int takePills(int turns, int n) {
        int m = 30;
        int[][] c = new int[m + 1][m + 1];
        for (int i = 0; i < c.length; i++) {
            c[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
            }
        }
        int[][] dp = new int[turns + 1][m + 1];
        Arrays.fill(dp[0], 1);
        for (int cur = 1; cur <= turns; cur++) {
            dp[cur][0] = 1;
            for (int men = 1; men <= m; men++) {
                for (int j = 0; j <= men; j++) {
                    int z = c[men][j];
                    dp[cur][men] = add(dp[cur][men], mul(z, dp[cur - 1][men - j]));
                }
            }
        }
        int ans = 0;
        while (dp[turns][ans] < n) ++ans;
        return ans;
    }

    static int mul(int a, int b) {
        if ((long) a * b > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        return a * b;
    }

    static int add(int a, int b) {
        if (Integer.MAX_VALUE - a < b) return Integer.MAX_VALUE;
        return a + b;
    }
}
