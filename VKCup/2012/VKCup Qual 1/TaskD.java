package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskD {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int answer = Integer.MIN_VALUE;
        for (int i = 1; i * i <= n; i++) {
            if (n % i != 0) {
                continue;
            }
            answer = Math.max(answer, get(a, i));
            answer = Math.max(answer, get(a, n / i));
        }
        out.println(answer);
	}

    static int get(int[] a, int leave) {
        if (leave < 3) {
            return Integer.MIN_VALUE;
        }
        int n = a.length;
        int groupSize = n / leave;
        int[] b = new int[groupSize];
        for (int i = 0; i < n; i++) {
            b[i % groupSize] += a[i];
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < groupSize; i++) {
            ans = Math.max(ans, b[i]);
        }
        return ans;
    }
}
