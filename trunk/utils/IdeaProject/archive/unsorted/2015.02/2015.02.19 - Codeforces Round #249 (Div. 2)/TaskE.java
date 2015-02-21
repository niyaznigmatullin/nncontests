package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskE {

    static boolean check(char[][] c, char[][] pattern) {
        int n = c.length;
        int m = c[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (pattern[i][j] == '0') continue;
                if (pattern[i][j] != c[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    static char[][] solve(char[][] c) {
        int n = c.length;
        int m = c[0].length;
        char[][] d = new char[n][m];
        int[] p = new int[]{1, 2, 3, 4};
        do {
            for (int i = 0, cur = 0; i < n; i++, cur ^= 2) {
                int xor = 0;
                for (int j = 0; j < m; j++) {
                    if (c[i][j] - '0' == p[cur]) {
                        xor = (j & 1);
                        break;
                    }
                    if (c[i][j] - '0' == p[cur + 1]) {
                        xor = ((j ^ 1) & 1);
                        break;
                    }
                }
                for (int j = 0; j < m; j++) {
                    d[i][j] = (char) ('0' + p[cur + ((j ^ xor) & 1)]);
                }
            }
            if (check(d, c)) {
                return d;
            }
        } while (ArrayUtils.nextPermutation(p));
        return null;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] c = in.readCharacterFieldTokens(n, m);
        char[][] d = solve(c);
        boolean flipped = false;
        if (d == null) {
            c = ArrayUtils.rotateCounterclockwise(c);
            d = solve(c);
            flipped = true;
        }
        if (d == null) {
            out.println(0);
        } else {
            if (flipped) {
                d = ArrayUtils.rotateClockwise(d);
            }
            for (char[] e : d) {
                out.println(e);
            }
        }
    }
}
