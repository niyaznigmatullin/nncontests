package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int day = 0;
        while (n > 0) {
            day++;
            --n;
            if (day % m == 0) ++n;
        }
        out.println(day);
    }
}
