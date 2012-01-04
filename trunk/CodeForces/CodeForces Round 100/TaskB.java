package mypackage;

import niyazio.FastScanner;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TaskB {
    public void solve(int testNumber, FastScanner in, PrintWriter out) {
        int n = in.nextInt();
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][in.nextInt() - 1] = j;
            }
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[in.nextInt() - 1] = i;
        }
        int[] ans = new int[n];
        int[] whenToSend = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                int currentBest = -1;
                for (int j = 0; j <= i; j++) {
                    if (j == k) {
                        continue;
                    }
                    if (currentBest < 0 || b[currentBest] > b[j]) {
                        currentBest = j;
                    }
                }
                if (ans[k] < 0 || a[k][ans[k]] > a[k][currentBest]) {
                    ans[k] = currentBest;
                    whenToSend[k] = i;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            out.print(whenToSend[i] + 1 + " ");
        }
    }
}
