package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] a = new int[n][n];
        for (int[] d : a) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        int sum = 0;
        int[] deg = new int[n];
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int w = in.nextInt();
            deg[from]++;
            deg[to]++;
            sum += w;
            a[from][to] = a[to][from] = Math.min(a[from][to], w);
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i][k] == Integer.MAX_VALUE || a[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    a[i][j] = Math.min(a[i][j], a[i][k] + a[k][j]);
                }
            }
        }
        for (int i = 1; i < n; i++) {
            if (deg[i] > 0 && a[0][i] == Integer.MAX_VALUE) {
                out.println(-1);
                return;
            }
        }
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int mask = 0; mask < 1 << n; mask++) {
            if (dp[mask] == Integer.MAX_VALUE) {
                continue;
            }
            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 1) {
                    continue;
                }
                for (int j = i + 1; j < n; j++) {
                    if (((mask >> j) & 1) == 1 || a[i][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    int nMask = mask | (1 << i) | (1 << j);
                    dp[nMask] = Math.min(dp[nMask], dp[mask] + a[i][j]);
                }
            }
        }
        int mask = 0;
        for (int i = 0; i < n; i++) {
            if ((deg[i] & 1) == 1) {
                mask |= 1 << i;
            }
        }
        if (dp[mask] == Integer.MAX_VALUE) {
            out.println(-1);
            return;
        }
        out.println(dp[mask] + sum);
    }
}
