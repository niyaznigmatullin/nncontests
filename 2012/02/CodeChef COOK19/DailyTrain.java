package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class DailyTrain {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int x = in.nextInt();
        int n = in.nextInt();
        int[][] a = new int[n][9];
        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int j = 0; j < 54; j++) {
                char c = s.charAt(j);
                if (c == '0') {
                    continue;
                }
                a[i][getC(j)]++;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 9; j++) {
                ans += c(6 - a[i][j], x);
            }
        }
        out.println(ans);
	}

    static int c(int n, int k) {
        if (k < 0 || k > n) {
            return 0;
        }
        int ans = 1;
        for (int i = 0; i < k; i++) {
            ans = ans * (n - i) / (i + 1);
        }
        return ans;
    }

    static int getC(int x) {
        if (x < 36) {
            return x / 4;
        }
        return (54 - x - 1) / 2;
    }
}
