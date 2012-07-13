package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.math.BigInteger;

public class ProblemB {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int k1 = in.nextInt();
        int k2 = in.nextInt();
        out.println(new BigInteger(in.next(), k1).toString(k2).toUpperCase());
	}
}
