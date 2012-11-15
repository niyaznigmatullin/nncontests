package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class Fool {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        char[] c = in.next().toCharArray();
        int n = c.length;
        boolean[][] isElement = new boolean[n + 1][n + 1];
        boolean[][] isList = new boolean[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            isList[i][i] = true;
        }
        for (int i = 0; i < n; i++) {
            isList[i][i + 1] = isElement[i][i + 1] = true;
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len;
                if (c[i] == '{' && c[j - 1] == '}' && isList[i + 1][j - 1]) {
                    isElement[i][j] = true;
                    isList[i][j] = true;
                }
                for (int k = i + 1; k < j; k++) {
                    if (c[k] == ',' && isElement[i][k] && j - k - 1 > 0 && isList[k + 1][j]) {
                        isList[i][j] = true;
                        break;
                    }
                }
            }
        }
        out.print("Word #" + testNumber + ": ");
        if (c[0] == '{' && c[n - 1] == '}' && isList[1][n - 1]) {
            out.println("Set");
        } else {
            out.println("No Set");
        }
	}
}
