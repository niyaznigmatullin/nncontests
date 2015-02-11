package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskG1 {

    static int[] a;
    static int count;
    static double sum;

    static void go(int moves) {
        if (moves == 0) {
            ++count;
            for (int i = 0; i < a.length; i++) {
                for (int j = i + 1; j < a.length; j++) {
                    if (a[i] > a[j]) ++sum;
                }
            }
            return;
        }
        for (int l = 0; l < a.length; l++) {
            for (int r = l; r < a.length; r++) {
                for (int i = l, j = r; i < j; i++, j--) {
                    int t = a[i];
                    a[i] = a[j];
                    a[j] = t;
                }
                go(moves - 1);
                for (int i = l, j = r; i < j; i++, j--) {
                    int t = a[i];
                    a[i] = a[j];
                    a[j] = t;
                }
            }
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        a = new int[n];
        int k = in.nextInt();
        for (int i = 0; i < n; i++) a[i] = in.nextInt() - 1;
        sum = 0;
        count = 0;
        go(k);
        out.println(sum / count);
    }
}
