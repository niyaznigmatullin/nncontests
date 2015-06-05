package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        char[][] c = in.readCharacterFieldTokens(n, n);
        int[][] ans = new int[2 * n - 1][2 * n - 1];
        for (int[] d : ans) {
            Arrays.fill(d, 1);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (c[i][j] == 'o') {
                    for (int dx = -n + 1; dx < n; dx++) {
                        for (int dy = -n + 1; dy < n; dy++) {
                            int x = i + dx;
                            int y = j + dy;
                            if (x < 0 || y < 0 || x >= n || y >= n) continue;
                            if (c[x][y] == '.') {
                                ans[dx + n - 1][dy + n - 1] = 0;
                            }
                        }
                    }
                }
            }
        }
        int[][] count = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (c[i][j] == 'o') {
                    for (int dx = -n + 1; dx < n; dx++) {
                        for (int dy = -n + 1; dy < n; dy++) {
                            int x = i + dx;
                            int y = j + dy;
                            if (x < 0 || y < 0 || x >= n || y >= n) continue;
                            if (ans[dx + n - 1][dy + n - 1] != 0) {
                                count[x][y]++;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (c[i][j] == 'x' && count[i][j] == 0) {
                    out.println("NO");
                    return;
                }
            }
        }
        out.println("YES");
        for (int i = 0; i < 2 * n - 1; i++) {
            for (int j = 0; j < 2 * n - 1; j++) {
                if (i == n - 1 && j == n - 1) out.print('o');
                else if (ans[i][j] == 1) out.print('x');
                else
                    out.print('.');
            }
            out.println();
        }
    }
}
