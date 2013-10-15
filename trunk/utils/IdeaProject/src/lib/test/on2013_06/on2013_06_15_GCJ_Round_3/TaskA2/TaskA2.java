package lib.test.on2013_06.on2013_06_15_GCJ_Round_3.TaskA2;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Locale;

public class TaskA2 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        out.print("Case #" + testNumber + ": ");
        System.err.println("[" + testNumber + "]");
        long b = in.nextLong();
        int n = in.nextInt();
        double ans = 0;
        long[] a = new long[37];
        for (int i = 0; i < n; i++) a[i] = in.nextLong();
        Arrays.sort(a);
        for (int i = a.length - 1; i >= 0; i--) a[i] -= a[0];
        for (int win = 1; win <= 35; win++) {
            if (!ok(a, win, a[win - 1], b)) {
                continue;
            }
            long l = a[win - 1];
            long r = b + 1;
            while (l < r - 1) {
                long mid = (l + r) >>> 1;
                if (ok(a, win, mid, b)) {
                    l = mid;
                } else {
                    r = mid;
                }
            }
            long cur = 0;
            for (int i = 0; i < win; i++) {
                cur += l - a[i];
            }
            double sum = cur;
            for (int i = win; i < 35; i++) {
                if (a[i] <= l) {
                    cur += l - a[i] + 1;
                }
            }
//            System.out.println(sum + " " + win + " " + cur + " " + l + " " + (36 * sum / win - cur));
            ans = Math.max(ans, 36 * sum / win - cur);
        }
        out.printf(Locale.US, "%.17f\n", ans);
    }

    boolean ok(long[] a, int k, long x, long b) {
        long cur = 0;
        for (int i = 0; i < k; i++) {
            if (a[i] > x) throw new AssertionError();
            cur += x - a[i];
            if (cur > b) return false;
        }
        for (int i = k; i < 35; i++) {
            if (a[i] <= x) {
                cur += x - a[i] + 1;
                if (cur > b) return false;
            }
        }
        if (a[35] <= x || a[36] <= x) return false;
        return cur <= b;
    }
}
