package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskE {

    static long[] ds;
    static int[][] cs;
    static long[] ans = new long[100000];
    static int cn;

    static boolean go(int x, long k, int who) {
        if (who == 0 || x == k) {
            ans[cn++] = ds[who];
            return cn >= ans.length;
        }
        for (int i = 0; i < cs[who].length; i++) {
            if (go(x + 1, k, cs[who][i])) {
                return true;
            }
        }
        return false;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long x = in.nextLong();
        long k = in.nextLong();
        List<Long> divs = new ArrayList<>();
        for (long i = 1; i * i <= x; i++) {
            if (x % i != 0) continue;
            divs.add(i);
            if (i * i != x) divs.add(x / i);
        }
        Collections.sort(divs);
        ds = ArrayUtils.toPrimitiveArrayLong(divs);
        cs = new int[ds.length][];
        for (int i = 0; i < ds.length; i++) {
            int cc = 1;
            for (int j = 0; j < i; j++) {
                if (ds[i] % ds[j] == 0) cc++;
            }
            cs[i] = new int[cc];
            cc = 0;
            for (int j = 0; j <= i; j++) {
                if (ds[i] % ds[j] == 0) {
                    cs[i][cc++] = j;
                }
            }
        }
        cn = 0;
        go(0, k, ds.length - 1);
        for (int i = 0; i < cn; i++) {
            if (i > 0) out.print(' ');
            out.print(ans[i]);
        }
        out.println();
    }
}
