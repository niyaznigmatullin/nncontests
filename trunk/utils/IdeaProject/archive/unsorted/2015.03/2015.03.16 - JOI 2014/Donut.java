package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Donut {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[3 * n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i] = a[i + n] = a[i + n + n] = in.nextInt();
        }
        long left = 0;
        long right = sum / 3 + 1;
        int[] pos = new int[3 * n];
        long[] fs = new long[3 * n];
        while (left < right - 1) {
            long mid = left + right >> 1;
            long cur = 0;
            for (int i = 0, j = 0; i < 2 * n; i++) {
                while (cur < mid) {
                    cur += a[j++];
                }
                pos[i] = j;
                fs[i] = cur;
                cur -= a[i];
            }
            boolean ok = false;
            for (int i = 0; i < n; i++) {
                int pos1 = pos[i];
                if (sum - fs[i] - fs[pos1] >= mid) {
                    ok = true;
                    break;
                }
            }
            if (ok) left = mid; else right = mid;
        }
        out.println(left);
    }
}
