package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Task1968 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] a = in.readInt2DArray(n, m);
        int[][] count = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                count[i][j] = a[i][j];
                if (count[i][j] != 0) {
                    a[i][j] -= count[i][j];
                    a[i + 1][j] -= count[i][j];
                    a[i][j + 1] -= count[i][j];
                    a[i + 1][j + 1] -= count[i][j];
                }
            }
        }
        for (int h = n / 2, w = m / 2, dx = 0, dy = 0; h > 0 && w > 0; h--, w--, dx++, dy++) {
            char[][] ans = new char[h][w];
            for (char[] e : ans) {
                Arrays.fill(e, 'W');
            }
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (count[2 * i + dx][2 * j + dy] > 0) {
                        --count[2 * i + dx][2 * j + dy];
                        ans[i][j] = 'R';
                    }
                }
            }
            for (char[] e : ans) {
                out.println(e);
            }
            out.println();
        }
    }
}
