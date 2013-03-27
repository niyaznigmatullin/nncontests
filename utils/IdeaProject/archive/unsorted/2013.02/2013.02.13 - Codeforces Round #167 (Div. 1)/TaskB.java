package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int[] b = in.readIntArray(n);
        int m = in.nextInt();
        int[] c = new int[n + n];
        for (int i = 0; i < n; i++) {
            c[i] = a[i];
            c[i + n] = b[i];
        }
        c = ArrayUtils.sortAndUnique(c);
        int[] cnt = new int[c.length];
        long ans = 1;
        for (int i = 0; i < n; i++) {
            int id1 = Arrays.binarySearch(c, a[i]);
            int id2 = Arrays.binarySearch(c, b[i]);
            if (a[i] == b[i]) {
                long z = cnt[id1];
                z = ((z + 1) * (z + 2) / 2) % m;
                ans = ans * z % m;
                cnt[id1] += 2;
            } else {
                ++cnt[id1];
                ++cnt[id2];
                ans *= cnt[id1];
                ans %= m;
                ans *= cnt[id2];
                ans %= m;
            }
        }
        out.println(ans % m);
    }
}
