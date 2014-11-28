package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println("[" + testNumber + "]");
        int n = in.nextInt();
        int p = in.nextInt();
        int q = in.nextInt();
        int r = in.nextInt();
        int s = in.nextInt();
        a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = ((long) i * p + q) % r + s;
        }
        for (int i = 1; i < n; i++) a[i] += a[i - 1];
        long minimal = a[n - 1];
        for (int i = 0; i < n; i++) {
            minimal = Math.min(minimal, Math.max(sum(0, i), sum(i, n)));
        }
        for (int i = 2, j = 1; i < n; i++) {
            while (j + 1 < i && sum(0, j + 1) <= sum(j + 1, i)) {
                ++j;
            }
            minimal = Math.min(minimal, Math.max(Math.max(sum(i, n), sum(j, i)), sum(0, j)));
            if (j + 1 < i) {
                minimal = Math.min(minimal, Math.max(Math.max(sum(i, n), sum(j + 1, i)), sum(0, j + 1)));
            }
        }
        out.println("Case #" + testNumber + ": " + (1. - 1. * minimal / a[n - 1]));
    }

    static long[] a;

    static long sum(int l, int r) {
        if (l >= r) return 0;
        long ret = a[r - 1];
        if (l > 0) ret -= a[l - 1];
        return ret;
    }
}
