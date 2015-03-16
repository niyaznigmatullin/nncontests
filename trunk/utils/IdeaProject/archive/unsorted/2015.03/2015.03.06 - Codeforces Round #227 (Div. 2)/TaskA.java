package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int t1 = in.readTimeHM(':');
        int t2 = in.readTimeHM(':');
        t1 = (t1 + 1440 - t2) % 1440;
        out.printf("%02d:%02d\n", t1 / 60, t1 % 60);
    }
}
