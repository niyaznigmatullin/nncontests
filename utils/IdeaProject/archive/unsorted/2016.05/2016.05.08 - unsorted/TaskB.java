package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int h = in.nextInt();
        int[] a = in.readIntArray(n);
        int[] b = in.readIntArray(n);
        long length = 0;
        if (n == 1) {
            out.println(1 - b[0]);
            return;
        }
        int ans = (1 - b[0]) + (1 - b[n - 1]);
        for (int i = 1; i + 1 < n; i++) {
            if (b[i] == 1) {
                length = 0;
                continue;
            }
            length += a[i];
            if (length >= h) {
                ++ans;
                length = 0;
            }
        }
        out.println(ans);
    }
}
