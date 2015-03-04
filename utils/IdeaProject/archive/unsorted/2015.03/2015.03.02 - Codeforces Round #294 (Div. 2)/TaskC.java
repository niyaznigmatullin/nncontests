package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int ans = 0;
        for (int one = 0; one <= m && 2 * one <= n; one++) {
            int leftN = n - 2 * one;
            int leftM = m - one;
            ans = Math.max(ans, one + Math.min(leftN, leftM / 2));
        }
        out.println(ans);
    }
}
