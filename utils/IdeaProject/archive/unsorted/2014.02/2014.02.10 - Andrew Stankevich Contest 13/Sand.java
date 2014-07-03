package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Sand {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int h = in.nextInt();
        int r = in.nextInt();
        int s = in.nextInt();
        int m = in.nextInt();
        double sin = 1. * h / Math.sqrt(h * h + r * r);
        for (int i = m; i > 0; i--) {
            double curh = s * Math.pow(1. * i / m, 1. / 3);
            out.println(curh / sin);
        }
    }
}
