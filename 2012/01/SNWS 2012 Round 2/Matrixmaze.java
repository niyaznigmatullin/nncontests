package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class Matrixmaze {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = in.nextInt();
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = i; j < 10; j++) {
                for (int k = j; k < 10; k++) {
                    if (canGo((1 << i) | (1 << j) | (1 << k), a, n, m)) {
                        out.println(i + " " + j + " " + k);
                        return;
                    }
                }
            }
        }
        out.println("-1 -1 -1");
	}

    static final int[] DX = {1, 0, -1, 0};
    static final int[] DY = {0, 1, 0, -1};
    static boolean canGo(int mask, int[][] a, int n, int m) {
        int[] qx = new int[n * m];
        int[] qy = new int[n * m];
        int head = 0;
        int tail = 0;
        boolean[][] was = new boolean[n][m];
        for (int i = 0; i < m; i++) {
            if (((mask >> a[0][i]) & 1) == 1) {
                was[0][i] = true;
                qx[tail] = 0;
                qy[tail++] = i;
            }
        }
        while (head < tail) {
            int curX = qx[head];
            int curY = qy[head++];
            for (int dir = 0; dir < 4; dir++) {
                int x = curX + DX[dir];
                int y = curY + DY[dir];
                if (x < 0 || y < 0 || x >= n || y >= m || was[x][y] || ((mask >> a[x][y]) & 1) == 0) {
                    continue;
                }
                was[x][y] = true;
                if (x == n - 1) {
                    return true;
                }
                qx[tail] = x;
                qy[tail++] = y;
            }
        }
        return false;
    }
}
