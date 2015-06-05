package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

public class DrumDecoratorHard {
    static final int MOD = 1000000007;

    static int get(int r, int c) {
        int[] f = new int[r + 1];
        int[] g = new int[r + 1];
        f[0] = 1;
        g[0] = 1;
        for (int i = 1; i <= r; i++) {
            if (i > 1) {
                g[i] = f[i - 2];
            }
            f[i] = g[i - 1];
            if (c % 3 == 0 && i > 1) {
                f[i] = (int) ((f[i] + 3L * g[i - 2]) % MOD);
            }
            if (c % 6 == 0 && i > 1) {
                f[i] = (int) ((f[i] + 6L * g[i - 2]) % MOD);
            }
            if (c % 4 == 0 && i > 2) {
                f[i] = (int) ((f[i] + 4L * g[i - 3]) % MOD);
            }
        }
        return (f[r] + g[r]) % MOD;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int r = in.nextInt();
        int c = in.nextInt();
        int ans = 0;
        for (int i = 1; i <= c; i++) {
            int cycle = MathUtils.gcd(i, c);
            ans = (ans + get(r, cycle)) % MOD;
        }
        ans = (int) ((long) ans * MathUtils.modPow(c, MOD - 2, MOD) % MOD);
        out.println("Case #" + testNumber + ": " + ans);
    }
}
