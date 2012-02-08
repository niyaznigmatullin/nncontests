package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class CountOfMaximum {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        Arrays.sort(a);
        int bestCount = 0;
        int answer = -1;
        for (int i = 0; i < n;) {
            int j = i;
            while (j < n && a[j] == a[i]) {
                j++;
            }
            int count = j - i;
            if (bestCount < count) {
                bestCount = count;
                answer = a[i];
            }
            i = j;
        }
        out.println(answer + " " + bestCount);
	}
}
