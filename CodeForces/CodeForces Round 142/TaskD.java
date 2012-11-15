package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] h = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = in.nextInt();
        }
        long[] sum = new long[n];
        for (int i = 0; i < n; i++) {
            sum[i] = i == 0 ? h[0] : sum[i - 1] + h[i];
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MIN_VALUE);
        }
        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            int[] next = new int[n + 1];
            int k = 0;
            for (int j = 0; j <= n; j++) {
                while (k <= n && getSum(sum, i, j) > getSum(sum, j, k)) {
                    k++;
                }
                next[j] = k;
            }
            for (int j = i; j < n; j++) {
                dp[i][j + 1] = Math.max(dp[i][j], dp[i][j + 1]);
                k = next[j];
                if (k <= n) {
                    dp[j][k] = Math.max(dp[j][k], dp[i][j] + 1);
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, dp[i][n]);
        }
        out.println(n - answer);
    }

    static int stupid(int[] a) {
        int n = a.length;
        int[][] dp = new int[n][n];
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MIN_VALUE);
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        int[][] from = new int[n][n];
        for (int i = 1; i < n; i++) {
            for (int j = i; j < n; j++) {
                for (int k = 0; k < i; k++) {
                    int sum1 = 0;
                    for (int t = k; t < i; t++) {
                        sum1 += a[t];
                    }
                    int sum2 = 0;
                    for (int t = i; t <= j; t++) {
                        sum2 += a[t];
                    }
                    if (sum1 > sum2) {
                        continue;
                    }
                    if (dp[i][j] < dp[k][i - 1] + 1) {
                        dp[i][j] = dp[k][i - 1] + 1;
                        from[i][j] = k;
                    }
                }
            }
        }
        int answer = 0;
        int id = -1;
        for (int i = 0; i < n; i++) {
            if (answer < dp[i][n - 1]) {
                answer = dp[i][n - 1];
                id = i;
            }
        }
        for (int i = id, j = n - 1; ;) {
            System.out.println(i + " " + j);
            if (i == 0) {
                break;
            }
            int t = from[i][j];
            j = i - 1;
            i = t;
        }
        return n - answer;
    }

    static long getSum(long[] sum, int left, int right) {
        if (right <= 0 || left >= right) {
            return 0;
        }
        long ret = sum[right - 1];
        if (left > 0) {
            ret -= sum[left - 1];
        }
        return ret;
    }
}
