package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[][] dp = new int[n][n];
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            left[i] = in.nextInt();
            right[i] = in.nextInt();
        }
        for (int[] e : dp) Arrays.fill(e, -1);
        for (int i = 0; i < n; i++) {
            if (left[i] <= 1 && 1 <= right[i]) {
                dp[i][i] = 0;
            }
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0, j = len - 1; j < n; i++, j++) {
                for (int dist = left[i]; dist <= right[i]; dist++) {
                    if ((dist & 1) == 0) continue;
                    int inside = dist / 2;
                    int after = i + 1 + inside;
                    if (after > j + 1) continue;
                    int before = after - 1;
                    boolean can1 = i + 1 > before || dp[i + 1][before] >= 0;
                    boolean can2 = after > j || dp[after][j] >= 0;
                    if (can1 && can2) {
                        dp[i][j] = after;
                        break;
                    }
                }
            }
        }
        if (dp[0][n - 1] < 0) {
            out.println("IMPOSSIBLE");
        } else {
            out.println(restore(0, n - 1, dp));
        }
    }

    static String restore(int left, int right, int[][] dp) {
        if (left == right) return "()";
        int after = dp[left][right];
        String ret = "(";
        if (left + 1 <= after - 1) ret += restore(left + 1, after - 1, dp);
        ret += ")";
        if (after <= right) ret += restore(after, right, dp);
        return ret;
    }
}
