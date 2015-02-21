package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int[] x = new int[n + 1];
        int[] y = new int[n + 1];
        x[0] = 0;
        y[0] = 0;
        for (int i = 1; i <= n; i++) {
            x[i] = x[i - 1] + a[i - 1];
            y[i] = y[i - 1] + ((i & 1) == 1 ? 1 : -1) * a[i - 1];
        }
        int maxY = 0;
        int minY = 0;
        for (int i : y) {
            maxY = Math.max(maxY, i);
            minY = Math.min(minY, i);
        }
        char[][] c = new char[maxY - minY][x[n]];
        for (char[] e : c) Arrays.fill(e, ' ');
        for (int i = 0; i < n; i++) {
            for (int cx = x[i], cy = y[i]; cx < x[i + 1]; cx++) {
                if ((i & 1) == 0) {
                    c[c.length - (cy - minY) - 1][cx] = '/';
                }
                cy += (i & 1) == 0 ? 1 : -1;
                if ((i & 1) == 1) {
                    c[c.length - (cy - minY) - 1][cx] = '\\';
                }
            }
        }
        for (char[] e : c) out.println(e);
    }
}
