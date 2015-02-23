package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = Math.min(in.nextInt(), in.nextInt());
        if ((n & 1) == 1) {
            out.println("Akshat");
        } else {
            out.println("Malvika");
        }
    }
}
