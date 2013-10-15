package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Holidays {

    static final String[] MONTHS =
            {"January", "February", "March", "April", "May", "June", "July",
                    "August", "September", "October", "November", "December"};

    static int getMonthID(String s) {
        for (int i = 0; i < MONTHS.length; i++) {
            if (s.equals(MONTHS[i])) return i;
        }
        throw new AssertionError("no such month " + s);
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int y1 = in.nextInt();
        int y2 = in.nextInt();
        int n = in.nextInt();
        int[] mr = new int[n];
        int[] dr = new int[n];
        int[] ma = new int[n];
        int[] da = new int[n];
        boolean[] add = new boolean[n];
        for (int i = 0; i < n; i++) {
            String m1 = in.next();
            String ds1 = in.next();
            int d1 = Integer.parseInt(ds1.substring(0, ds1.length() - 1));
            String op = in.next();
            String m2 = in.next();
            int d2 = in.nextInt();
            mr[i] = getMonthID(m1);
            ma[i] = getMonthID(m2);
            dr[i] = d1;
            da[i] = d2;
            add[i] = op.equals("added");
        }
        int[][] dp = new int[n][y2 - y1 + 1];
        boolean[][] here = new boolean[n][y2 - y1 + 1];
        int[][] py = new int[n][y2 - y1 + 1];
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MIN_VALUE);
        }
        for (int y = y1; y <= y2; y++) {
            if (mr[0] != 1 || dr[0] != 29 || isLeap(y)) {
                dp[0][y - y1] = countHolidays(y, y2, mr[0], dr[0], ma[0], da[0]);
                here[0][y - y1] = true;
            }
            if (y != y1 && dp[0][y - y1] < dp[0][y - y1 - 1]) {
                dp[0][y - y1] = dp[0][y - y1 - 1];
                here[0][y - y1] = false;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = y1; j <= y2; j++) {
                int val = dp[i - 1][j - y1];
                if (val == Integer.MIN_VALUE) continue;
                int ny;
                if (compare(dr[i - 1], mr[i - 1], 0, dr[i], mr[i], 0) < 0) {
                    ny = j;
                } else {
                    ny = j + 1;
                }
                if (ny > y2) continue;
                if (mr[i] == 1 && dr[i] == 29 && !isLeap(ny)) continue;
                int count = countHolidays(ny, y2, mr[i], dr[i], ma[i], da[i]);
                if (!add[i]) count = -count;
                if (dp[i][ny - y1] < val + count) {
                    dp[i][ny - y1] = val + count;
                    here[i][ny - y1] = true;
                    py[i][ny - y1] = j - y1;
                }
            }
            for (int j = y1 + 1; j <= y2; j++) {
                if (dp[i][j - y1] < dp[i][j - y1 - 1]) {
                    here[i][j - y1] = false;
                    dp[i][j - y1] = dp[i][j - y1 - 1];
                }
            }
        }
        int ans = dp[n - 1][y2 - y1];
        if (ans == Integer.MIN_VALUE) {
            out.println(-1);
            return;
        }
        out.println(ans);
        int[] yr = new int[n];
        for (int i = n - 1, j = y2 - y1; i >= 0; ) {
            if (here[i][j]) {
                yr[i] = j + y1;
                j = py[i][j];
                --i;
            } else {
                --j;
            }
        }
//        int[] yr = new int[n];
//        if (!make(n, mr, dr, yr, add, y1, y2)) {
//            out.println(-1);
//            return;
//        }
//        int bestHolidays = -1;
//        int bi = -1;
//        for (int i = 0; i <= n; i++) {
//            if (!make(i, mr, dr, yr, add, y1, y2)) {
//                throw new AssertionError();
//            }
//            boolean[][] holiday = new boolean[12][32];
//            int cur = 0;
//            int countHolidays = 0;
//            for (int y = y1; y <= y2; y++) {
//                final int[] days = getDays(y);
//                for (int m = 0; m < 12; m++) {
//                    for (int d = 1; d <= days[m]; d++) {
//                        if (holiday[m][d]) {
//                            ++countHolidays;
//                        }
//                        if (cur < n && dr[cur] == d && mr[cur] == m && yr[cur] == y) {
//                            holiday[ma[cur]][da[cur]] ^= true;
//                            ++cur;
//                        }
//                    }
//                }
//            }
//            if (countHolidays > bestHolidays) {
//                bestHolidays = countHolidays;
//                bi = i;
//            }
//        }
//        make(bi, mr, dr, yr, add, y1, y2);
//        out.println(bestHolidays);
        for (int i = 0; i < n; i++) {
            out.println(MONTHS[mr[i]] + " " + dr[i] + " " + yr[i] + ", " + (add[i] ? "added" : "removed") + " " + MONTHS[ma[i]] + " " + da[i]);
        }
    }

    static int countHolidays(int y1, int y2, int m1, int d1, int m2, int d2) {
        int ans = 0;
        for (int y = y1; y <= y2; y++) {
            if (!isLeap(y) && m2 == 1 && d2 == 29) continue;
            if (y == y1 && compare(d1, m1, 0, d2, m2, 0) >= 0) continue;
            ++ans;
        }
        return ans;
    }

    static int[] getDays(int year) {
        int[] ret = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeap(year)) {
            ret[1]++;
        }
        return ret;
    }

    static boolean make(int mm, int[] mr, int[] dr, int[] yr, boolean[] add, int y1, int y2) {
        int n = mr.length;
        int cur = 0;
        all:
        for (int y = y1; y <= y2; y++) {
            final int[] days = getDays(y);
            for (int m = 0; m < 12; m++) {
                for (int d = 1; d <= days[m]; d++) {
                    if (cur >= mm) {
                        break all;
                    }
                    if (dr[cur] == d && mr[cur] == m) {
                        yr[cur] = y;
                        ++cur;
                    }
                }
            }
        }
        if (cur < mm) return false;
        cur = n - 1;
        all:
        for (int y = y2; y >= y1; y--) {
            final int[] days = getDays(y);
            for (int m = 11; m >= 0; m--) {
                for (int d = days[m]; d >= 1; d--) {
                    if (cur < mm) {
                        break all;
                    }
                    if (dr[cur] == d && mr[cur] == m) {
                        yr[cur] = y;
                        --cur;
                    }
                }
            }
        }
        while (true) {
            boolean changed = false;
            for (int i = 0; i < n; i++) {
                if (add[i]) {
                    if (i == 0) continue;
                    if (mr[i] == 1 && dr[i] == 29) {
                        continue;
                    } else {
                        while (compare(dr[i - 1], mr[i - 1], yr[i - 1], dr[i], mr[i], yr[i] - 1) < 0) {
                            --yr[i];
                            changed = true;
                        }
                    }
                } else {
                    if (i + 1 >= n) continue;
                    if (mr[i] == 1 && dr[i] == 29) {
                        continue;
                    } else {
                        while (compare(dr[i], mr[i], yr[i] + 1, dr[i + 1], mr[i + 1], yr[i + 1]) < 0) {
                            ++yr[i];
                            changed = true;
                        }
                    }
                }
            }
            if (!changed) break;
        }
        return true;
    }

    static int compare(int d1, int m1, int y1, int d2, int m2, int y2) {
        if (y1 != y2) return y1 - y2;
        if (m1 != m2) return m1 - m2;
        return d1 - d2;
    }

    static boolean isLeap(int y) {
        return y % 400 == 0 || y % 4 == 0 && y % 100 != 0;
    }
}
