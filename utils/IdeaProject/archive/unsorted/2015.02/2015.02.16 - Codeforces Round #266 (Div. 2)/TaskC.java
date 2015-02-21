package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = in.nextInt();
        long[] b = a.clone();
        for (int i = 1; i < n; i++) b[i] += b[i - 1];
        if (b[n - 1] % 3 != 0) {
            out.println(0);
            return;
        }
        long s = b[n - 1] / 3;
        int[] f = new int[n];
        for (int i = 0; i < n; i++) {
            if (b[i] == s) ++f[i];
        }
        for (int i = 1; i < n; i++) f[i] += f[i - 1];
        long cur = 0;
        long ans = 0;
        for (int i = n - 1; i >= 2; i--) {
            cur += a[i];
            if (cur != s) continue;
            ans += f[i - 2];
        }
        out.println(ans);
    }
}
