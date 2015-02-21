package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long t = in.nextLong();
        long a = in.nextLong();
        long b = in.nextLong();
        if (t == 1 && a == 1 && b == 1) {
            out.println("inf");
            return;
        }
        if (t == a && a != b) {
            out.println(0);
            return;
        }
//        if (a == 1) {
//            if (b == 1) {
//                out.println(1);
//            } else {
//                out.println(0);
//            }
//            return;
//        }
        out.println(go(b, a, a, t));
    }

    static int go(long c, long d, long a, long t) {
        if (c == 0 || d == 0) {
            return (c == 0 && d == 0 ? 1 : 0);
        }
        int ans = 0;
        for (long e = c % a; e <= c && e <= d; e += a) {
            if ((d - e) % t != 0) continue;
            ans += go((c - e) / a, (d - e) / t, a, t);
        }
        return ans;
    }
}
