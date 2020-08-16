package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        ArrayUtils.sort(a);
        int eq = 0;
        for (int i = 1; i < n; i++) if (a[i] == a[i - 1]) eq++;
        String first = "sjfnb";
        String second = "cslnb";
        if (eq > 1) {
            out.println(second);
            return;
        }
        if (eq == 1) {
            for (int i = 1; i < n; i++) {
                if (a[i] == a[i - 1]) {
                    if (a[i] == 0 || i > 1 && a[i - 1] == a[i - 2] + 1) {
                        out.println(second);
                        return;
                    }
                    a[i]--;
                    String t = first;
                    first = second;
                    second = t;
                    break;
                }
            }
        }
        long sum = 0;
        for (int i : a) sum += i;
        long needSum = (n - 1) * (long) n / 2;
        long moves = sum - needSum;
        if (moves % 2 == 0) {
            out.println(second);
        } else {
            out.println(first);
        }
    }
}
