package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i * m <= n + m; i++) {
            ans = Math.min(ans, b * i + Math.max(0, n - i * m) * a);
        }
        out.println(ans);
    }
}
