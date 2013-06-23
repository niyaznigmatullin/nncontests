package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {

    final int MOD = 1000000007;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        char[] c = in.next().toCharArray();
        int n = c.length;
        long ans = 0;
        long t = 1;
        for (int i = 0; i + 1 < n; i++) t = t * 2 % MOD;
        for (int i = 0; i < n; i++) {
            if (c[i] == '0') continue;
            long cur = t;
            for (int j = i + 1; j < n; j++) cur = cur * 2 % MOD;
            ans = (ans + cur) % MOD;
        }
        out.println(ans);
    }
}
