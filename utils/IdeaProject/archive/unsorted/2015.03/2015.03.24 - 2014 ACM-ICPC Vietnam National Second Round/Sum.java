package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Sum {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long n = in.nextLong();
        final int MAXN = 1000000;
        long ans = 0;
        for (int i = 1; n / i >= MAXN; i++) {
            ans += n / i;
        }
        for (int i = 1; i <= n && i < MAXN; i++) {
            ans += i * (n / i - (n / (i + 1)));
        }
        out.println(ans % 1000000);
    }
}
