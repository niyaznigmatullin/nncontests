package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskE {

    static char[][] c;
    static int[][][] next;
    static int[][] dp;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        c = new char[n][8];
        for (int i = 0; i < n; i++) {
            String s = in.next();
            Arrays.fill(c[i], '0');
            for (int j = 0; j < s.length(); j++) {
                c[i][s.length() - j - 1] = s.charAt(j);
            }
        }
        next = new int[8][10][n];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 10; j++) next[i][j][n - 1] = n;
        for (int i = n - 1; i >= 0; i--) {
            boolean justZeros = true;
            for (int pos = 7; pos >= 0; pos--) {
                for (int d = 0; d < 10; d++) {
                    if (i + 1 < n)
                        next[pos][d][i] = next[pos][d][i + 1];
                    if (c[i][pos] == '?') {
                        if (justZeros && d == 0) {
                            next[pos][d][i] = i;
                        }
                    } else {
                        if (c[i][pos] - '0' != d) {
                            next[pos][d][i] = i;
                        }
                    }
                }
                if (c[i][pos] != '0') justZeros = false;
            }
        }
        dp = new int[n][9];
        for (int i = 0; i < n; i++) {
            dp[i][0] = i + 1;
        }
        for (int pos = 1; pos < 9; pos++) {
            for (int i = 0; i < n; i++) {
                int where = i;
                for (int d = 0; where < n && d < 10; d++) {
                    where = Math.min(dp[where][pos - 1], next[pos - 1][d][where]);
                }
                dp[i][pos] = where;
            }
        }
        if (dp[0][8] == n) {
            out.println("YES");
            restore(8, 0, n);
            int cur = Integer.MIN_VALUE;
            int[] ans = new int[n];
            for (int i = 0; i < n; i++) {
                int x = Integer.parseInt(new StringBuilder(new String(c[i])).reverse().toString());
                if (x <= cur) {
                    out.println("NO");
                    return;
                }
                cur = x;
                ans[i] = x;
            }
            for (int i : ans) {
                out.println(i);
            }
        } else {
            out.println("NO");
        }
    }

    static void restore(int pos, int left, int right) {
        if (pos == 0) return;
        int where = left;
        for (int d = 0; where < right && d < 10; d++) {
            int go = Math.min(dp[where][pos - 1], next[pos - 1][d][where]);
            go = Math.min(go, right);
            restore(pos - 1, where, go);
            while (where < go) {
                c[where][pos - 1] = (char) (d + '0');
                ++where;
            }
        }
    }
}
