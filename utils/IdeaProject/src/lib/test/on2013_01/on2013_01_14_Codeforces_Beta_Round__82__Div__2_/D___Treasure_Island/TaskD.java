package lib.test.on2013_01.on2013_01_14_Codeforces_Beta_Round__82__Div__2_.D___Treasure_Island;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] c = in.readCharacterFieldTokens(n, m);
        int[][][] can = new int[4][n][m];
        final String DIRS = "NESW";
        final int[] DX = {-1, 0, 1, 0};
        final int[] DY = {0, 1, 0, -1};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] == '#') {
                    can[0][i][j] = can[3][i][j] = 0;
                } else {
                    can[0][i][j] = (i > 0 ? can[0][i - 1][j] : 0) + 1;
                    can[3][i][j] = (j > 0 ? can[3][i][j - 1] : 0) + 1;
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (c[i][j] == '#') {
                    can[1][i][j] = can[2][i][j] = 0;
                } else {
                    can[1][i][j] = (j + 1 < m ? can[1][i][j + 1] : 0) + 1;
                    can[2][i][j] = (i + 1 < n ? can[2][i + 1][j] : 0) + 1;
                }
            }
        }
        int q = in.nextInt();
        int[] dir = new int[q];
        int[] step = new int[q];
        for (int i = 0; i < q; i++) {
            dir[i] = DIRS.indexOf(in.next());
            step[i] = in.nextInt();
        }
        boolean[] ans = new boolean[26];
        for (int i = 0; i < n; i++) {
            all: for (int j = 0; j < m; j++) {
                if (c[i][j] == '.' || c[i][j] == '#') {
                    continue;
                }
                int x = i;
                int y = j;
                for (int k = 0; k < q; k++) {
                    if (can[dir[k]][x][y] - 1 < step[k]) {
                        continue all;
                    }
                    x += DX[dir[k]] * step[k];
                    y += DY[dir[k]] * step[k];
                }
                ans[c[i][j] - 'A'] = true;
            }
        }
        int cnt = 0;
        for (int i = 0; i < 26; i++) {
            if (ans[i]) {
                out.print((char) (i + 'A'));
                ++cnt;
            }
        }
        if (cnt == 0) {
            out.print("no solution");
        }
        out.println();
    }
}
