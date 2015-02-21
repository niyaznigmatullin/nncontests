package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

import java.util.Arrays;

public class TaskE {

    static final int[] DX = {1, 0, -1, 0};
    static final int[] DY = {0, 1, 0, -1};

    static int getMask(char[][] c, int x, int y, boolean[][] last) {
        int ret = (1 << 26) - 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + DX[i];
            int ny = y + DY[i];
            if (nx < 0 || ny < 0 || nx >= c.length || ny >= c[nx].length || c[nx][ny] == 0 || last[nx][ny]) continue;
            ret &= ~(1 << c[nx][ny] - 'A');
        }
        return ret;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] ans = new char[n][m];
        boolean[][] last = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (ans[i][j] != 0) continue;
                int curChar = Integer.numberOfTrailingZeros(getMask(ans, i, j, last));
                ans[i][j] = (char) (curChar + 'A');
                last[i][j] = true;
                int size = 2;
                while (i + size <= n && j + size <= m) {
                    if (Integer.numberOfTrailingZeros(getMask(ans, i, j + size - 1, last)) < curChar) {
                        break;
                    }
                    boolean ok = true;
                    for (int k = 0; k < size; k++) {
                        if (ans[i + size - 1][j + k] != 0 || ans[i + k][j + size - 1] != 0 || ((getMask(ans, i + k, j + size - 1, last) >> curChar) & 1) == 0 ||
                                ((getMask(ans, i + size - 1, j + k, last) >> curChar) & 1) == 0) {
                            ok = false;
                            break;
                        }
                    }
                    if (ok) {
                        for (int k = 0; k < size; k++) {
                            ans[i + size - 1][j + k] = (char) (curChar + 'A');
                            ans[i + k][j + size - 1] = (char) (curChar + 'A');
                            last[i + size - 1][j + k] = true;
                            last[i + k][j + size - 1] = true;
                        }
                    } else break;
                    ++size;
                }
                for (int x = 0; x + 1 < size; x++) {
                    for (int y = 0; y + 1 < size; y++) last[i + x][j + y] = false;
                }
            }
        }
        for (char[] e : ans) out.println(e);
    }
}
