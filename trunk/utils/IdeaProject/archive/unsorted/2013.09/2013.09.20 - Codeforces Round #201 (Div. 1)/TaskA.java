package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int g = 0;
        for (int i = 0; i < n; i++) {
            g = MathUtils.gcd(g, a[i]);
        }
        for (int i = 0; i < n; i++) {
            a[i] /= g;
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, a[i]);
        }
        max -= n;
        if ((max & 1) == 1) {
            out.println("Alice");
        } else {
            out.println("Bob");
        }
    }
}
