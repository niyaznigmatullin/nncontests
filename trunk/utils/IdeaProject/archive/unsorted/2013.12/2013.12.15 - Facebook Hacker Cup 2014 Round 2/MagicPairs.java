package coding;

import ru.ifmo.niyaz.DataStructures.Fenwick;
import ru.ifmo.niyaz.DataStructures.MaxSegmentTree;
import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class MagicPairs {

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        out.print("Case #" + testNumber + ": ");
        System.err.println(testNumber);
        int n = in.nextInt();
        int m = in.nextInt();
        int x1 = in.nextInt();
        long a1 = in.nextInt();
        long b1 = in.nextInt();
        long c1 = in.nextInt();
        int r1 = in.nextInt();
        int x2 = in.nextInt();
        long a2 = in.nextInt();
        long b2 = in.nextInt();
        long c2 = in.nextInt();
        int r2 = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[m];
        a[0] = x1;
        b[0] = x2;
        for (int i = 1; i < n || i < m; i++) {
            if (i < n) {
                a[i] = (int) ((a1 * a[i - 1] + b1 * b[(i - 1) % m] + c1) % r1);
            }
            if (i < m) {
                b[i] = (int) ((a2 * a[(i - 1) % n] + b2 * b[i - 1] + c2) % r2);
            }
        }
        int[] c = new int[a.length + b.length];
        for (int i = 0; i < c.length; i++) {
            if (i < a.length) {
                c[i] = a[i];
            } else {
                c[i] = b[i - a.length];
            }
        }
        c = ArrayUtils.sortAndUnique(c);
        for (int i = 0; i < n; i++) {
            a[i] = Arrays.binarySearch(c, a[i]);
        }
        for (int i = 0; i < m; i++) {
            b[i] = Arrays.binarySearch(c, b[i]);
        }
        int[] ca = new int[n + 1];
        int[] cb = new int[m + 1];
        int[] addA = getIt(a, c.length, ca);
        int[] addB = getIt(b, c.length, cb);
        int[] f = new int[c.length];
        int zeros = c.length;
        long ans = 0;
        for (int i = 0; i < addA.length && i < addB.length; i++) {
            if (f[addA[i]] == 0) --zeros;
            f[addA[i]]++;
            if (f[addA[i]] == 0) ++zeros;
            if (f[addB[i]] == 0) --zeros;
            f[addB[i]]--;
            if (f[addB[i]] == 0) ++zeros;
            if (zeros == c.length) {
                ans += (long) ca[i + 1] * cb[i + 1];
            }
        }
        out.println(ans);
    }

    int[] getIt(int[] a, int m, int[] ca) {
        int[] f = new int[m];
        int n = a.length;
        int[] ret = new int[n];
        int cn = 0;
        for (int i = 0; i < a.length; i++) {
            if (f[a[i]] == 0) {
                ret[cn++] = a[i];
            }
            ++f[a[i]];
            ca[cn]++;
        }
        return ret;
    }
}
