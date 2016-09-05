package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int x = in.nextInt();
        int[] a = in.readIntArray(n);
        int[] d = a.clone();
        long ans = Long.MAX_VALUE;
        for (int step = 0; step < n; step++) {
            long sum = (long) x * step;
            for (int i = 0; i < n; i++) {
                sum += d[i];
            }
            ans = Math.min(ans, sum);
            for (int i = 0; i < n; i++) {
                int j = (i - step - 1 + n) % n;
                d[i] = Math.min(d[i], a[j]);
            }
        }
        out.println(ans);
    }
}
