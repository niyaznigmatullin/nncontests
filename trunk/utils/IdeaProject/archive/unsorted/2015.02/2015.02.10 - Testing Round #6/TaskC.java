package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

public class TaskC {

    static final int MOD = 1000000009;

    static int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }

    static int[] FACT;
    static int[] INVFACT;
    static final int MAXN = 12345;

    static {
        FACT = new int[MAXN];
        INVFACT = new int[MAXN];
        FACT[0] = INVFACT[0] = 1;
        for (int i = 1; i < MAXN; i++) {
            FACT[i] = mul(FACT[i - 1], i);
            INVFACT[i] = mul(INVFACT[i - 1], MathUtils.modPow(i, MOD - 2, MOD));
        }
    }

    static int add(int a, int b) {
        a += b;
        if (a >= MOD) a -= MOD;
        return a;
    }

    static int c(int n, int k) {
        if (k > n || k < 0 || n < 0) return 0;
        return mul(mul(FACT[n], INVFACT[n - k]), INVFACT[k]);
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int w = in.nextInt();
        int b = in.nextInt();
        int ans = 0;
        for (int black = 1; black + 2 <= n; black++) {
            int white = n - black;
            ans = add(ans, mul(n - 1 - black, mul(c(b - 1, black - 1), c(w - 1, white - 1))));
        }
        out.println(mul(ans, mul(FACT[w], FACT[b])));
    }
}
