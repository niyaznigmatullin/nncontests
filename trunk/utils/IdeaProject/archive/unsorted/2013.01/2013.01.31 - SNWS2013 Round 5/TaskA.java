package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] x = new int[m];
        int[] y = new int[m];
        for (int i = 0; i < m; i++) {
            x[i] = in.nextInt() - 1;
            y[i] = in.nextInt() - 1;
        }
        was = new boolean[n];
        p = new int[n];
        out.println(go(0, n, x, y));
    }

    boolean[] was;
    int[] p;

    boolean check(int n, int[] x, int[] y) {
        for (int i = 0; i < x.length; i++) {
            if (p[x[i]] > p[y[i]]) {
                return false;
            }
        }
        return true;
    }

    int go(int v, int n, int[] x, int[] y) {
        if (v == n) {
            return check(n, x, y) ? 1 : 0;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (was[i]) {
                continue;
            }
            was[i] = true;
            p[v] = i;
            ans += go(v + 1, n, x, y);
            was[i] = false;
        }
        return ans;
    }
}
