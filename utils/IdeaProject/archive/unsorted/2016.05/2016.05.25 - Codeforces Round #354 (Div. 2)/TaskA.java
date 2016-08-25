package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
                int id1 = -1;
                int idn = -1;
                for (int e = 0; e < n; e++) {
                    if (a[e] == 1) id1 = e;
                    if (a[e] == n) idn = e;
                }
                ans = Math.max(ans, Math.abs(id1 - idn));
                t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        out.println(ans);
    }
}
