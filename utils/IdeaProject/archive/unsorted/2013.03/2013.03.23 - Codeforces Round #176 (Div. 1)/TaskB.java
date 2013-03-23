package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[2 * n + 10];
        Arrays.fill(a, -1);
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        for (int k = 2; k <= n; k++) {
            int sh = k - 2;
            int last = (n - 1) / k * k;
            for (int i = last; i >= 0; i -= k) {
                int to = Math.min(i + k, n);
                a[to + sh] = a[i + sh];
            }
        }
        StringBuilder sb = new StringBuilder();
        int sh = n - 1;
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(a[i + sh] + 1);
        }
        out.println(sb);
    }
}
