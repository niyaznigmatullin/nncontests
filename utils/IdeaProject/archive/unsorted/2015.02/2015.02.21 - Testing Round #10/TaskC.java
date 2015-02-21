package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int shift = 15;
        int maxn = 60;
        
        int[][][] dp = new int[maxn + 1][maxn + 1][shift * 2 + 1];
        for (int[][] e1 : dp) {
            for (int[] e2 : e1) Arrays.fill(e2, Integer.MAX_VALUE);
        }
        dp[maxn][maxn][shift] = 0;
        char[] d = ("000" + in.next()).toCharArray();
        for (int i = d.length - 1; i >= 0; i--) {
            int digit = d[i] - '0';
            for (int plus = maxn; plus >= 0; plus--) {
                for (int minus = maxn; minus >= 0; minus--) {
                    for (int carry = 0; carry <= 2 * shift; carry++) {
                        if (plus + 1 <= maxn)
                            dp[plus][minus][carry] = Math.min(dp[plus][minus][carry], dp[plus + 1][minus][carry]);
                        if (minus + 1 <= maxn)
                            dp[plus][minus][carry] = Math.min(dp[plus][minus][carry], dp[plus][minus + 1][carry]);
                    }
                }
            }
            int[][][] next = new int[maxn + 1][maxn + 1][shift * 2 + 1];
            for (int[][] e1 : next) {
                for (int[] e2 : e1) Arrays.fill(e2, Integer.MAX_VALUE);
            }
            for (int plus = maxn; plus >= 0; plus--) {
                for (int minus = maxn; minus >= 0; minus--) {
                    for (int carry = 0; carry <= 2 * shift; carry++) {
                        int val = dp[plus][minus][carry];
                        if (val == Integer.MAX_VALUE) continue;
                        int realCarry = carry - shift;
                        int curN = plus - minus + realCarry;
                        int curD = curN % 10;
                        if (curD < 0) curD += 10;
                        int newCarry = (curN - curD) / 10;
                        if (curD != digit) continue;
                        next[plus][minus][newCarry + shift] = Math.min(next[plus][minus][newCarry + shift], plus + minus + val);
                    }
                }
            }
            dp = next;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= maxn; i++) {
            for (int j = 0; j <= maxn; j++) ans = Math.min(ans, dp[i][j][shift]);
        }
        out.println(ans);
    }
}
