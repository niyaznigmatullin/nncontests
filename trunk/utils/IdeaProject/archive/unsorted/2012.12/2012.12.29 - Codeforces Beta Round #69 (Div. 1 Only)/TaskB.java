package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        double a = in.nextInt();
        double b = in.nextInt();
        double c = Math.min(a / 4, b);
        if (b < 1e-9) {
            out.println(1);
            return;
        }
        if (a < 1e-9) {
            out.println(0.5);
            return;
        }
        double prob = a * c + a * b - 2 * c * c;
        out.println(prob / a / b / 2);
    }
}
