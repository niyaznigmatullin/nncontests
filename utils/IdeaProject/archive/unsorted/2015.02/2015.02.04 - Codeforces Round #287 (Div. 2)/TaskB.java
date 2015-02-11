package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long r = in.nextInt();
        long x1 = in.nextInt();
        long y1 = in.nextInt();
        x1 -= in.nextInt();
        y1 -= in.nextInt();
        long d = (long) (Math.sqrt(x1 * x1 + y1 * y1) / (2 * r)) - 1;
        if (d < 0) d = 0;
        while (d * d * 4 * r * r < x1 * x1 + y1 * y1) ++d;
        out.println(d);
    }
}
