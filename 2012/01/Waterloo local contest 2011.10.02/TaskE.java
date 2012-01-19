package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int classes = in.nextInt();
        int tasks = in.nextInt();
        int length = in.nextInt();
        int[][] cost = new int[classes][tasks];
        int[][] where = new int[classes][tasks];
        for (int i = 0; i < classes; i++) {
            for (int j = 0; j < tasks; j++) {
                where[i][j] = in.nextInt();
                cost[i][j] = in.nextInt();
            }
        }
        int[] dp = new int[tasks];
        for (int i = 0; i < tasks; i++) {
            dp[i] = where[0][i] + cost[0][i];
        }
        for (int curClass = 1; curClass < classes; curClass++) {
            int[] next = new int[tasks];
            int[] where1 = where[curClass];
            int[] where2 = where[curClass - 1];
            for (int curTask = 0; curTask < tasks; curTask++) {
                int value = Integer.MAX_VALUE;
                for (int lastTask = 0; lastTask < tasks; lastTask++) {
                    value = Math.min(value,
                            dp[lastTask] + Math.abs(where1[curTask] - where2[lastTask]));
                }
                next[curTask] = value + cost[curClass][curTask];
            }
            dp = next;
        }
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < tasks; i++) {
            answer = Math.min(answer, dp[i] + length - where[classes - 1][i]);
        }
        out.println(answer);
    }
}
