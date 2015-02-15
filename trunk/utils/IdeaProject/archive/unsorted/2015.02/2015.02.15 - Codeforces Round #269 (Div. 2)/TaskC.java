package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long n = in.nextLong();
        int ans = 0;
        for (int h = 1; h <= 2000000; h++) {
            long need = h * 2 + (long) h * (h - 1) / 2 * 3;
            if (need % 3 != n % 3 || n < need) continue;
            ++ans;
        }
        out.println(ans);
    }
}
