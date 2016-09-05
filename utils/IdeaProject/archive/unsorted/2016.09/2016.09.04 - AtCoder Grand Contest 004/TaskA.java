package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        out.println(Math.min(solve(a, b, c), Math.min(solve(b, a, c), solve(c, a, b))));
    }

    static long solve(int a, int b, int c) {
        if ((a & 1) == 0) {
            return 0;
        }
        return (long) b * c;
    }
}
