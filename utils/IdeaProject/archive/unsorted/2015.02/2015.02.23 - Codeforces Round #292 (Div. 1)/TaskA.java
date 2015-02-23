package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskA {

    static int[] primes = {2, 3, 5, 7};
    static int[] f;
    static int[] x;
    static int[] g;
    static int[][] add;
    static int len;

    static boolean go(int pos, int cur) {
        boolean found = true;
        for (int i = 0; i < primes.length; i++) if (f[i] != g[i]) {
            found = false;
        }
        if (found) {
            len = pos;
            return true;
        }
        for (int i = 2; i <= cur; i++) {
            boolean ok = true;
            for (int j = 0; j < primes.length; j++) {
                g[j] += add[i][j];
                if (g[j] > f[j]) {
                    ok = false;
                }
            }
            x[pos] = i;
            if (ok && go(pos + 1, i)) {
                return true;
            }
            for (int j = 0; j < primes.length; j++) {
                g[j] -= add[i][j];
            }
        }
        return false;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        String s = in.next();
        f = new int[primes.length];
        add = new int[10][primes.length];
        for (int d = 2; d < 10; d++) {
            int[] f = add[d];
            for (int i = 0; i < primes.length; i++) {
                int p = primes[i];
                int x = d;
                while (x > 0) {
                    x /= p;
                    f[i] += x;
                }
            }
        }
        for (char c : s.toCharArray()) {
            int d = c - '0';
            if (d <= 1) continue;
            for (int i = 0; i < primes.length; i++) {
                f[i] += add[d][i];
            }
        }
        g = new int[primes.length];
        x = new int[100];
        if (go(0, 9)) {
            for (int i = 0; i < len; i++) {
                out.print(x[i]);
            }
            out.println();
        } else {
            throw new AssertionError();
        }
    }
}
