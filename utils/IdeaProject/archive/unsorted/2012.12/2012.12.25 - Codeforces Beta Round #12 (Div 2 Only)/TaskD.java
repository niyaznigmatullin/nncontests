package coding;

import ru.ifmo.niyaz.DataStructures.FenwickMin;
import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.io.FastScanner;

import java.util.Arrays;
import java.util.Comparator;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        final int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            c[i] = in.nextInt();
        }
        int[] nb = ArrayUtils.sortAndUnique(b);
        for (int i = 0; i < n; i++) {
            b[i] = Arrays.binarySearch(nb, b[i]);
        }
        FenwickMin f = new FenwickMin(nb.length);
        int ans = 0;
        long[] z = new long[n];
        for (int i = 0; i < n; i++) {
            z[i] = ((long) a[i] << 32) | i;
        }
        Arrays.sort(z);
        int[] index = new int[n];
        for (int i = 0; i < n; i++) {
            index[i] = (int) (z[i] & 0xFFFFFFFFL);
        }
        int m = nb.length;
        for (int i = n - 1; i >= 0; ) {
            int j = i;
            int ini = index[i];
            int inj;
            while (j >= 0 && a[ini] == a[inj = index[j]]) {
                if (-f.getMin(m - b[inj] - 2) > c[inj]) {
                    ++ans;
                }
                --j;
            }
            while (i > j) {
                ini = index[i];
                f.setAndMin(m - b[ini] - 1, -c[ini]);
                --i;
            }
        }
        out.println(ans);
    }

}
