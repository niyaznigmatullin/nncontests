package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long n = in.nextLong() * 6L;
        long a = in.nextLong();
        long b = in.nextLong();
        boolean swapped = false;
        if (a > b) {
            long t = a;
            a = b;
            b = t;
            swapped = true;
        }
        if (a * b >= n) {
            out.println(a * b);
            if (swapped) out.println(b + " " + a);
            else
                out.println(a + " " + b);
            return;
        }
        long s = Long.MAX_VALUE;
        long sa = -1;
        long sb = -1;
        for (long left = a; ; left++) {
            long f = (n + left - 1) / left;
            f = Math.max(f, b);
            f = Math.max(f, left);
            if (s > f * left) {
                s = f * left;
                sa = left;
                sb = f;
            }
            if (left * left > n) break;
        }
        out.println(s);
        if (swapped) out.println(sb + " " + sa);
        else
            out.println(sa + " " + sb);
    }
}
