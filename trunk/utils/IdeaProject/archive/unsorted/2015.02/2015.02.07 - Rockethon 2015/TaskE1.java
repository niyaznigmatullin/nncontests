package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskE1 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
//        int[] b = new int[n];
//        {
//            int curSum = 0;
//            for (int i = 0; i < n; i++) {
//                curSum += a[i];
//                b[i] = curSum;
//            }
//        }
        int[][] dp = new int[n + 1][k + 1];
        int[][] dp1 = new int[n + 1][k + 1];
        int[][] dp2 = new int[n + 1][k + 1];
        for (int[] e : dp)
            Arrays.fill(e, Integer.MIN_VALUE);
        for (int[] e : dp1)
            Arrays.fill(e, Integer.MIN_VALUE);
        for (int[] e : dp2)
            Arrays.fill(e, Integer.MIN_VALUE);
        dp[0][0] = 0;
//        int[][] minElement = new int[n + 1][k + 1];
//        int[][] maxElement = new int[n + 1][k + 1];
//        for (int[] e : minElement) Arrays.fill(e, Integer.MAX_VALUE);
//        for (int[] e : maxElement) Arrays.fill(e, Integer.MIN_VALUE);
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0];
            for (int e = 1; e <= k; e++) {
                dp[i][e] = dp[i - 1][e];
                dp1[i][e] = dp1[i - 1][e];
                dp2[i][e] = dp2[i - 1][e];
                int sum = 0;
                for (int j = i - 1; j >= 0; j--) {
                    sum += a[j];
                    if (dp[j][e - 1] == Integer.MIN_VALUE) continue;
                    int val1 = dp1[j][e - 1] == Integer.MIN_VALUE ? 0 : dp1[j][e - 1] + sum;
                    int val2 = dp2[j][e - 1] == Integer.MIN_VALUE ? 0 : dp2[j][e - 1] - sum;
                    int val = Math.max(val1, val2);
                    if (dp[i][e] < val) {
                        dp[i][e] = val;
                    }
                    if (dp1[i][e] < val - sum) {
                        dp1[i][e] = val - sum;
                    }
                    if (dp2[i][e] < val + sum) {
                        dp2[i][e] = val + sum;
                    }
                }
//                System.out.println(i + " " + e + " " + dp[i][e] + " " + dp1[i][e] + " " + dp2[i][e]);
            }
        }
        out.println(dp[n][k]);
    }
}
