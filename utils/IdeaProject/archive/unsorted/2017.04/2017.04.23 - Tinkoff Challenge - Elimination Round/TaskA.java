package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = in.readIntArray(n);
        for (int i = 0; i < n; i++) {
            if (a[0] % k != a[i] % k) {
                out.println(-1);
                return;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i : a) {
            min = Math.min(min, i);
        }
        long ans = 0;
        for (int i : a) {
            ans += (i - min) / k;
        }
        out.println(ans);
    }
}
