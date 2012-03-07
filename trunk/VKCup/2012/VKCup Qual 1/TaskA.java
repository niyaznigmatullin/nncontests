package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskA {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int kth = a[k - 1];
        int answer = 0;
        for (int i : a) {
            if (i >= kth && i > 0) {
                ++answer;
            }
        }
        out.println(answer);
	}
}
