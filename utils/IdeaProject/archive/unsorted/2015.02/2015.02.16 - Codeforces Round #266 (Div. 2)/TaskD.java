package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        final int MOD = 1000000007;
        int n = in.nextInt();
        int h = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = h - in.nextInt();
        }
        int[] dp = new int[h + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            int[] next = new int[h + 1];
            for (int opened = 0; opened <= h; opened++) {
                int val = dp[opened];
                if (val == 0) continue;
                for (int open = 0; open <= 1; open++) {
                    for (int close = 0; close <= opened + open && close <= 1; close++) {
                        int nOpened = opened - close + open;
                        if (opened + open != a[i]) continue;
                        int curVal = val;
                        if (close == 1) {
                            curVal = (int) ((long) curVal * (opened + open) % MOD);
                        }
                        next[nOpened] += curVal;
                        if (next[nOpened] >= MOD) next[nOpened] -= MOD;
                    }
                }
            }
            dp = next;
        }
        out.println(dp[0]);
    }
}
