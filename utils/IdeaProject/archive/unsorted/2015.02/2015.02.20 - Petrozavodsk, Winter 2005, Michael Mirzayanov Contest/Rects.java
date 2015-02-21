package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.BitSet;

public class Rects {

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] xs = new int[2 * n];
        int[] ys = new int[2 * n];
        int[] x1 = new int[n];
        int[] y1 = new int[n];
        int[] x2 = new int[n];
        int[] y2 = new int[n];
        for (int i = 0; i < n; i++) {
            x1[i] = in.nextInt();
            y1[i] = in.nextInt();
            x2[i] = in.nextInt();
            y2[i] = in.nextInt();
            if (x1[i] > x2[i]) {
                int t = x1[i];
                x1[i] = x2[i];
                x2[i] = t;
            }
            if (y1[i] > y2[i]) {
                int t = y1[i];
                y1[i] = y2[i];
                y2[i] = t;
            }
            xs[2 * i] = x1[i];
            xs[2 * i + 1] = x2[i];
            ys[2 * i] = y1[i];
            ys[2 * i + 1] = y2[i];
        }
        xs = ArrayUtils.sortAndUnique(xs);
        ys = ArrayUtils.sortAndUnique(ys);
        for (int i = 0; i < n; i++) {
            x1[i] = Arrays.binarySearch(xs, x1[i]);
            x2[i] = Arrays.binarySearch(xs, x2[i]);
            y1[i] = Arrays.binarySearch(ys, y1[i]);
            y2[i] = Arrays.binarySearch(ys, y2[i]);
        }
        BitSet[] bs = new BitSet[xs.length];
        for (int i = 0; i < xs.length; i++) {
            bs[i] = new BitSet(ys.length);
        }
        int[] ans = new int[n];
        int ac = 0;
        for (int i = 0; i < n; i++) {
            boolean ok = true;
            for (int x = x1[i]; x < x2[i]; x++) {
                if (ok && bs[x].nextClearBit(y1[i]) < y2[i]) {
                    ok = false;
                }
                if (!ok) {
                    bs[x].set(y1[i], y2[i], true);
                }
            }
            if (ok) ans[ac++] = i + 1;
        }
        out.println(ac);
        out.printArray(Arrays.copyOf(ans, ac));
    }
}
