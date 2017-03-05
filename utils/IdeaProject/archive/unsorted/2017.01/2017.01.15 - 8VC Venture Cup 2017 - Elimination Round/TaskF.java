package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskF {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i< n; i++) {
            a[i] = in.nextInt() - 1;
        }
        boolean[] was = new boolean[n];
        int[] cycles = new int[n];
        int cn = 0;
        for (int i = 0; i < n; i++) {
            if (was[i]) continue;
            int v = i;
            int cc = 0;
            while (!was[v]) {
                ++cc;
                was[v] = true;
                v = a[v];
            }
            cycles[cn++] = cc;
        }
        cycles = Arrays.copyOf(cycles, cn);
        ArrayUtils.sort(cycles);
        int min = 0;
        int have = k;
        for (int i : cycles) {
            if (have == 0) break;
            if (have >= i) {
                have -= i;
                min += i;
            } else {
                min += have + 1;
                have = 0;
                break;
            }
        }
        int max = 0;
        have = k;
        for (int i : cycles) {
            if (have == 0) break;
            if (have >= i / 2) {
                have -= i / 2;
                max += i / 2 * 2;
            } else {
                max += have * 2;
                have = 0;
            }
        }
        max = Math.min(n, max + have);
        out.println(min + " " + max);
    }
}
