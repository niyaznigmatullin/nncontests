package coding;

import ru.ifmo.niyaz.DataStructures.FenwickMultiSum;
import ru.ifmo.niyaz.DataStructures.StupidSummator;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int w = in.nextInt();
        FenwickMultiSum fx = new FenwickMultiSum(n);
        FenwickMultiSum fy = new FenwickMultiSum(m);
        long all = 0;
        for (int i = 0; i < w; i++) {
            int type = in.nextInt();
            int x1 = in.nextInt() - 1;
            int y1 = in.nextInt() - 1;
            int x2 = in.nextInt();
            int y2 = in.nextInt();
            if (type == 0) {
                long v = in.nextInt();
                all += v * (x2 - x1) * (y2 - y1);
                fx.add(x1, x2, (y2 - y1) * v);
                fy.add(y1, y2, (x2 - x1) * v);
            } else {
                out.println(fx.getSum(x1, x2) + fy.getSum(y1, y2) - all);
            }
        }
    }
}
