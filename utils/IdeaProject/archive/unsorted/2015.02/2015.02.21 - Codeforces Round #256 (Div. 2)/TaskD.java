package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        long k = in.nextLong();
        long left = 0;
        long right = (long) n * m + 1;
        while (left < right - 1) {
            long mid = (left + right) >> 1;
            long s = 0;
            for (int x = 1; x <= n; x++) {
                long count = Math.min(m, mid / x);
                s += count;
                if (s >= k) break;
            }
            if (s >= k) right = mid;
            else left = mid;
        }
        out.println(right);
    }
}
