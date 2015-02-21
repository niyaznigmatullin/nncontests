package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Cinema {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        long minimal = (long) n * (n + 1) / 2 * k;
        long maximal = (long) n * (n - 1) / 2 * (n - k + 1) + (long) n * ((long) n * (k - 1) + 1);
        out.println(maximal);
        if (n <= 100) {
            int[][] a = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = k - 1; j < n; j++) {
                    a[i][j] = n * (k - 1) + (n - k + 1) * i + j - (k - 1) + 1;
                }
            }
            int cn = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i][j] == 0) {
                        a[i][j] = ++cn;
                    }
                }
            }
            for (int[] e : a) {
                out.printArray(e);
            }
        }
        out.println(minimal);
        if (n <= 100) {
            int[][] a = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < k; j++) {
                    a[i][j] = i * k + j + 1;
                }
            }
            int cn = n * k;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i][j] == 0) {
                        a[i][j] = ++cn;
                    }
                }
            }
            for (int[] e : a) {
                out.printArray(e);
            }
        }
    }
}
