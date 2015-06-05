package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;
import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] f = new int[n];
        for (int i = 0; i < n; i++) f[i] = in.nextInt() - 1;
        int[] was = new int[n];
        int[] d = new int[n];
        BigInteger ans = BigInteger.ONE;
        for (int i = 0; i < n; i++) {
            Arrays.fill(was, 0);
            int p = i;
            int c = 0;
            while (was[p] == 0) {
                d[p] = c++;
                was[p] = 1;
                p = f[p];
            }
            BigInteger cycle = BigInteger.valueOf(c - d[p]);
            ans = ans.divide(ans.gcd(cycle)).multiply(cycle);
        }
        for (int q = 1; q <= 2 * n; q++) {
            boolean bad = false;
            for (int i = 0; i < n; i++) {
                int v = i;
                for (int j = 0; j < q; j++) v = f[v];
                int u = v;
                for (int j = 0; j < q; j++) u = f[u];
                if (v != u) {
                    bad = true;
                }
            }
            if (!bad) {
                out.println(q);
                return;
            }
        }
        out.println(ans);
    }
}
