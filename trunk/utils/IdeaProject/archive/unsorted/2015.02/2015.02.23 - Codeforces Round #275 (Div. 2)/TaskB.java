package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int cnt1 = in.nextInt();
        int cnt2 = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        long l = -1;
        long r = 1L << 60;
        while (l < r - 1) {
            long mid = l + r >> 1;
            long first = mid / y - mid / x / y;
            long second = mid / x - mid / x / y;
            long have = mid - mid / x - mid / y + mid / x / y;
            long left = Math.max(cnt1 - first, 0) + Math.max(cnt2 - second, 0);
            if (have >= left) r = mid; else l = mid;
        }
        out.println(r);
    }
}
