package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

public class TaskD {

    static int MOD = 1000000007;

    static int add(int a, int b) {
        a += b;
        if (a >= MOD) a -= MOD;
        return a;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        MOD = in.nextInt();
        int[][] dp = new int[2][k];
        int ten = 1;
        int ans = 0;
        dp[0][0] = 1;
        for (int i = 0; i < n; i++, ten = ten * 10 % k) {
            int[][] next = new int[2][k];
            for (int mod = 0; mod < k; mod++) {
                for (int d = 0; d < 10; d++) {
                    int nMod = (mod + ten * d) % k;
                    for (int has = 0; has < 2; has++) {
                        int val = dp[has][mod];
                        if (val == 0) continue;
                        int nHas = has;
                        if (nMod == 0 && d > 0) nHas = 1;
                        next[nHas][nMod] = add(next[nHas][nMod], val);
                        if (i + 1 == n && d > 0 && nHas == 1) ans = add(ans, val);
                    }
                }
            }
            dp = next;
        }
        out.println(ans);
    }
}
