package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            b[i] = in.nextInt();
            c[i] = in.nextInt();
            d[i] = in.nextInt();
        }
        boolean[] bad = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i] > a[j] && b[i] > b[j] && c[i] > c[j]) {
                    bad[j] = true;
                }
            }
        }
        int best = -1;
        for (int i = 0; i < n; i++) {
            if (bad[i]) {
                continue;
            }
            if (best < 0 || d[best] > d[i]) {
                best = i;
            }
        }
        out.println(best + 1);
    }
}
