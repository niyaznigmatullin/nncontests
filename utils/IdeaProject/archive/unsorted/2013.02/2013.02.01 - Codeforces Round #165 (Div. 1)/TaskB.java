package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            in.next();
        }
        int[] dp = new int[m + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            int x = a[i];
            int minimal = Integer.MAX_VALUE;
            for (int j = 0; j <= x; j++) {
                minimal = Math.min(minimal, dp[j]);
            }
            for (int j = 0; j <= m; j++) {
                if (dp[j] != Integer.MAX_VALUE) {
                    dp[j]++;
                }
            }
            dp[x] = Math.min(dp[x], minimal);
        }
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i <= m; i++) {
            answer = Math.min(answer, dp[i]);
        }
        out.println(answer);
    }
}
