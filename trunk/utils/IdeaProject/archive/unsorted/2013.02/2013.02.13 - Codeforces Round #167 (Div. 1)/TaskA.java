package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int m = in.nextInt();
        long height = 0;
        for (int i = 0; i < m; ++i) {
            int w = in.nextInt();
            int h = in.nextInt();
            long nh = Math.max(height, a[w - 1]);
            out.println(nh);
            height = nh + h;
        }
    }
}
