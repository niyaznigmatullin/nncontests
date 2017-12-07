package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        long[] f = new long[3];
        for (int i = 0; i < 3; i++) f[i] = in.nextInt();
        int[] a = in.readIntArray(n);
        long[] dp = new long[4];
        Arrays.fill(dp, Long.MIN_VALUE);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= 3; j++) {
                if (dp[j - 1] != Long.MIN_VALUE)
                    dp[j] = Math.max(dp[j], f[j - 1] * a[i] + dp[j - 1]);
            }
        }
        out.println(dp[3]);
    }
}
