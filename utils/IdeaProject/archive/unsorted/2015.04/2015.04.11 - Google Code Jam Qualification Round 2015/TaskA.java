package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        in.next();
        char[] c = in.next().toCharArray();
        all: for (int men = 0; ; men++) {
            int cur = men;
            for (int i = 0; i < c.length; i++) {
                int x = c[i] - '0';
                for (int j = 0; j < x; j++) {
                    if (cur < i) {
                        continue all;
                    }
                    ++cur;
                }
            }
            out.println("Case #" + testNumber + ": " + men);
            return;
        }
    }
}
