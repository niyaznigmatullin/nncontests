package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int h = in.nextInt();
        long n = in.nextLong();
        long leaves = 1L << h;
        n = leaves - n + 1;
        long ans = 0;
        for (int i = 0; i < h; i++) {
            leaves /= 2;
            if (n > leaves) {
                n = 2 * leaves - n + 1;
                ++ans;
            } else {
                ans += 2 * leaves;
            }
        }
        out.println(ans);
    }
}
