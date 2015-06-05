package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        ArrayUtils.sort(a);
        ArrayUtils.reverse(a);
        long ans = 0;
        for (int i = 0; i + 1 < n; ) {
            if (a[i + 1] < a[i] - 1) {
                i++;
                continue;
            }
            int j = i + 2;
            while (j + 1 < n && a[j + 1] < a[j] - 1) {
                ++j;
            }
            if (j + 1 >= n) break;
            ans += (long) a[i + 1] * a[j + 1];
            i = j + 2;
        }
        out.println(ans);
    }
}
