package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = in.nextInt();
            }
        }
        int[][] b = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                b[i][j] = 1;
                for (int k = 0; k < n; k++) {
                    b[i][j] &= a[k][j];
                }
                for (int k = 0; k < m; k++) {
                    b[i][j] &= a[i][k];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int x= 0;
                for (int k = 0; k < n; k++) x |= b[k][j];
                for (int k = 0; k < m; k++) x |= b[i][k];
                if (x != a[i][j]) {
                    out.println("NO");
                    return;
                }
            }
        }
        out.println("YES");
        for (int i = 0; i < n; i++) {
            out.printArray(b[i]);
        }
    }
}
