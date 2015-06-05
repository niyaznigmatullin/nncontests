package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
//        System.err.println("[" + testNumber + "]");
//        int r = in.nextInt();
//        int c = in.nextInt();
//        int n = in.nextInt();
//        int ans = solveStupid(r, c, n);
        int r = 4;
        int c = 5;
        for (int i = r * c / 2; i <= r * c; i++) solveStupid(r, c, i);
//        int ans2 = solveSmart(r, c, n);
//        if (ans != ans2) {
//            System.out.println(r + " " + c + " " + n);
//            System.out.println(ans + " " + ans2);
//            throw new AssertionError();
//        }
//        out.println("Case #" + testNumber + ": " + ans);
    }

    private int solveSmart(int r, int c, int n) {
        if (r > c) {
            int t = r;
            r = c;
            c = t;
        }
        if (n <= (r * c + 1) / 2) {
            return 0;
        }
        if (r == 1) {
            return Math.max(0, (r * c - 1) - 2 * (r * c - n));
        }
        n = r * c - n;
        int internal = ((r - 2) * (c - 2)) / 2;
        int got4 = Math.min(internal, n);
        n -= got4;
        int ans = r * (c - 1) + c * (r - 1) - 4 * got4;
        int external = ((r - 1) / 2 + (c - 1) / 2) * 2;
        if ((r & 1) != (c & 1)) --external;
        int got3 = Math.min(external, n);
        n -= got3;
        ans -= 3 * got3;
        int got2 = 0;
        if ((r & 1) != 1 || (c & 1) != 1) {
            got2 += 2;
        }
        got2 = Math.min(got2, n);
        n -= got2;
        ans -= 2 * got2;
        return ans;
    }

    private int solveStupid(int r, int c, int n) {
        boolean[][] has = new boolean[r][c];
        boolean[][] res = null;
        int ans = Integer.MAX_VALUE;
        for (int mask = 0; mask < 1 << r * c; mask++) {
            if (Integer.bitCount(mask) != n) continue;
            for (int i = 0, p = mask; i < r; i++) {
                for (int j = 0; j < c; j++, p >>= 1) {
                    has[i][j] = (p & 1) == 1;
                }
            }
            int cur = 0;
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (!has[i][j]) continue;
                    if (i + 1 < r && has[i + 1][j]) ++cur;
                    if (j + 1 < c && has[i][j + 1]) ++cur;
                }
            }
            ans = Math.min(ans, cur);
            if (ans == cur) {
                res = has.clone();
                for (int i = 0; i < res.length; i++) res[i] = res[i].clone();
            }
        }
        System.out.println(r + " " + c + " " + n + " " + ans);
        for (boolean[] e : res) {
            for (boolean z : e) System.out.print(z ? 1 : 0);
            System.out.println();
        }
        return ans;
    }
}
