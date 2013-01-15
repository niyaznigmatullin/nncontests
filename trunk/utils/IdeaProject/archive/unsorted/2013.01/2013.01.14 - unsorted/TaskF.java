package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskF {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        for (int i = 0; i < 1 << n; i++) {
            int x = Integer.reverse(i) >>> (32 - n);
            out.print(x + 1 + " ");
        }
    }
}
