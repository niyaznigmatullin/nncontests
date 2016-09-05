package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Cards {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int x = in.nextInt();
        int sh = 50 * 50 + 1;
        long[] dp = new long[2 * sh];
        dp[sh] = 1;
        for (int i = 0; i < n; i++) {
            int y = in.nextInt() - x;
            long[] next = new long[2 * sh];
            if (y <= 0) {
                for (int j = 0; j < 2 * sh; j++) {
                    next[j] += dp[j];
                    if (j - y < 2 * sh) {
                        next[j] += dp[j - y];
                    }
                }
            } else {
                for (int j = 2 * sh - 1; j >= 0; j--) {
                    next[j] += dp[j];
                    if (j - y >= 0) {
                        next[j] += dp[j - y];
                    }
                }
            }
            dp = next;
        }
        out.println(dp[sh] - 1);
    }
}
