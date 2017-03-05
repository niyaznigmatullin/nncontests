package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        for (int i = 0; i < n; i++) --a[i];
        boolean[] has = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (has[a[i]]) {
                out.println(-1);
                return;
            }
            has[a[i]] = true;
        }
        long ans = 1;
        boolean[] was = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (was[i]) continue;
            int count = 0;
            int v = i;
            while (!was[v]) {
                was[v] = true;
                ++count;
                v = a[v];
            }
            if (count % 2 == 0) count /= 2;
            ans = MathUtils.lcm(ans, count);
        }
        out.println(ans);
    }
}
