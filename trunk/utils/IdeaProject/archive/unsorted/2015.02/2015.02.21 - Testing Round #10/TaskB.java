package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        long[] a = new long[n];
        long s = 0;
        for (int i = 0; i < n; i++) s += a[i] = in.nextInt();
        s /= n;
        long ans = 0;
        for (int i = 0; i + 1 < n; i++) {
            if (i > 0)
                a[i] += a[i - 1];
            ans += Math.abs(a[i] - s * (i + 1));
        }
        out.println(ans);
    }
}
