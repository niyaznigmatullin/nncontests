package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.math.BigInteger;

public class Chinese {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        BigInteger n = new BigInteger(in.next());
        BigInteger k = n.shiftRight(1);
        while (!n.gcd(k).equals(BigInteger.ONE)) {
            k = k.subtract(BigInteger.ONE);
        }
        out.println(k);
	}
}
