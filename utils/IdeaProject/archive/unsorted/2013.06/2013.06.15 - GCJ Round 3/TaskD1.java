package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD1 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        out.print("Case #" + testNumber + ": ");
        System.err.println("[" + testNumber + "]");
        char[] c = in.next().toCharArray();
        int n = c.length;
        double[] dp = new double[1 << n];
        for (int i = (1 << n) - 1; i >= 0; i--) {
            double all = 0;
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 0) {
                    double cur = 0;
                    cur += n;
                    int k = j - 1;
                    if (k < 0) k += n;
                    int cost = n - 1;
                    int count = 1;
                    while (((i >> k) & 1) == 1) {
                        cur += cost;
                        ++count;
                        --cost;
                        k--;
                        if (k < 0) k += n;
                    }
                    all += (cur + count * dp[i | (1 << j)]);
                }
            }
            dp[i] = all / n;
        }
        int mask = 0;
        for (int i = 0; i < n; i++) {
            if (c[i] == 'X') {
                mask |= 1 << i;
            }
        }
        out.println(dp[mask]);
    }
}
