package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n];
        int[] where = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt() - 1;
            where[a[i]] = i;
        }
        long ans = 0;
        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            ans += where[x] / k + 1;
            int pos = where[x];
            if (pos > 0) {
                int t = a[pos];
                a[pos] = a[pos - 1];
                a[pos - 1] = t;
                where[a[pos]] = pos;
                where[a[pos - 1]] = pos - 1;
            }
        }
        out.println(ans);
    }
}
