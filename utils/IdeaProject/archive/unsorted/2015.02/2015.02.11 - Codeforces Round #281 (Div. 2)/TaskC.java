package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        ArrayUtils.sort(a);
        int m = in.nextInt();
        int[] b = in.readIntArray(m);
        ArrayUtils.sort(b);
        int ans = 3 * n - 3 * m;
        int first = 3 * n;
        if (2 * n - 2 * m > ans || 2 * n - 2 * m == ans && 2 * n > first) {
            ans = 2 * n - 2 * m;
            first = 2 * n;
        }
        for (int i = 0, j = 0; i < a.length || j < b.length; ) {
            int x = j >= b.length || i < a.length && a[i] < b[j] ? a[i] : b[j];
            while (i < a.length && a[i] <= x) ++i;
            while (j < b.length && b[j] <= x) ++j;
            int curA = 2 * i + 3 * (n - i);
            int curB = 2 * j + 3 * (m - j);
            if (curA - curB > ans || curA - curB == ans && curA > first) {
                ans = curA - curB;
                first = curA;
            }
        }
        out.println(first + ":" + (first - ans));
    }
}
