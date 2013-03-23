package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] dp = new int[111111];
        Arrays.fill(dp, -1);
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int ans = 0;
            for (int d = 1; d * d <= x; d++) {
                if (x % d != 0) continue;
                if (dp[d] < i - y) {
                    ++ans;
                }
                dp[d] = i;
                if (x != d * d) {
                    if (dp[x / d] < i - y) {
                        ++ans;
                    }
                    dp[x / d] = i;
                }
            }
            out.println(ans);
        }
    }
}
