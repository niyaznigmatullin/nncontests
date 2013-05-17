package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class I {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[][][] numbers = new int[6][n][n];
        int value = 1;
        for (int it = 0; it < 6; it += 2) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    numbers[it][i][j] = value;
                    numbers[it+1][i][j] = 6 * n * n  +1 - value;
                    ++value;
                }
            }
        }
        for (int i = 0 ; i < n ;i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0) {
                    out.print(' ');
                }
                out.print(numbers[0][i][j]);
            }
            out.println();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0) {
                    out.print(' ');
                }
                out.print(numbers[2][i][j]);
            }
            for (int j = 0; j < n; j++) {
                out.print(' ');
                out.print(numbers[4][i][j]);
            }
            for (int j = 0; j < n; j++) {
                out.print(' ');
                out.print(numbers[3][i][n-1-j]);
            }
            for (int j = 0; j < n; j++) {
                out.print(' ');
                out.print(numbers[5][i][n-1-j]);
            }
            out.println();
        }
        for (int i = 0 ; i < n ;i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0) {
                    out.print(' ');
                }
                out.print(numbers[1][n-1-i][j]);
            }
            out.println();
        }

    }
}
