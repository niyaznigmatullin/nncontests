package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class RainingOutside {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = in.readIntArray(n);
        int[] b = in.readIntArray(m);
//        long ans2 = stupid(a, b);
        long ans1 = smart(a, b);
//        if (ans1 != ans2) {
//            throw new AssertionError(ans1 + " " + ans2);
//        }
//        out.println(stupid(a, b));
        out.println(ans1);
    }

    private long stupid(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;
        a = a.clone();
        b = b.clone();
        Arrays.sort(a);
        Arrays.sort(b);
        long[][] dp = new long[m + 1][n + 1];
        for (long[] e : dp) {
            Arrays.fill(e, Long.MAX_VALUE);
        }
        int[][] from = new int[m + 1][n + 1];
        dp[0][0] = 0;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                long val = dp[i][j];
                if (dp[i][j + 1] > val) {
                    dp[i][j + 1] = val;
                    from[i][j + 1] = i;
                }
                dp[i][j + 1] = Math.min(dp[i][j + 1], val);
                if (val == Long.MAX_VALUE) continue;
                for (int upTo = i + 1; upTo <= m; upTo++) {
                    long got = val + getPath(a[j], b[i], b[upTo - 1]);
                    if (dp[upTo][j + 1] > got) {
                        dp[upTo][j + 1] = got;
                        from[upTo][j + 1] = i;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        for (int i = m, j = n; j > 0; j--) {
            System.out.println("[" + i + " " + from[i][j] + "]");
            i = from[i][j];
        }
        return dp[m][n];
    }

    private long notSoStupid(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;
        Arrays.sort(a);
        Arrays.sort(b);
        long[][] dp = new long[m + 1][n + 1];
        for (long[] e : dp) {
            Arrays.fill(e, Long.MAX_VALUE);
        }
        dp[0][0] = 0;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                long val = dp[i][j];
                dp[i][j + 1] = Math.min(dp[i][j + 1], val);
                if (i == m) continue;
                if (j > 0 && a[j - 1] > b[i]) continue;
                if (j + 1 < n && a[j + 1] < b[i]) continue;
                if (val == Long.MAX_VALUE) continue;
                for (int upTo = i + 1; upTo <= m; upTo++) {
                    dp[upTo][j + 1] = Math.min(dp[upTo][j + 1], val + getPath(a[j], b[i], b[upTo - 1]));
                }
            }
        }
        return dp[m][n];
    }

    private long smarter(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;
        Arrays.sort(a);
        Arrays.sort(b);
        int[] left = new int[m];
        for (int i = 0, j = -1; i < m; i++) {
            while (j + 1 < n && a[j + 1] <= b[i]) ++j;
            left[i] = j;
        }
        int[] right = new int[m];
        for (int i = m - 1, j = n; i >= 0; i--) {
            while (j - 1 >= 0 && a[j - 1] >= b[i]) --j;
            right[i] = j;
        }
        long[][] dp = new long[m + 1][2];
        for (long[] e : dp) Arrays.fill(e, Long.MAX_VALUE);
        dp[0][0] = 0;
        dp[0][1] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 2; j++) {
                long val = dp[i][j];
                if (val == Long.MAX_VALUE) continue;
                int id = j == 0 ? left[i] : right[i];
                if (id < 0 || id >= n) continue;
                for (int upTo = i + 1; upTo <= m && left[upTo - 1] <= id; upTo++) {
                    long got = getPath(a[id], b[i], b[upTo - 1]) + val;
                    int curLeft = upTo == m ? n : left[upTo];
                    int curRight = upTo == m ? n : right[upTo];
                    if (curLeft > id) {
                        dp[upTo][0] = Math.min(dp[upTo][0], got);
                    }
                    if (curRight > id) {
                        dp[upTo][1] = Math.min(dp[upTo][1], got);
                    }
                }
            }
        }
        return Math.min(dp[m][0], dp[m][1]);
    }

    private long smart(int[] a, int[] b) {
        int n = a.length;
        a = a.clone();
        Arrays.sort(a);
        b = ArrayUtils.sortAndUnique(b);
        int m = b.length;
        {
            int newM = 0;
            for (int i = 0, j = 0; i < m; i++) {
                while (j < n && a[j] < b[i]) ++j;
                if (j >= n || a[j] != b[i]) {
                    b[newM++] = b[i];
                }
            }
            b = Arrays.copyOf(b, newM);
            m = newM;
        }
        if (m == 0) return 0;
        int[] left = new int[m];
        for (int i = 0, j = -1; i < m; i++) {
            while (j + 1 < n && a[j + 1] < b[i]) ++j;
            left[i] = j;
        }
        long[][][] dp = new long[2][2][m]; // [left or right][twice or once][element]
        for (long[][] e : dp) for (long[] f : e) Arrays.fill(f, Long.MAX_VALUE);
        if (left[0] >= 0) {
            dp[0][1][0] = 2L * (b[0] - a[left[0]]);
            dp[0][0][0] = b[0] - a[left[0]];
        }
        if (left[0] + 1 < n) {
            dp[1][1][0] = 0;
            dp[1][0][0] = 0;
        }
        for (int i = 0; i + 1 < m; i++) {
            for (int isRight = 0; isRight < 2; isRight++) {
                for (int isTwice = 0; isTwice < 2; isTwice++) {
                    long val = dp[isRight][isTwice][i];
                    if (val == Long.MAX_VALUE) continue;
                    long mul = isTwice + 1;
                    if (left[i] == left[i + 1]) {
                        dp[isRight][isTwice][i + 1] = Math.min(dp[isRight][isTwice][i + 1], val + (b[i + 1] - b[i]) * mul);
                        if (isRight == 0 && left[i + 1] + 1 < n) {
                            dp[1][0][i + 1] = Math.min(dp[1][0][i + 1], val);
                            dp[1][1][i + 1] = Math.min(dp[1][1][i + 1], val);
                        }
                    } else {
                        long addLeft = isRight == 0 ? 0 : mul * (a[left[i] + 1] - b[i]);
                        {
                            long addRight = b[i + 1] - a[left[i + 1]];
                            dp[0][isTwice ^ 1][i + 1] = Math.min(dp[0][isTwice ^ 1][i + 1], val + addLeft + addRight * ((isTwice ^ 1) + 1));
                            if (isRight == 0 || left[i + 1] != left[i] + 1) {
                                dp[0][isTwice][i + 1] = Math.min(dp[0][isTwice][i + 1], val + addLeft + addRight * (isTwice + 1));
                            }
                        }
                        if (left[i + 1] + 1 < n) {
                            dp[1][0][i + 1] = Math.min(dp[1][0][i + 1], val + addLeft);
                            dp[1][1][i + 1] = Math.min(dp[1][1][i + 1], val + addLeft);
                        }
                    }
                }
            }
        }
        long answer = Math.min(dp[0][0][m - 1], dp[0][1][m - 1]);
        for (int isTwice = 0; isTwice < 2; isTwice++) {
            long val = dp[1][isTwice][m - 1];
            if (val != Long.MAX_VALUE && left[m - 1] + 1 < n) {
                answer = Math.min(answer, val + (long) (isTwice + 1) * (a[left[m - 1] + 1] - b[m - 1]));
            }
        }
        return answer;
    }

    static long getPath(long from, long to1, long to2) {
        return Math.min(Math.abs(from - to1) + Math.abs(to1 - to2), Math.abs(from - to2) + Math.abs(to1 - to2));
    }
}
