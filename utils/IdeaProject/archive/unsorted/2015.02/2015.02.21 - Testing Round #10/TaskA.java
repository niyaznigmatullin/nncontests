package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        boolean[] watched = new boolean[n];
        for (int i = 0; i < n - 1; i++) {
            watched[in.nextInt() - 1] = true;
        }
        for (int i = 0; i < n; i++) if (!watched[i]) out.println(i + 1);
    }
}
