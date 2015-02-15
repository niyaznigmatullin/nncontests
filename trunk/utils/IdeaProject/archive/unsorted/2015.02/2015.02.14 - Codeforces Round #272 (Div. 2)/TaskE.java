package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        char[] c = in.next().toCharArray();
        char[] p = in.next().toCharArray();
        int[][] dp = new int[c.length + 1][c.length / p.length + 1];
        for (int[] e : dp) Arrays.fill(e, Integer.MAX_VALUE);
        dp[0][0] = 0;
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (dp[i][j] != Integer.MAX_VALUE)
                    dp[i + 1][j] = dp[i][j];
            }
            int cur = p.length - 1;
            int where = -1;
            for (int j = i; j >= 0; j--) {
                if (p[cur] == c[j]) {
                    --cur;
                }
                if (cur < 0) {
                    where = j;
                    break;
                }
            }
            if (where < 0) continue;
            for (int entries = 1; entries < dp[i + 1].length; entries++) {
                int got = dp[where][entries - 1];
                if (got == Integer.MAX_VALUE) continue;
                dp[i + 1][entries] = Math.min(dp[i + 1][entries], got + (i + 1 - where - p.length));
            }
        }
//        System.out.println(Arrays.deepToString(dp));
        int[] ans = new int[c.length + 1];
        for (int i = 0; i < dp[c.length].length; i++) {
            int cur = dp[c.length][i];
            if (cur == Integer.MAX_VALUE) continue;
            ans[cur] = Math.max(ans[cur], i);
        }
        for (int i = 1; i <= c.length; i++) ans[i] = Math.max(ans[i], ans[i - 1]);
        for (int i = 0; i <= c.length; i++) {
            int left = c.length - i;
            ans[i] = Math.min(ans[i], left / p.length);
        }
        out.printArray(ans);
    }
}
