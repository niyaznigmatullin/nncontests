package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class CookieClickerAlpha {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long c = Math.round(in.nextDouble() * 1e6);
        long f = Math.round(in.nextDouble() * 1e6);
        long x = Math.round(in.nextDouble() * 1e6);
        System.err.println("[" + testNumber + "]");
        out.println("Case #" + testNumber + ": " + solve(c, f, x));
    }

    static double solve(long c, long f, long x) {
        double time = 0.;
        long v = 2000000;
        double ans = Double.POSITIVE_INFINITY;
        for (int i = 0; i < 5234567; i++) {
            ans = Math.min(ans, time + 1. * x / v);
            time += (1. * c / v);
            v += f;
        }
        ans = Math.min(ans, time + 1. * x / v);
        return ans;
    }

}
