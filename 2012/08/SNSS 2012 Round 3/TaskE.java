package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] from = new int[n];
        int[] to = new int[n];
        int[] deg = new int[7];
        for (int i = 0; i < n; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
            deg[from[i]]++;
            deg[to[i]]++;
        }
        for (int i = 0; i < 7; i++) {
            if ((deg[i] & 1) == 1) {
                out.println(0);
                return;
            }
        }
        long[][] dp = new long[7][1 << n];
        for (int i = 0; i < 7; i++) {
            dp[i][0] = 1;
        }
        boolean[][] was = new boolean[7][7];
        for (int mask = 0; mask < 1 << n; mask++) {
            for (int last = 0; last < 7; last++) {
                long val = dp[last][mask];
                if (val == 0) {
                    continue;
                }
                for (boolean[] e : was) {
                    Arrays.fill(e, false);
                }
                for (int j = 0; j < n; j++) {
                    int a = from[j];
                    int b = to[j];
                    if (((mask >> j) & 1) == 1 || (a != last && b != last)) {
                        continue;
                    }
                    if (a > b) {
                        int t = a;
                        a = b;
                        b = t;
                    }
                    if (was[a][b]) {
                        continue;
                    }
                    was[a][b] = true;
                    int next = a ^ b ^ last;
                    dp[next][mask | (1 << j)] += val;
                }
            }
        }
        long answer = 0;
        for (int i = 0; i < 7; i++) {
            answer += dp[i][(1 << n) - 1];
        }
        out.println(answer);
    }
}
