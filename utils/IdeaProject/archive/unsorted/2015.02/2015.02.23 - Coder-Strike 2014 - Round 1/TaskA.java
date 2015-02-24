package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        --k;
        String s = in.next();
        if (k < n - k - 1) {
            while (k > 0) {
                out.println("LEFT");
                --k;
            }
            while (k < n) {
                out.println("PRINT " + s.charAt(k));
                if (k + 1 < n)
                    out.println("RIGHT");
                ++k;
            }
        } else {
            while (k + 1 < n) {
                out.println("RIGHT");
                ++k;
            }
            while (k >= 0) {
                out.println("PRINT " + s.charAt(k));
                if (k > 0)
                    out.println("LEFT");
                --k;
            }
        }
    }
}
