package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int len = in.nextInt();
        int[] x = new int[n + 1];
        int[] b = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            x[i] = in.nextInt();
            b[i] = in.nextInt();
        }
        double[][] dist = new double[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                dist[i][j] = Math.sqrt(Math.abs(len - (x[j] - x[i])));
            }
        }
        double l = 0;
        double r = 1e7;
        int[] lastfrom = null;
        for (int it = 0; it < 80; it++) {
            double mid = (l + r) * .5;
            double[] dp = new double[n + 1];
            Arrays.fill(dp, Double.POSITIVE_INFINITY);
            dp[0] = 0;
            int[] from = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j < i; j++) {
                    double cur = dp[j] + dist[j][i] - b[i] * mid;
                    if (cur < dp[i]) {
                        dp[i] = cur;
                        from[i] = j;
                    }
                }
            }
            if (dp[n] < 0) {
                r = mid;
                lastfrom = from;
            }
            else l = mid;
        }
        int[] ans = new int[n];
        int ac = 0;
        for (int i = n; i > 0; i = lastfrom[i]) {
            ans[ac++] = i;
        }
        for (int i = ac - 1; i >= 0; i--) {
            if (i + 1 < ac) out.print(' ');
            out.print(ans[i]);
        }
        out.println();
    }
}
