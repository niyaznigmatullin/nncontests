package lib.test.on2013_07.on2013_07_20_Codeforces_Round__192__Div__1_.B___Biridian_Forest;



import com.sun.org.apache.bcel.internal.generic.DUP_X1;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();

        int m = in.nextInt();
        char[][] c = in.readCharacterFieldTokens(n, m);
        int[] q = new int[n * m];
        int head = 0;
        int tail = 0;
        int[][] d = new int[n][m];
        for (int[] e : d) {
            Arrays.fill(e, Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] == 'E') {
                    q[tail++] = i * m + j;
                    d[i][j] = 0;
                }
            }
        }
        final int[] DX = {1, 0, -1, 0};
        final int[] DY = {0, 1, 0, -1};
        while (head < tail) {
            int v = q[head++];
            int x = v / m;
            int y = v % m;
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + DX[dir];
                int ny = y + DY[dir];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m || d[nx][ny] != Integer.MAX_VALUE || c[nx][ny] == 'T') {
                    continue;
                }
                q[tail++] = nx * m + ny;
                d[nx][ny] = d[x][y] + 1;
            }
        }
        int our = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] == 'S') {
                    our = d[i][j];
                }
            }
        }
        if (our == Integer.MAX_VALUE) {
            out.println(-1);
            return;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (Character.isDigit(c[i][j])) {
                    if (d[i][j] <= our) {
                        ans += c[i][j] - '0';
                    }
                }
            }
        }
        out.println(ans);
    }
}
