package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int b = n % m;
        for (int i = 0; i < m - b; i++) {
            out.print(n / m + " ");
        }
        for (int i = 0; i < b; i++) {
            out.print(n / m + 1 + " ");
        }
    }
}
