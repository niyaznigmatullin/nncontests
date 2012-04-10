package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskA {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int a = in.nextInt();
        int b = Integer.parseInt(new StringBuilder(in.next()).reverse().toString());
        out.println(a + b);
	}
}
