package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] c = in.readCharacterFieldTokens(n, m);
        int startX = -1;
        int startY = -1;
        int finishX = -1;
        int finishY = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] == 'S') {
                    startX = i;
                    startY = j;
                } else if (c[i][j] == 'T') {
                    finishX = i;
                    finishY = j;
                }
            }
        }
        int[] q = new int[n * m * 3 * 4];
        int head = 0;
        int tail = 0;
        boolean[][][][] was = new boolean[3][4][n][m];
        for (int dir = 0; dir < 4; dir++) {
            q[tail++] = (startX * m + startY) + dir * n * m;
            was[0][dir][startX][startY] = true;
        }
        final int[] DX = {1, 0, -1, 0};
        final int[] DY = {0, 1, 0, -1};
        while (head < tail) {
            int v = q[head++];
            int turn = v / (n * m * 4);
            int dir = v / (n * m) % 4;
            int cx = v / m % n;
            int cy = v % m;
            if (c[cx][cy] == 'T') {
                out.println("YES");
                return;
            }
            for (int nDir = 0; nDir < 4; nDir++) {
                int nTurn = turn + (nDir == dir ? 0 : 1);
                if (nTurn > 2) continue;
                int nx = cx + DX[nDir];
                int ny = cy + DY[nDir];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m || c[nx][ny] == '*' || was[nTurn][nDir][nx][ny]) continue;
                was[nTurn][nDir][nx][ny] = true;
                q[tail++] = nTurn * 4 * n * m + nDir * n * m + nx * m + ny;
            }
        }
        out.println("NO");
    }
}
