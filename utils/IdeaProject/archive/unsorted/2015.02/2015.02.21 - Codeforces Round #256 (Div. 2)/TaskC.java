package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    static int[] a;

    static long go(int left, int right, int up) {
        if (left >= right) return 0;
        int best = -1;
        for (int i = left; i < right; i++) {
            if (best < 0 || a[best] > a[i]) best = i;
        }
        return Math.min(right - left, go(left, best, a[best]) + go(best + 1, right, a[best]) + a[best] - up);
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        a = in.readIntArray(n);
        out.println(go(0, n, 0));
    }
}
