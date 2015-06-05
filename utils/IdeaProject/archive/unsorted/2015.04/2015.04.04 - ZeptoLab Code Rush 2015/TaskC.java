package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        final int C = 50000;
        if (x < C) {
            long ans = 0;
            long all = n / y;
            for (long i = 0; i * y <= n && i <= x; i++) {
                {
                    long left = n - i * y;
                    long f = i * b + (left / x) * a;
                    ans = Math.max(ans, f);
                }
                {
                    long got = all - i;
                    long left = n - got * y;
                    long f = got * b + (left / x) * a;
                    ans = Math.max(ans, f);
                }
            }
            out.println(ans);
        } else {
            long ans = 0;
            for (long i = 0; i * x <= n; i++) {
                long left = n - i * x;
                long f = i * a + (left / y) * b;
                ans = Math.max(ans, f);
            }
            out.println(ans);
        }
    }
}
