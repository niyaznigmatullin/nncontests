package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class RailroadRenovations {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println("Test #" + testNumber);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] p = new int[n];
        int[] r = new int[n];
        int[] all = new int[n];
        int cn = 0;
        for (int i = 0; i < n; i++) {
            p[i] = in.nextInt();
            r[i] = in.nextInt();
            if (r[i] == 1) {
                all[cn++] = p[i];
            }
        }
        all = Arrays.copyOf(all, cn);
        all = ArrayUtils.sortAndUnique(all);
        cn = all.length;
        k = Math.min(k, n);
        int[][] dp = new int[cn + 1][k + 1];
        for (int[] e : dp) Arrays.fill(e, Integer.MAX_VALUE);
        dp[0][0] = 0;
        for (int to = 0; to < cn; to++) {
            for (int from = 0; from <= to; from++) {
                int dp0 = 0;
                int dp1 = 0;
                for (int e = 0; e < n; e++) {
                    if (p[e] < all[from] || p[e] > all[to]) continue;
                    int ndp0 = r[e] == 1 ? dp0 + 1 : dp0;
                    int ndp1 = r[e] == 0 ? 1 + dp1 : Math.min(dp0, dp1);
                    dp0 = ndp0;
                    dp1 = ndp1;
                }
                int rearrangements = Math.min(dp0, dp1);
                for (int was = 0; was + rearrangements <= k; was++) {
                    int value = dp[from][was];
                    if (value == Integer.MAX_VALUE) continue;
                    dp[to + 1][was + rearrangements] = Math.min(dp[to + 1][was + rearrangements], value + 1);
                }
            }
        }
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i <= k; i++) {
            answer = Math.min(answer, dp[cn][i]);
        }
        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        out.println("Case #" + testNumber + ": " + answer);
    }
}
