package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        if (k > 3 || k > n - 1) {
            out.println(-1);
            return;
        }
        if (k == 3) {
            out.println(3 + 2 * (n - 4));
            out.println("1 2");
            out.println("2 3");
            out.println("3 4");
            for (int i = 5; i <= n; i++) {
                out.println("1 " + i);
                out.println("3 " + i);
            }
            return;
        }
        if (k == 1) {
            if (n > 2) {
                out.println(-1);
            } else {
                out.println("1");
                out.println("1 2");
            }
            return;
        }
        out.println(n - 1);
        for (int i = 2; i <= n; i++) {
            out.println((i - 1) + " " + i);
        }
    }
}
