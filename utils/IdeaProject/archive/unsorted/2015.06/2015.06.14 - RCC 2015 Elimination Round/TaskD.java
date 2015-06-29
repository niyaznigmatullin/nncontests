package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {
    static final int MOD = 1000000007;

    static int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }

    static int c(int n, int k) {
        return mul(mul(INVFACT[k], INVFACT[n - k]), FACT[n]);
    }

    static int catalan(int n) {
        return mul(c(2 * n, n), INV[n + 1]);
    }

    static int[] INV;
    static int[] FACT;
    static int[] INVFACT;
    static final int MAXN = 1234567;
    static {
        INV = new int[MAXN];
        INV[1] = 1;
        for (int i = 2; i < MAXN; i++) {
            INV[i] = mul(MOD - MOD / i, INV[MOD % i]);
        }
        FACT = new int[MAXN];
        INVFACT = new int[MAXN];
        FACT[0] = INVFACT[0] = 1;
        for (int i = 1; i < MAXN; i++) {
            FACT[i] = mul(i, FACT[i - 1]);
            INVFACT[i] = mul(INV[i], INVFACT[i - 1]);
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int[] stack = new int[n + 1];
        int cn = 0;
        int[] next = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (cn > 0 && a[stack[cn - 1]] < a[i]) --cn;
            if (cn > 0) {
                next[i] = stack[cn - 1];
            } else {
                next[i] = -1;
            }
            stack[cn++] = i;
        }
        boolean[] was = new boolean[n];
        int ans = 1;
        for (int i = 0; i < n; i++) {
            if (was[i]) continue;
            int v = i;
            int cc = 1;
            while (true) {
                was[v] = true;
                if (next[v] < 0 || a[next[v]] != a[v]) break;
                v = next[v];
                ++cc;
            }
            ans = mul(ans, catalan(cc));
        }
        out.println(ans);
    }
}
