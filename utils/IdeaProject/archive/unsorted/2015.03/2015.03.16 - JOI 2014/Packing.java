package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Packing {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] p = in.readIntArray(n);
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < m; i++) {
            int c = in.nextInt();
            int e = in.nextInt();
            for (int j = n; j >= 0; j--) {
                int val = dp[j];
                if (val == Integer.MAX_VALUE) continue;
                int nj = Math.min(n, j + c);
                if (dp[nj] > val + e) {
                    dp[nj] = val + e;
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) dp[i] = Math.min(dp[i], dp[i + 1]);
        Arrays.sort(p);
        ArrayUtils.reverse(p);
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            sum += p[i];
            if (dp[i + 1] != Integer.MAX_VALUE) {
                ans = Math.max(ans, sum - dp[i + 1]);
            }
        }
        out.println(ans);
    }
}
