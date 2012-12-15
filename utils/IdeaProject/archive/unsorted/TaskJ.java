package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskJ {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <= 20; j++) {
                int ans = 0;
                for (int k = i, t = j; k <= t; k++, t--) {
                    ans += s(k) * p(t) + s(t) * p(k);
                }
                out.print(ans + "\t");
            }
            out.println();
        }
    }

    static int p(int n) {
        return n == 0 ? 1 : n % 10 * p(n / 10);
    }

    static int s(int n) {
        return n == 0 ? 0 : n % 10 + s(n / 10);
    }
}
