package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.math.BigInteger;

public class TaskC {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        out.println(new BigInteger(in.next()).add(new BigInteger(in.next())));
	}
}
