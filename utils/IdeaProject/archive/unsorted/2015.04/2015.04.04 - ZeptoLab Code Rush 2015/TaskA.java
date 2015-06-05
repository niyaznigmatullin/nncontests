package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        in.next();
        char[] c = in.next().toCharArray();
        for (int i = 0; i < c.length; i++) {
            for (int j = i + 1; j < c.length; j++) {
                int dif = j - i;
                boolean ok = true;
                for (int e = 0; e < 5; e++) {
                    int k = i + dif * e;
                    if (k >= c.length || c[k] == '.') {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    out.println("yes");
                    return;
                }
            }
        }
        out.println("no");
    }
}
