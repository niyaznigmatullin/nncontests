package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Task1400 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] a = new int[n][n];
        for (int[] d : a) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            if (i - m >= 0) {
                a[i][i - m] = 1;
            }
            if (i + m < n) {
                a[i][i + m] = 1;
            }
            a[i][(i + 1) % n] = 1;
            a[i][(i + n - 1) % n] = 1;
            a[i][i] = 0;
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i][k] == Integer.MAX_VALUE || a[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    a[i][j] = Math.min(a[i][j], a[i][k] + a[k][j]);
                }
            }
        }
        int best = Integer.MAX_VALUE;
        int bestI = -1;
        for (int i = 0; i < n; i++) {
            int cur = 0;
            for (int j = 0; j < n; j++) {
                cur += a[i][j];
            }
            if (cur < best) {
                best = cur;
                bestI = i;
            }
        }
        out.printf("Mean = %.2f", (1. * best / n));
        for (int i = 0; i < n; i++) {
            if (i % m == 0) {
                out.println();
            } else {
                out.print(' ');
            }
            out.print(a[bestI][i]);
        }
        out.println();
    }
}
