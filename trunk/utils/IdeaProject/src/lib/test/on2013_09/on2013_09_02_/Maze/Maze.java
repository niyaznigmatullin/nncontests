package lib.test.on2013_09.on2013_09_02_.Maze;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Maze {

    static int[] DX = {1, 0, -1, 0};
    static int[] DY = {0, 1, 0, -1};
    static String DIRS = "SENW";

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        boolean[][][] canGo = new boolean[4][100][100];;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                String s = in.next();
                for (char c : s.toCharArray()) {
                    canGo[DIRS.indexOf(c)][i][j] = true;
                }
            }
        }
        int[][] d = new int[100][100];
        for (int[] e : d) {
            Arrays.fill(e, Integer.MAX_VALUE);
        }
        int[] q = new int[100 * 100];
        int head = 0;
        int tail = 0;
        q[tail++] = 0;
        d[0][0] = 0;
        int ans = 0;
        while (head < tail) {
            int v = q[head++];
            int cx = v / 100;
            int cy = v % 100;
            ans += d[cx][cy];
            for (int dir = 0; dir < 4; dir++) {
                if (!canGo[dir][cx][cy]) continue;
                int nx = cx + DX[dir];
                int ny = cy + DY[dir];
                if (d[nx][ny] == Integer.MAX_VALUE) {
                    d[nx][ny] = d[cx][cy] + 1;
                    q[tail++] = nx * 100 + ny;
                }
            }
        }
        out.println(ans > 1700000 ? "KRUSKAL" : "PRIM");
    }
}
