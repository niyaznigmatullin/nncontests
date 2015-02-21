package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int length = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        Arrays.sort(a);
        double ans = 0;
        ans = Math.max(ans, a[0]);
        ans = Math.max(ans, length - a[n - 1]);
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, (a[i] - a[i - 1]) * .5);
        }
        out.println(ans);
    }
}
