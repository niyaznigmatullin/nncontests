package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class FastDial {

    static char[][] KEYBOARD = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}, {'*', '0', '#'}};
    static double[][] DISTANCE;
    static {
        DISTANCE = new double[10][10];
        int[] x = new int[10];
        int[] y = new int[10];
        for (int i = 0; i < KEYBOARD.length; i++) {
            char[] c = KEYBOARD[i];
            for (int j = 0; j < c.length; j++) {
                if (c[j] >= '0' && c[j] <= '9') {
                    x[c[j] - '0'] = i;
                    y[c[j] - '0'] = j;
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                double dx = x[i] - x[j];
                double dy = y[i] - y[j];
                DISTANCE[i][j] = Math.sqrt(dx * dx + dy * dy);
            }
        }
    }
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        char[] s = in.next().toCharArray();
        double[][] dp = new double[10][10];
        for (int i = 0; i < s.length; i++) {
            double[][] next = new double[10][10];
            for (double[] z : next) {
                Arrays.fill(z, Double.POSITIVE_INFINITY);
            }
            int newDigit = s[i] - '0';
            for (int first = 0; first < 10; first++) {
                for (int second = 0; second < 10; second++) {
                    if (dp[first][second] == Double.POSITIVE_INFINITY) {
                        continue;
                    }
                    next[first][newDigit] = Math.min(next[first][newDigit], dp[first][second] + DISTANCE[second][newDigit]);
                    next[newDigit][second] = Math.min(next[newDigit][second], dp[first][second] + DISTANCE[first][newDigit]);
                }
            }
            dp = next;
        }
        double ans = Double.POSITIVE_INFINITY;
        for (double[] e : dp) {
            for (double z : e) {
                ans = Math.min(ans, z);
            }
        }
        out.println(ans);
	}
}
