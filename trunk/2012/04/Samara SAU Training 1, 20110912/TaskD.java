package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskD {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        Arrays.sort(a);
        long answer = 0;
        for (int i = n - 1; i >= 0 && k > 0; i--) {
            answer += a[i];
            --k;
        }
        out.println(answer);
	}
}
