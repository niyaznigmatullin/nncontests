package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskA {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int minPos = -1;
        int maxPos = -1;
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (x <= minX) {
                minPos = i;
                minX = x;
            }
            if (x > maxX) {
                maxPos = i;
                maxX = x;
            }
        }
        if (maxPos < minPos) {
            out.println(maxPos + (n - 1 - minPos));
        } else {
            out.println(maxPos + n - 1 - minPos - 1);
        }
	}
}
