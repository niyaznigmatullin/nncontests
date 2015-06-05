package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskD {

    static char[][] ans;
    static char[][] c;

    static int[][] right;
    static int[][] down;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
//        char[][] c = new char[n + 2][m + 2];
//        for (char[] d : c) Arrays.fill(d, '#');
//        for (int i = 1; i <= n; i++) {
//            char[] z = in.next().toCharArray();
//            for (int j = 1; j <= m; j++) {
//                c[i][j] = z[j - 1];
//            }
//        }
        c = in.readCharacterFieldTokens(n, m);
        char[][] d = new char[n * 2 + 1][m * 2 + 1];
        for (char[] e : d) Arrays.fill(e, '.');
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] == '*') {
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            d[2 * i + 1 + dx][2 * j + 1 + dy] = '*';
                        }
                    }
                }
            }
        }
        n = n * 2 + 1;
        m = m * 2 + 1;
        c = d;
        right = new int[n][m];
        down = new int[n][m];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (c[i][j] != '*') continue;
                right[i][j] = down[i][j] = 1;
                if (i + 1 < n) {
                    down[i][j] += down[i + 1][j];
                }
                if (j + 1 < m) {
                    right[i][j] += right[i][j + 1];
                }
            }
        }
        boolean had = false;
        ans = new char[n][m];
        for (char[] f : ans) Arrays.fill(f, '.');
        for (int i = 0; i < n; i++) {
            if (right[i][0] >= m) {
                boolean was = goHorizontal(i, 0, 0, n, m);
                had = true;
                if (was) break;
            }
        }
        if (!had) {
            for (int i = 0; i < m; i++) {
                if (down[0][i] >= n) {
                    boolean was = goVertical(i, 0, 0, n, m);
                    if (was) break;
                }
            }
        }
        n /= 2;
        m /= 2;
        for (int i = 0; i < n; i++) {
            char[] f = new char[m];
            for (int j = 0; j < m; j++) {
                f[j] = ans[i * 2 + 1][j * 2 + 1];
            }
            out.println(f);
        }
    }

    static boolean goHorizontal(int z, int x1, int y1, int x2, int y2) {
        for (int i = y1; i < y2; i++) {
            ans[z][i] = '*';
        }
        int last = y1;
        for (int i = y1; i < y2; i++) {
            if (down[x1][i] >= z - x1) {
                boolean was = goVertical(i, x1, last, z, y2);
                if (was) break;
                last = i + 1;
            }
        }
        last = y1;
        boolean ret = false;
        for (int i = y1; i < y2; i++) {
            if (down[z][i] >= x2 - z) {
                boolean was = goVertical(i, z + 1, last, x2, y2);
                ret = true;
                if (was) break;
                last = i + 1;
            }
        }
        return ret;
    }

    static boolean goVertical(int z, int x1, int y1, int x2, int y2) {
        for (int i = x1; i < x2; i++) {
            ans[i][z] = '*';
        }
        int last = x1;
        for (int i = x1; i < x2; i++) {
            if (right[i][y1] >= z - y1) {
                boolean was = goHorizontal(i, last, y1, x2, z);
                if (was) break;
                last = i + 1;
            }
        }
        last = x1;
        boolean ret = false;
        for (int i = x1; i < x2; i++) {
            if (right[i][z] >= y2 - z) {
                boolean was = goHorizontal(i, last, z + 1, x2, y2);
                ret = true;
                if (was) break;
                last = i + 1;
            }
        }
        return ret;
    }
}
