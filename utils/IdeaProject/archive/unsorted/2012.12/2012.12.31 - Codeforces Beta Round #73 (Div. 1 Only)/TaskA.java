package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        long lcm = MathUtils.lcm(a, b);
        long all = 0;
        for (long current = a; current <= lcm; current += a) {
            long z = (current - 1) / b * b;
            if (current % b != 0 || (current % b == 0 && a > b)) {
                all += Math.max(0, z - (current - a));
            } else {
                all += a;
            }
        }
        if (2 * all > lcm) {
            out.println("Masha");
        } else if (2 * all == lcm) {
            out.println("Equal");
        } else {
            out.println("Dasha");
        }
    }
}
