package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

public class TaskD {

    static final int MOD = 1000000007;

    static int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] FACT = new int[m + n];
        int[] INV = new int[m + n];
        int[] INVFACT = new int[m + n];
        INV[1] = 1;
        for (int i = 2; i < INV.length; i++) {
            INV[i] = mul(INV[MOD % i], MOD - MOD / i);
        }
        FACT[0] = 1;
        INVFACT[0] = 1;
        for (int i = 1; i < FACT.length; i++) {
            FACT[i] = mul(FACT[i - 1], i);
            INVFACT[i] = mul(INVFACT[i - 1], INV[i]);
        }
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            int edges = i - 1;
            int left = m - edges;
            if (left >= 0) {
                int from = left + edges - 1;
                int choose = edges - 1;
                int ways = choose(FACT, INVFACT, from, choose);
                ways = mul(ways, choose(FACT, INVFACT, n - 2, i - 2));
                ways = mul(ways, FACT[i - 2]);
                if (i < n) {
                    ways = mul(ways, MathUtils.modPow(n, n - i - 1, MOD));
                    ways = mul(ways, i);
                    ways = mul(ways, MathUtils.modPow(m, n - i, MOD));
                }
                ans += ways;
                if (ans >= MOD) ans -= MOD;
            }
        }
        out.println(ans);
    }

    static int choose(int[] FACT, int[] INVFACT, int from, int choose) {
        return mul(FACT[from], mul(INVFACT[choose], INVFACT[from - choose]));
    }
}
