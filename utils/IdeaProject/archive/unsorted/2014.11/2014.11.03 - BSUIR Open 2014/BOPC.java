package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

public class BOPC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        final int[] x = {1, 2, 3, 4, 5};
        final int[] y = {0, 4, 26, 92, 240};
        long ans = 0;
        final int MOD = 1000000007;
        for (int i = 0; i < x.length; i++) {
            long cur = y[i];
            for (int j = 0; j < x.length; j++) {
                if (i == j) continue;
                cur = cur * (n - x[j] + MOD) % MOD;
                cur = cur * MathUtils.modPow((MOD + x[i] - x[j]) % MOD, MOD - 2, MOD) % MOD;
            }
            ans = (ans + cur) % MOD;
        }
        out.println(ans);
    }
}
