package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Experiment {

    static final int MAXR = 1 << 19;

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[MAXR];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            b[a[i]]++;
        }
        Arrays.sort(a);
        int[] c = b.clone();
        for (int i = c.length - 2; i >= 0; i--) {
            c[i] += c[i + 1];
        }
        int l = 0;
        int r = MAXR;
        while (l < r - 1) {
            int mid = l + r >> 1;
            int cur = 0;
            for (int i = 0; i < MAXR; i++) {
                if (b[i] == 0) {
                    continue;
                }
                int high = 2 * mid - i - 1;
                if (high < c.length && high >= 0) {
                    cur += c[high] * b[i];
                }
            }
            if (cur >= n) {
                l = mid;
            } else {
                r = mid;
            }
        }
        List<Integer> answer = new ArrayList<Integer>();
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int x = (a[i] + a[j] + 1) >> 1;
                if (x <= l) {
                    break;
                }
                answer.add(x);
            }
        }
        while (answer.size() < n) {
            answer.add(l);
        }
        Collections.sort(answer);
        Collections.reverse(answer);
        for (int i : answer) {
            out.print(i + " ");
        }
	}
}
