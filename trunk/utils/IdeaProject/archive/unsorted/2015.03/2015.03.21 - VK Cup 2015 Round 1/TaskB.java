package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        final int MAXN = 1234;
        int[] dp = new int[MAXN];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            int w = in.nextInt();
            int h = in.nextInt();
            int[] next = new int[MAXN];
            Arrays.fill(next, Integer.MAX_VALUE);
            for (int j = 0; j < MAXN; j++) {
                int val = dp[j];
                if (val == Integer.MAX_VALUE) continue;
                {
                    int k = Math.max(j, h);
                    next[k] = Math.min(next[k], val + w);
                }
                {
                    int k = Math.max(j, w);
                    next[k] = Math.min(next[k], val + h);
                }
            }
            dp = next;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < MAXN; i++) {
            int val = dp[i];
            if (val == Integer.MAX_VALUE) continue;
            ans = Math.min(ans, i * val);
        }
        out.println(ans);
    }
}
