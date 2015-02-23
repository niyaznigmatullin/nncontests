package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int[] ax = new int[n];
        int[] ay = new int[n];
        int ac = 0;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[min]) min = j;
            }
            if (min != i) {
                ax[ac] = i;
                ay[ac] = min;
                ac++;
                int t = a[i];
                a[i] = a[min];
                a[min] = t;
            }
        }
        out.println(ac);
        for (int i = 0; i < ac; i++) {
            out.println(ax[i] + " " + ay[i]);
        }
    }
}
