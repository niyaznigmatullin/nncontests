package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskD {

    static int[] DX = {1, 0, -1, 0};
    static int[] DY = {0, 1, 0, -1};

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] c = in.next().toCharArray();
            for (int j = 0; j < m; j++) {
                int x;
                if (c[j] == '+') {
                    x = 15;
                } else if (c[j] == '-') {
                    x = 10;
                } else if (c[j] == '|') {
                    x = 5;
                } else if (c[j] == '^') {
                    x = 4;
                } else if (c[j] == '>') {
                    x = 2;
                } else if (c[j] == 'v') {
                    x = 1;
                } else if (c[j] == '<') {
                    x = 8;
                } else if (c[j] == 'L') {
                    x = 15 - 8;
                } else if (c[j] == 'R') {
                    x = 15 - 2;
                } else if (c[j] == 'U') {
                    x = 15 - 4;
                } else if (c[j] == 'D') {
                    x = 15 - 1;
                } else if (c[j] == '*') {
                    x = 0;
                } else throw new AssertionError();
                a[i][j] = x;
            }
        }
        int[] q = new int[n * m * 4];
        int[] d = new int[n * m * 4];
        Arrays.fill(d, Integer.MAX_VALUE);
        int head = 0;
        int tail = 0;
        int startX = in.nextInt() - 1;
        int startY = in.nextInt() - 1;
        int finishX = in.nextInt() - 1;
        int finishY = in.nextInt() - 1;
        q[tail++] = startX * m + startY;
        d[q[0]] = 0;
        while (head < tail) {
            int v = q[head++];
            int turn = v / n / m;
            int cx = v / m % n;
            int cy = v % m;
            if (cx == finishX && cy == finishY) {
                out.println(d[v]);
                return;
            }
            for (int dir = 0; dir <= 4; dir++) {
                int nx, ny, nturn;
                if (dir == 4) {
                    nx = cx;
                    ny = cy;
                    nturn = (turn + 1) & 3;
                } else {
                    nx = cx + DX[dir];
                    ny = cy + DY[dir];
                    nturn = turn;
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                    if (((a[cx][cy] >> ((dir + turn) & 3)) &1 ) == 0 || (((a[nx][ny] >> (2 ^ ((dir + turn) & 3)))) & 1) == 0) {
                        continue;
                    }
                }
                int nv = nturn * n * m + nx * m + ny;
                if (d[nv] != Integer.MAX_VALUE) {
                    continue;
                }
//                System.out.println(turn + " " + cx + " " + cy + " " + d[v] + " " + dir + " " + nturn + " " + nx + " " + ny);
                d[nv] = d[v] + 1;
                q[tail++] = nv;
            }
        }
        out.println(-1);
    }
}
