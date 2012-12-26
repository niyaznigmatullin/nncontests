package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskA {

    static int min;
    static int max;
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        f(a - b);
        f(a + b);
        f(b - a);
        out.println(max + " " + min);
	}

    static void f(int a) {
        min = Math.min(min, a);
        max = Math.max(max, a);
    }
}
