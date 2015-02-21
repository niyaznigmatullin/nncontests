package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int cn1 = 0;
        int cn2 = 0;
        int[] a = new int[n];
        int[] b = new int[n];
        long sum1 = 0;
        long sum2 = 0;
        int last = 0;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (x > 0) {
                a[cn1++] = x;
                sum1 += x;
                last = 1;
            } else {
                b[cn2++] = -x;
                sum2 -= x;
                last = 2;
            }
        }
        out.println(won(a, cn1, b, cn2, sum1, sum2, last) ? "first" : "second");
    }

    static boolean won(int[] a, int cn1, int[] b, int cn2, long sum1, long sum2, int last) {
        if (sum1 > sum2) return true;
        if (sum1 < sum2) return false;
        for (int i = 0; i < Math.min(cn1, cn2); i++) {
            if (a[i] > b[i]) return true;
            if (a[i] < b[i]) return false;
        }
        if (cn1 > cn2) return true;
        if (cn1 < cn2) return false;
        return last == 1;
    }
}
