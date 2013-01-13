package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[] a = in.readIntArray(n);
        if ((n & 1) == 0) {
            out.println(0);
            return;
        }
        int minimal = Integer.MAX_VALUE;
        for (int i = 0; i < n; i += 2) {
            minimal = Math.min(minimal, a[i]);
        }
        int one = (n + 1) / 2;
        int count = m / one;
        out.println(Math.min((long) count * k, minimal));
    }
}
