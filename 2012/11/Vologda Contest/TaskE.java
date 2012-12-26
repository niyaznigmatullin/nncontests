package mypackage;

import math.MathUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskE {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int[] x = new int[3];
        int[] y = new int[3];
        for (int i = 0; i < 3; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }
        int answer = 0;
        for (int i = 0; i < 3; i++) {
            int j = (i + 1) % 3;
            answer += MathUtils.gcd(Math.abs(x[i] - x[j]), Math.abs(y[i] - y[j]));
        }
        out.println(answer);
	}
}
