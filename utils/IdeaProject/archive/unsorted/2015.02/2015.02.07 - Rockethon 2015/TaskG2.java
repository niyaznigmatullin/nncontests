package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskG2 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = in.readIntArray(n);
        double[][] dp = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i][j] = 1.;
            }
        }
        for (int step = 0; step < k; step++) {
            double[][] next = new double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) continue;
                    double cur = 0.;
                    int count = 0;
                    for (int l = 0; l < n; l++) {
                        for (int r = l; r < n; r++) {
                            ++count;
                            int ni = i;
                            if (ni >= l && ni <= r) {
                                ni = r - (ni - l);
                            }
                            int nj = j;
                            if (nj >= l && nj <= r) {
                                nj = r - (nj - l);
                            }
                            cur += dp[ni][nj];
                        }
                    }
                    cur /= count;
                    next[i][j] = cur;
                }
            }
            dp = next;
        }
        for (double[] e : dp) {
            System.out.println(Arrays.toString(e));
        }
        double ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i] > a[j]) {
                    ans += dp[j][i];
                } else {
                    ans += dp[i][j];
                }
            }
        }
        out.println(ans);
    }
}
