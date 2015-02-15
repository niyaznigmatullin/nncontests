package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long n = in.nextLong();
        if ((n & 1) == 0) {
            out.println(n / 2);
        } else {
            out.println(n / 2 - n);
        }
    }
}
