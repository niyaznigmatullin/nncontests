package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = in.nextInt();
        int[] ret = new int[n];
        int ac = 0;
        for (int i = 0; i < n; i++) {
            int best = -1;
            for (int j = 0; j < n; j++) {
                if (a[j] < 0) continue;
                if (best < 0 || a[best] > a[j]) best = j;
            }
            if (best < 0 || k < a[best]) break;
            k -= a[best];
            a[best] = -1;
            ret[ac++] = best + 1;
        }
        out.println(ac);
        out.printArray(Arrays.copyOf(ret, ac));
    }
}
