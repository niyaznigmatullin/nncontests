package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int to = in.nextInt() - 1   ;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int v = 0;
        boolean[] was = new boolean[n];
        while (!was[v]) {
            was[v] = true;
            if (v == to) {
                out.println("YES");
                return;
            }
            v += a[v];
        }
        out.println("NO");
    }
}
