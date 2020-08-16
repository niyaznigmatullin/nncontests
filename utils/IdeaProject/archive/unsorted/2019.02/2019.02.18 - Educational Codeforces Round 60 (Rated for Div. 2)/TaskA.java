package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int mx = -1;
        for (int i = 0; i < n; i++) {
            if (a[i] > mx) {
                mx = a[i];
            }
        }
        int bestL = -1;
        int bestR = -1;
        for (int i = 0; i < n; ) {
            if (a[i] != mx) {
                i++;
                continue;
            }
            int j = i;
            while (j < n && a[j] == mx) {
                j++;
            }
            if (j - i > bestR - bestL) {
                bestL = i;
                bestR = j;
            }
            i = j;
        }
        out.println(bestR - bestL);
    }
}
