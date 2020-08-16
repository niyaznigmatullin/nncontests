package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class GridWalk {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        if (!(1 <= n && n <= 1000 && 1 <= m && m <= 1000)) throw new AssertionError();
        int[][] a = in.readInt2DArray(n, m);
        int[][] left = in.readInt2DArray(n - 1, m);
        int[][] right = in.readInt2DArray(n - 1, m);
        for (int[] e : a) {
            int last = 0;
            for (int d : e) {
                if (d < 1 || d > m) throw new AssertionError();
                if (last > d) throw new AssertionError();
                last = d;
            }
        }
        for (int i = 0; i + 1 < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j > 0) {
                    if (left[i][j] < left[i][j - 1] || right[i][j] < right[i][j - 1]) throw new AssertionError();
                    if (left[i][j] > right[i][j] + 1) throw new AssertionError();
                }
            }
        }
        int[][] dp = new int[n][m];
        int[][] diff = new int[n][m];
        for (int column = 0; column < m; column++) {
            dp[n - 1][column] = 1;
            if (column > 0 && a[n - 1][column] == a[n - 1][column - 1]) {
                diff[n - 1][column] = 1;
            }
        }
        for (int row = n - 2; row >= 0; row--) {
            int curLeft = 0;
            int curRight = 0;
            int sumDP = 0;
            int sumDiff = 0;
            for (int col = 0; col < m; col++) {
                left[row][col]--;
                while (curLeft < left[row][col]) {
                    sumDP = add(sumDP, MOD - dp[row + 1][curLeft]);
                    sumDiff = add(sumDiff, MOD - diff[row + 1][curLeft]);
                    ++curLeft;
                }
                if (col > 0 && a[row][col] == a[row][col - 1]) {
                    if (left[row][col] == right[row][col - 1]) {
                        diff[row][col] = a[row + 1][right[row][col - 1] - 1] != a[row + 1][left[row][col]] ? 0 : diff[row + 1][left[row][col]];
                    } else {
                        diff[row][col] = add(sumDP, MOD - (curLeft < curRight ? add(sumDiff, MOD - diff[row + 1][curLeft]) : 0));
                    }
                }
                while (curRight < right[row][col]) {
                    sumDP = add(sumDP, dp[row + 1][curRight]);
                    sumDiff = add(sumDiff, diff[row + 1][curRight]);
                    curRight++;
                }
                int subtract = add(sumDiff, MOD - diff[row + 1][curLeft]);
                dp[row][col] = add(sumDP, MOD - subtract);
//                System.out.println("row = " + row + ", col = " + col + ", diff = " + diff[row][col] + ", dp = " + dp[row][col]);
            }
        }
        int sum = 0;
        for (int col = 0; col < m; col++) {
            sum = add(sum, dp[0][col]);
            sum = add(sum, MOD - diff[0][col]);
        }
        out.println(sum);
    }

    static int add(int a, int b) {
        a += b;
        if (a >= MOD) a -= MOD;
        return a;
    }

    static final int MOD = 1000000007;
}
