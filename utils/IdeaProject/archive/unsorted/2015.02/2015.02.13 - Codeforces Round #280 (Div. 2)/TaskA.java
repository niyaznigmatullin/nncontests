package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int i = 1;
        int s = 1;
        int all = 0;
        int n = in.nextInt();
        while (all + s <= n) {
            all += s;
            ++i;
            s += i;
        }
        out.println(i - 1);
    }
}
