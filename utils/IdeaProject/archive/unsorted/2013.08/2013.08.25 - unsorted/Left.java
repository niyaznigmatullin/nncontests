package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Left {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] go = new int[n][m];
        int[][] nc = new int[n][m];
        int[][] move = new int[n][m];
        for (int i = 0; i + 1 < n; i++) {
            for (int j = 0; j < m; j++) {
                int to = in.nextInt() - 1;
                int ch = in.nextInt() - 1;
                int z = in.nextInt();
                go[i][j] = to;
                nc[i][j] = ch;
                move[i][j] = z;
            }
        }
        int[] q = new int[n * m * 2];
        int head = 0;
        int tail = 0;
        boolean[][][] was = new boolean[2][n][m];
        for (int i = 0; i < m; i++) {
            if (i == m - 1) {
                was[1][0][i] = true;
                q[tail++] = i + n * m;
            } else {
                q[tail++] = i;
                was[0][0][i] = true;
            }
        }
        boolean ans = false;
        while (head < tail) {
            int vv = q[head++];
            int zz = 0;
            if (vv >= n * m) {
                zz = 1;
                vv -= n * m;
            }
            int v = vv / m;
            int c = vv % m;
            if (move[v][c] < 0) {
                ans = true;
                break;
            }
            if (move[v][c] == 0 && !was[zz][go[v][c]][nc[v][c]]) {
                q[tail++] = go[v][c] * m + nc[v][c] + zz * n * m;
                was[zz][go[v][c]][nc[v][c]] = true;
            }
            if (move[v][c] == 1) {
                if (zz == 1) {
                    if (!was[zz][go[v][c]][m - 1]) {
                        was[zz][go[v][c]][m - 1] = true;
                        q[tail++] = go[v][c] * m + m - 1 + n * m;
                    }
                } else {
                    for (int i = 0; i < m; i++) {
                        if (i != m - 1) {
                            if (!was[0][go[v][c]][i]) {
                                was[0][go[v][c]][i] = true;
                                q[tail++] = go[v][c] * m + i;
                            }
                        } else {
                            if (!was[1][go[v][c]][m - 1]) {
                                was[1][go[v][c]][m - 1] = true;
                                q[tail++] = go[v][c] * m + m - 1 + n * m;
                            }
                        }
                    }
                }
            }
        }
        out.println(ans ? "NO" : "YES");
    }
}
