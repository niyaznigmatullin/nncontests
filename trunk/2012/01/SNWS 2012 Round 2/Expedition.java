package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class Expedition {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[m];
        for (int i = 0; i < m; i++) {
            a[i] = in.nextInt();
        }
        Arrays.sort(a);
        int answer = 0;
        int count = m / (n + 1);
        for (int i = m - 1; count-- > 0; i--) {
            answer += a[i];
        }
        out.println(answer);
	}
}
