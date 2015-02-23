package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskF {
    static int MOD;

    static int add(int a, int b) {
        a += b;
        if (a >= MOD) a -= MOD;
        return a;
    }

    static int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }

    static int f(int n) {
        return n * (n - 1) / 2 % MOD;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        MOD = in.nextInt();
        int zeros = 0;
        int ones = 0;
        int[] cnt = new int[n];
        for (int i = 0; i < m; i++) {
            char[] c = in.next().toCharArray();
            for (int j = 0; j < n; j++) {
                cnt[j] += c[j] - '0';
            }
        }
        for (int i = 0; i < n; i++) {
            if (cnt[i] == 0) ++zeros; else
                if (cnt[i] == 1) ++ones;
        }
        int[][] dp = new int[n + 1][n + 1];
        dp[zeros][ones] = 1;
        for (int i = m; i < n; i++) {
            int[][] next = new int[n + 1][n + 1];
            for (int c0 = 0; c0 <= n; c0++) {
                for (int c1 = 0; c1 <= n; c1++) {
                    int val = dp[c0][c1];
                    if (val == 0) continue;
                    if (c0 >= 2) {
                        next[c0 - 2][c1 + 2] = add(next[c0 - 2][c1 + 2], mul(f(c0), val));
                    }
                    if (c0 >= 1 && c1 >= 1) {
                        next[c0 - 1][c1] = add(next[c0 - 1][c1], mul(c0, mul(c1, val)));
                    }
                    if (c1 >= 2) {
                        next[c0][c1 - 2] = add(next[c0][c1 - 2], mul(f(c1), val));
                    }
                }
            }
            dp = next;
        }
        out.println(dp[0][0]);
    }
}
