package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[][] a = new int[3][n];
        int[][] where = new int[3][n];
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < n; i++) {
                a[j][i] = in.nextInt() - 1;
                where[j][a[j][i]] = i;
            }
        }
        int[] dp = new int[1 << n];
        for (int i = (1 << n) - 1; i >= 0; i--) {
            if (Integer.bitCount(i) == n - 1) {
                dp[i] = Integer.numberOfTrailingZeros(~i);
                continue;
            }
            int who = Integer.bitCount(i) % 3;
            int best = -1;
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) continue;
                int cur = dp[i | (1 << j)];
                if (best < 0 || where[who][best] > where[who][cur]) {
                    best = cur;
                }
            }
            dp[i] = best;
        }
        out.println(dp[0] + 1);
    }
}
