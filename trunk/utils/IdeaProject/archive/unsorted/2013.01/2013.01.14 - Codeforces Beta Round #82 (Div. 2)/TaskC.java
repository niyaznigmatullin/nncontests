package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int w = in.nextInt();
        int n = in.nextInt();
        int c0 = in.nextInt();
        int d0 = in.nextInt();
        int[] dp = new int[w + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        for (int i = 0; i + c0 <= w; i++) {
            int val = dp[i];
            if (val == Integer.MIN_VALUE) {
                continue;
            }
            dp[i + c0] = Math.max(dp[i + c0], dp[i] + d0);
        }
        for (int i = 0; i < n; i++) {
            int all = in.nextInt();
            all /= in.nextInt();
            int c = in.nextInt();
            int d = in.nextInt();
            for (int j = w; j >= 0; j--) {
                for (int k = 1; j >= k * c && k <= all; k++) {
                    int val = dp[j - k * c];
                    if (val == Integer.MIN_VALUE) {
                        continue;
                    }
                    dp[j] = Math.max(dp[j], val + d * k);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i <= w; i++) {
            ans = Math.max(ans, dp[i]);
        }
        out.println(ans);
    }
}
