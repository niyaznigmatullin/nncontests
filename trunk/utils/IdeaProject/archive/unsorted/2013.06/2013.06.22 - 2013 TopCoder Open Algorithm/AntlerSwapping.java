package coding;

import java.util.Arrays;

public class AntlerSwapping {
    public int getmin(int[] antler1, int[] antler2, int capacity) {
        int n = antler1.length;
        int[][] a = new int[2][n];
        for (int i = 0; i < n; i++) {
            a[0][i] = antler1[i];
            a[1][i] = antler2[i];
        }
        boolean[] can = new boolean[1 << n];
        int[] b = new int[2 * n];
        for (int i = 0; i < 1 << n; i++) {
            int cn = 0;
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    b[cn++] = a[0][j];
                    b[cn++] = a[1][j];
                }
            }
            if (cn < 3) continue;
            Arrays.sort(b, 0, cn);
            boolean ok = true;
            for (int j = 0; j < cn; j += 2) {
                if (b[j + 1] - b[j] > capacity) {
                    ok = false;
                    break;
                }
            }
            can[i] = ok;
        }
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        for (int i = 0; i < 1 << n; i++) {
            int mask = ((1 << n) - 1) ^ i;
            if (dp[i] == Integer.MIN_VALUE) {
                continue;
            }
            int val = dp[i] + 1;
            for (int subMask = mask; subMask > 0; subMask = (subMask - 1) & mask) {
                if (can[subMask]) {
                    dp[i | subMask] = Math.max(dp[i | subMask], val);
                }
            }
        }
//        System.out.println(Arrays.toString(dp));
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 1 << n; i++) {
            if (dp[i] == Integer.MIN_VALUE) continue;
            boolean ok = true;
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 0 && Math.abs(a[0][j] - a[1][j]) > capacity) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                int cur = Integer.bitCount(i) - dp[i];
                if (ans > cur) {
                    ans = cur;
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
