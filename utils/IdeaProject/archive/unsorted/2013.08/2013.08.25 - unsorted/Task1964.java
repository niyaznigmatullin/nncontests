package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Task1964 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int k = in.nextInt();
        int n = in.nextInt();
        int[] a = new int[n];
        int minimal = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            if (a[i] < minimal) minimal = a[i];
        }
        long all = 0;
        for (int i = 0; i < n; i++) {
            all += k - a[i];
        }
        out.println(Math.max(0, Math.min(minimal, k - all)));
    }
}
