package lib.test.on2013_08.on2013_08_24_.Straight;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Straight {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String[] line = in.nextLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        char[][] field = new char[2 * n - 1][];
        for (int i = 0; i < 2 * n - 1; i++) {
            StringBuilder z = new StringBuilder();
            z.append(in.nextLine());
            while (z.length() < 2 * m - 1) z.append(' ');
            field[i] = z.toString().toCharArray();
        }
        final int[] dx = {1, 0, -1, 0};
        final int[] dy = {0, 1, 0, -1};
        boolean[][][] canGo = new boolean[4][n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int dir = 0; dir < 4; dir++) {
                    int x = 2 * i + dx[dir];
                    int y = 2 * j + dy[dir];
                    if (x < 0 || y < 0 || x >= 2 * n - 1 || y >= 2 * m - 1) continue;
                    if (field[x][y] != ' ') {
                        canGo[dir][i][j] = true;
                    }
                }
            }
        }
        int[] q = new int[n * m];
        int[][] d = new int[n][m];
        for (int[] e : d) {
            Arrays.fill(e, Integer.MAX_VALUE);
        }
        int head = 0;
        int tail = 1;
        q[0] = m - 1;
        d[0][m - 1] = 0;
        while (head < tail) {
            int v = q[head++];
            int cx = v / m;
            int cy = v % m;
            for (int dir = 0; dir < 4; dir++) {
                if (!canGo[dir][cx][cy]) continue;
                int nx = cx + dx[dir];
                int ny = cy + dy[dir];
                if (d[nx][ny] == Integer.MAX_VALUE) {
                    d[nx][ny] = d[cx][cy] + 1;
                    q[tail++] = nx * m + ny;
                }
            }
        }
//        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(d[i]));
//        }
        int cx, cy, cd;
        if (!canGo[1][n - 1][0] || (canGo[2][n - 1][0] && d[n - 2][0] < d[n - 1][1])) {
            out.println('N');
            cx = n - 2;
            cy = 0;
            cd = 2;
        } else {
            out.println('E');
            cx = n - 1;
            cy = 1;
            cd = 1;
        }
        while (cx != 0 || cy != m - 1) {
//            System.out.println(cx + " " + cy + " " + cd);
            if (canGo[cd][cx][cy] && d[cx + dx[cd]][cy + dy[cd]] == d[cx][cy] - 1) {
                out.print('F');
                cx += dx[cd];
                cy += dy[cd];
            } else {
                boolean found = false;
                for (int dir = 0; dir < 4; dir++) {
                    if (canGo[dir][cx][cy] && d[cx + dx[dir]][cy + dy[dir]] == d[cx][cy] - 1) {
                        if ((cd + 1 & 3) == dir) {
                            out.print('L');
                        } else {
                            out.print('R');
                        }
                        cd = dir;
                        cx += dx[dir];
                        cy += dy[dir];
                        found = true;
                        break;
                    }
                }
                if (!found) throw new AssertionError();
            }
        }
        out.println();
    }
}
