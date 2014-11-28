package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {

    static final int[] DX = {1, 0, -1, 0};
    static final int[] DY = {0, 1, 0, -1};
    static final char[] DIRS = "DRUL".toCharArray();

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        in.nextInt();
        char[][] c = in.readCharacterFieldTokens(n, m);
        int[][] count = new int[n][m];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int dir = 0; dir < 4; dir++) {
                    int x = i + DX[dir] * i;
                    int y = j + DY[dir] * i;
                    if (x < 0 || y < 0 || x >= n || y >= m) continue;
                    if (c[x][y] == DIRS[dir + 2 & 3]) {
                        count[i][j]++;
                    }
                }
            }
        }
        for (int col = 0; col < m; col++) {
            int curCount = 0;
            for (int i = 0; i < n; i++) {
                curCount += count[i][col];
            }
            if (col > 0) out.print(' ');
            out.print(curCount);
        }
        out.println();
    }
}
