package mypackage;

import arrayutils.ArrayUtils;
import com.sun.deploy.util.ArrayUtil;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskA {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        final int BITS = 21;
        int[][] next = new int[n + 1][BITS];
        for (int i = 0; i < BITS; i++) {
            next[n][i] = n;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < BITS; j++) {
                if (((a[i] >> j) & 1) == 1) {
                    next[i][j] = i;
                } else {
                    next[i][j] = next[i + 1][j];
                }
            }
        }
        int answer = 0;
        boolean[] was = new boolean[1 << BITS];
        for (int start = 0; start < n; start++) {
            int[] z = next[start].clone();
            ArrayUtils.sort(z);
            int x = a[start];
            was[x] = true;
            for (int i : z) {
                if (i == n) {
                    break;
                }
                x |= a[i];
                was[x] = true;
            }
        }
        for (boolean e : was) {
            answer += e ? 1 : 0;
        }
        out.println(answer);
	}
}
