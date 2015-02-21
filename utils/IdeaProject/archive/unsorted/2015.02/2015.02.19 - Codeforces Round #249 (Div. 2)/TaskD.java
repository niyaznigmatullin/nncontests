package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {

    static int go(char[][] c) {
        int n = c.length;
        int m = c[0].length;
        int ans = 0;
        int[][] dp1 = new int[n][m];
        int[][] dp2 = new int[n][m];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] == '1') {
                    dp1[i][j] = dp2[i][j] = 0;
                } else {
                    dp1[i][j] = dp2[i][j] = 1;
                    if (i + 1 < n && j + 1 < m) {
                        dp1[i][j] += dp1[i + 1][j + 1];
                    }
                    if (i + 1 < n && j > 0) {
                        dp2[i][j] += dp2[i + 1][j - 1];
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] == '1') continue;
                for (int k = j + 1; k < m; k++) {
                    if (c[i][k] == '1') break;
                    if ((k - j & 1) == 0) {
                        int step = ((k - j) >> 1);
                        int vx = i - step;
                        int vy = j + step;
                        if (vx < 0) continue;
                        if (dp1[vx][vy] >= step + 1 && dp2[vx][vy] >= step + 1) ++ans;
                    }
                }
                for (int k = 1; k + i < n && k + j < m; k++) {
                    if (c[k + i][j] != '0' || c[i][k + j] != '0') break;
                    if (dp2[i][k + j] >= k + 1) ++ans;
                }
            }
        }
        return ans;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] c = in.readCharacterFieldTokens(n, m);
        int ans = 0;
        for (int a = 0; a < 4; a++) {
            ans += go(c);
            c = ArrayUtils.rotateCounterclockwise(c);
        }
        out.println(ans);
    }
}
