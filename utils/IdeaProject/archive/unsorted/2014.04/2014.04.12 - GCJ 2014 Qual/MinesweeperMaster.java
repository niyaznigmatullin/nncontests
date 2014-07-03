package coding;

import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.io.FastScanner;

public class MinesweeperMaster {

    static boolean[][] field;
    static int[][] countMines;
    static boolean[][] was;
    static int[] q;
    static int ci;
    static int cj;
    static int n;
    static int m;

    static boolean check() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                was[i][j] = false;
                countMines[i][j] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!field[i][j]) continue;
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        int x = i + dx;
                        int y = j + dy;
                        if (x < 0 || y < 0 || x >= n || y >= m) continue;
                        countMines[x][y]++;
                    }
                }
                was[i][j] = true;
            }
        }
        ci = cj = -1;
        all:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (countMines[i][j] == 0) {
                    ci = i;
                    cj = j;
                    break all;
                }
            }
        }
        if (ci < 0) {
            all:
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!field[i][j]) {
                        ci = i;
                        cj = j;
                        break all;
                    }
                }
            }
        }
        int head = 0;
        int tail = 0;
        q[tail++] = ci * m + cj;
        was[ci][cj] = true;
        while (head < tail) {
            int v = q[head++];
            int cx = v / m;
            int cy = v % m;
            if (countMines[cx][cy] == 0) {
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        int nx = cx + dx;
                        int ny = cy + dy;
                        if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                        if (!was[nx][ny]) {
                            q[tail++] = nx * m + ny;
                            was[nx][ny] = true;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!was[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println("[" + testNumber + "]");
        out.println("Case #" + testNumber + ":");
        n = in.nextInt();
        m = in.nextInt();
        int mines = in.nextInt();
        if (n > 5 || m > 5) {
            out.println("Bad input!!!!!");
            return;
        }
        field = new boolean[n][m];
        countMines = new int[n][m];
        was = new boolean[n][m];
        q = new int[n * m];
        for (int mask = 0; mask < 1 << n * m; mask++) {
            if (Integer.bitCount(mask) != mines) {
                continue;
            }
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < m; y++) {
                    int v = x * m + y;
                    field[x][y] = ((mask >> v) & 1) == 1;
                }
            }
            if (check()) {
                char[][] ans = new char[n][m];
                for (int x = 0; x < n; x++) {
                    for (int y = 0; y < m; y++) {
                        if (x == ci && y == cj) {
                            ans[x][y] = 'c';
                        } else if (field[x][y]) {
                            ans[x][y] = '*';
                        } else {
                            ans[x][y] = '.';
                        }
                    }
                }
                for (char[] e : ans) {
                    out.println(e);
                }
                return;
            }
        }
        out.println("Impossible");
    }
}
