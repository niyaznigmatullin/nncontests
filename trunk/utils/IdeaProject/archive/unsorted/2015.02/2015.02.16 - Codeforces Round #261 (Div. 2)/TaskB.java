package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        ArrayUtils.sort(a);
        if (a[0] == a[n - 1]) {
            out.println(0 + " " + (long) n * (n - 1) / 2);
        } else {
            int first = 0;
            while (a[0] == a[first]) ++first;
            int last = n - 1;
            while (a[last] == a[n - 1]) --last;
            out.println(a[n - 1] - a[0] + " " + (long) first * (n - last - 1));
        }
    }
}
