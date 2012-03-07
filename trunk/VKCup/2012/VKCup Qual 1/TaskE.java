package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import javax.swing.border.MatteBorder;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class TaskE {

    static final int DAY = 86400;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] t = new int[n];
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            t[i] = in.nextInt() - 1;
            d[i] = in.nextInt();
        }
        out.println(solve(n, k, t, d));
    }

    static int solve(int n, int k, int[] t, int[] d) {
        if (n == k) {
            return DAY;
        }
        int answer = 0;
        long[][] dp = new long[n + 1][k + 1];
        for (long[] e : dp) {
            Arrays.fill(e, Long.MAX_VALUE);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k && j <= i; j++) {
                long last = dp[i - 1][j];
                if (last != Long.MAX_VALUE) {
                    dp[i][j] = Math.max(t[i - 1], last) + d[i - 1];
                }
                if (j > 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }
            }
        }
        for (int before = 0; before <= n; before++) {
            int leftThere = Math.min(k, before);
            long start = dp[before][leftThere];
            long finish = before < n ? t[before] : DAY;
            if (start >= DAY || finish > DAY || start >= finish) {
                continue;
            }
            answer = (int) Math.max(answer, finish - start);
        }
        return answer;
    }

    static int solveVeryStupid(int n, int k, int[] t, int[] d) {
        int answer = 0;
        for (int mask = 0; mask < 1 << n; mask++) {
            if (Integer.bitCount(mask) < n - k) {
                continue;
            }
            int curAnswer = 0;
            int last = 0;
            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 0) {
                    continue;
                }
                int curStart = Math.max(last, t[i]);
                curAnswer = Math.max(curAnswer, curStart - last);
                last = curStart + d[i];
            }
            curAnswer = Math.max(curAnswer, DAY - last);
            answer = Math.max(answer, curAnswer);
        }
        return answer;
    }

    static int solveStupid(int n, int k, int[] t, int[] d) {
        int answer = 0;
        for (int after = -1; after < n; after++) {
            for (int before = after + 1; before <= n; before++) {
                long start = 0;
                for (int i = 0; i <= after; i++) {
                    start = Math.max(start, t[i]) + d[i];
                }
                long finish = before == n ? DAY : t[before];
                if (start >= DAY || finish > DAY) {
                    continue;
                }
                int last = 0;
                int bad = 0;
                for (int i = 0; i < n; i++) {
                    int startOfCall = Math.max(last, t[i]);
                    int endOfCall = startOfCall + d[i];
                    if (startOfCall >= start && startOfCall < finish || startOfCall < start && endOfCall >= start) {
                        ++bad;
                        continue;
                    }
                    if (startOfCall >= finish) {
                        break;
                    }
                    last = endOfCall;
                }
                if (bad <= k) {
                    answer = (int) Math.max(answer, finish - start);
                }
            }
        }
        return answer;
    }
}
