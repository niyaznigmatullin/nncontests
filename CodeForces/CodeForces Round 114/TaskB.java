package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskB {

    static class Tour implements Comparable<Tour> {
        double probability;
        int win;

        Tour(double probability, int win) {
            this.probability = probability;
            this.win = win;
        }


        public int compareTo(Tour o) {
            return o.win - win;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int toWin = in.nextInt();
        int start = in.nextInt();
        if (toWin > n) {
            out.println(0);
            return;
        }
        int maxToLose = n - toWin;
        double[] probToWin = new double[n];
        for (int i = 0; i < n; i++) {
            probToWin[i] = .01 * in.nextInt();
        }
        int[] delta = new int[n];
        for (int i = 0; i < n; i++) {
            delta[i] = in.nextInt();
        }
        Tour[] a = new Tour[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Tour(probToWin[i], delta[i]);
        }
        Arrays.sort(a);
        final int MAXWIN = 200;
        double[][] dp = new double[MAXWIN + 1][maxToLose + 1];
        double[][] next = new double[MAXWIN + 1][maxToLose + 1];
        dp[start][0] = 1.;
        for (Tour e : a) {
            for (double[] d : next) {
                Arrays.fill(d, 0);
            }
            double win = e.probability;
            double lose = 1 - win;
            for (int i = 0; i <= MAXWIN; i++) {
                for (int j = 0; j <= maxToLose; j++) {
                    double val = dp[i][j];
                    if (val == 0) {
                        continue;
                    }
                    if (j < maxToLose) {
                        next[i][j + 1] += val * lose;
                    }
                    int newWins = Math.min(MAXWIN, i + e.win);
                    if (newWins < 0) {
                        continue;
                    }
                    next[newWins][j] += val * win;
                }
            }
            {
                double[][] t = next;
                next = dp;
                dp = t;
            }
        }
        double answer = 0;
        for (int i = 0; i <= MAXWIN; i++) {
            for (int j = 0; j <= maxToLose; j++) {
                answer += dp[i][j];
            }
        }
        out.println(answer);
    }
}
