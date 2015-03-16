package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (x > 2 * a[i] || x == 1) {
                --ans;
            } else {
                ans += (long) (x / 2) * ((x + 1) / 2);
            }
        }
        out.println(ans);
    }
}
