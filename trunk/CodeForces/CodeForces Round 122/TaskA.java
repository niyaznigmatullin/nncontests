package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] c = new char[n][];
        int count = 0;
        for (int i = 0; i < n; i++) {
            c[i] = in.next().toCharArray();
            for (int j = 0; j < m; j++) {
                if (c[i][j] == '#') {
                    ++count;
                }
            }
        }
        if (count <= 2) {
            out.println(-1);
            return;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] != '#') {
                    continue;
                }
                c[i][j] = '.';
                if (!connected(c, n, m)) {
                    out.println(1);
                    return;
                }
                c[i][j] = '#';
            }
        }
        out.println(2);
    }

    static final int[] DX = {1, 0, -1, 0};
    static final int[] DY = {0, 1, 0, -1};

    static boolean connected(char[][] c, int n, int m) {
        boolean[][] was = new boolean[n][m];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (was[i][j] || c[i][j] != '#') {
                    continue;
                }
                dfs(i, j, c, was, n, m);
                ++count;
            }
        }
        return count == 1;
    }

    static void dfs(int i, int j, char[][] c, boolean[][] was, int n, int m) {
        was[i][j] = true;
        for (int dir = 0; dir < 4; dir++) {
            int x = i + DX[dir];
            int y = j + DY[dir];
            if (x < 0 || y < 0 || x >= n || y >= m || c[x][y] != '#' || was[x][y]) {
                continue;
            }
            dfs(x, y, c, was, n, m);
        }
    }
}
