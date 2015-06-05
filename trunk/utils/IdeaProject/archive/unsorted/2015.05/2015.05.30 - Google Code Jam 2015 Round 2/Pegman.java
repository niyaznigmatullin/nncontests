package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Pegman {

    static int[] DX = {1, 0, -1, 0};
    static int[] DY = {0, 1, 0, -1};
    static String DIRS = "v>^<";

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] c = in.readCharacterFieldTokens(n, m);
        int ans = 0;
        out.print("Case #" + testNumber + ": ");
        System.err.println(testNumber);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] == '.') continue;
                int mask = 0;
                for (int dir = 0; dir < 4; dir++) {
                    boolean bad = false;
                    for (int k = 1; ; k++) {
                        int x = i + DX[dir] * k;
                        int y = j + DY[dir] * k;
                        if (x < 0 || x >= n || y < 0 || y >= m) {
                            bad = true;
                            break;
                        }
                        if (c[x][y] != '.') {
                            break;
                        }
                    }
                    if (!bad) mask |= 1 << dir;
                }
                if (((mask >> DIRS.indexOf(c[i][j])) & 1) == 1) {
                    continue;
                }
                if (mask == 0) {
                    out.println("IMPOSSIBLE");
                    return;
                }
                ++ans;
            }
        }
        out.println(ans);
    }
}
