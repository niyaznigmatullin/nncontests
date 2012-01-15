package mypackage;

import niyazio.FastScanner;
import sun.nio.cs.ext.MacHebrew;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, FastScanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        Arrays.fill(x, -1);
        Arrays.fill(y, -1);
        for (int i = 0; i < n; i++) {
            char[] c = in.next().toCharArray();
            for (int j = 0; j < m; j++) {
                if (c[j] == 'G') {
                    x[i] = j;
                }
                if (c[j] == 'R') {
                    y[i] = j;
                }
            }
        }
        int count1 = 0;
        int count2 = 0;
        boolean canDraw1 = false;
        boolean canDraw2 = false;
        for (int i = 0; i < n; i++) {
            if (x[i] == -1 && y[i] >= 0) {
                canDraw2 = true;
            }
            if (x[i] >= 0 && y[i] == -1) {
                canDraw1 = true;
            }
            if (x[i] >= 0 && y[i] >= 0) {
                if (Math.abs(x[i] - y[i]) > 1) {
                    count1++;
                } else {
                    count2++;
                }
            }
        }
        boolean canWin = false;
        boolean[] win = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k && j <= i; j++) {
                win[i] |= !win[i - j];
            }
        }
        canWin |= win[count1];
        for (int i = 0; i <= k && i <= count2; i++) {
            canWin |= !win[count1 + i];
        }
        if (canWin) {
            if (canDraw2) {
                out.println("Draw");
            } else {
                out.println("First");
            }
        } else {
            if (canDraw1) {
                out.println("Draw");
            } else {
                out.println("Second");
            }
        }
    }


}
