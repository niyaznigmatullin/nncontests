package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        if ((n & 1) == 1) {
            out.println("black");
        } else {
            out.println("white\n1 2");
        }
    }
}
