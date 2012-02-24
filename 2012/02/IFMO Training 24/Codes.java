package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;
import java.util.Random;

public class Codes {


    static final int CONST = 60;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
//        test();
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (String e : solveSmart(a).bits) {
            out.println(e);
        }
    }

    static final Random rand = new Random(123131L);

    static void test() {
        final int N = 600;
        int q = 0;
        while (true) {
            if (++q == 10) {
                q = 0;
                System.err.println("TEST");
            }
            int[] a = new int[N];
            for (int i = 0; i < a.length; i++) {
                a[i] = rand.nextInt(1000000000);
            }
            long time = System.currentTimeMillis();
            long ans1 = solveSmart(a).answer;
            System.err.println(System.currentTimeMillis() - time);
            long ans2 = solveStupid(a).answer;
            if (ans1 != ans2) {
                System.err.println(ans1);
                System.err.println(ans2);
                System.err.println(Arrays.toString(a));
                throw new AssertionError();
            }
        }
    }

    static Answer solveStupid(int[] a) {
        int n = a.length;
        long[][] dp = new long[n][n];
        long[] b = new long[a.length];
        for (int i = 0; i < a.length; i++) {
            if (i > 0) {
                b[i] = b[i - 1];
            }
            b[i] += a[i];
        }
        for (int len = 1; len < n; len++) {
            for (int i = 0, j = len; j < n; i++, j++) {
                long ans = Long.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    ans = Math.min(ans, dp[i][k] + dp[k + 1][j]);
                }
                dp[i][j] = ans + sum(i, j, b);
            }
        }
        StringBuilder sb = new StringBuilder();
        String[] ans = new String[n];
        restoreStupid(0, n - 1, dp, sb, ans, b);
        return new Answer(dp[0][n - 1], ans);
    }

    static Answer solveSmart(int[] a) {
        int n = a.length;
        long[][] dp = new long[n][n];
        int[][] id = new int[n][n];
        for (int i = 0; i < n; i++) {
            id[i][i] = i;
        }
        long[] b = new long[a.length];
        for (int i = 0; i < a.length; i++) {
            if (i > 0) {
                b[i] = b[i - 1];
            }
            b[i] += a[i];
        }
        for (int len = 1; len < n; len++) {
            for (int i = 0, j = len; j < n; i++, j++) {
                long ans = Long.MAX_VALUE;
                for (int k = id[i][j - 1] - 5; k <= id[i + 1][j] + 5 && k < j; k++) {
                    if (k >= i && k < j) {
                        long v = dp[i][k] + dp[k + 1][j];
                        if (ans > v) {
                            ans = v;
                            id[i][j] = k;
                        }
                    }
                }
                dp[i][j] = ans + sum(i, j, b);
            }
        }
        StringBuilder sb = new StringBuilder();
        String[] ans = new String[n];
        restoreStupid(0, n - 1, dp, sb, ans, b);
        return new Answer(dp[0][n - 1], ans);
    }

    static void restoreStupid(int l, int r, long[][] dp, StringBuilder sb, String[] ans, long[] b) {
        if (l == r) {
            ans[l] = sb.toString();
            return;
        }
        long sum = sum(l, r, b);
        for (int k = l; k < r; k++) {
            if (dp[l][k] + dp[k + 1][r] + sum == dp[l][r]) {
                sb.append('0');
                restoreStupid(l, k, dp, sb, ans, b);
                sb.setLength(sb.length() - 1);
                sb.append('1');
                restoreStupid(k + 1, r, dp, sb, ans, b);
                sb.setLength(sb.length() - 1);
                break;
            }
        }
    }

    static void restoreSmart(int l, int r, long[][] dp, StringBuilder sb, String[] ans, long[] b) {
        if (l == r) {
            ans[l] = sb.toString();
            return;
        }
        long sum = sum(l, r, b);
        for (int t = -CONST; t <= CONST; t++) {
            int k = (l + r >> 1) + t;
            if (dp[l][k] + dp[k + 1][r] + sum == dp[l][r]) {
                sb.append('0');
                restoreStupid(l, k, dp, sb, ans, b);
                sb.setLength(sb.length() - 1);
                sb.append('1');
                restoreStupid(k + 1, r, dp, sb, ans, b);
                sb.setLength(sb.length() - 1);
                break;
            }
        }
    }


    static long sum(int l, int r, long[] b) {
        long ret = b[r];
        if (l > 0) {
            ret -= b[l - 1];
        }
        return ret;
    }

    static class Answer {
        long answer;
        String[] bits;

        Answer(long answer, String[] bits) {
            this.answer = answer;
            this.bits = bits;
        }
    }
}
