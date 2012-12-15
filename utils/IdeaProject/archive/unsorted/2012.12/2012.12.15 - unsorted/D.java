package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class D {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] t = new int[n];
        long maximal = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            t[i] = in.nextInt();
            maximal = Math.min(maximal, (long) t[i] * m);
        }
        long l = 0;
        long r = maximal;
        while (l < r - 1) {
            long mid = (l + r) >>> 1;
            if (check(mid, m, t)) {
                r = mid;
            } else {
                l = mid;
            }
        }
        out.println(r);
    }

    static boolean check(long time, int people, int[] t) {
        long all = 0;
        for (int i : t) {
            all += time / i;
        }
        return all >= people;
    }
}
