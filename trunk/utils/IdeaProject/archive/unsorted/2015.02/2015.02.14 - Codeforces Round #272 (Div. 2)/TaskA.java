package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int ans = Integer.MAX_VALUE;
        for (int two = 0; two * 2 <= n; two++) {
            int one = (n - two * 2);
            if ((two + one) % m == 0) {
                ans = Math.min(ans, two + one);
            }
        }
        out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}
