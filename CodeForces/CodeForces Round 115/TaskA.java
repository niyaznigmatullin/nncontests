package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.math.BigInteger;
import java.util.Arrays;

public class TaskA {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                Integer a = get(s.substring(0, i));
                Integer b = get(s.substring(i, j));
                Integer c = get(s.substring(j));
                if (a == null || b == null || c == null) {
                    continue;
                }
                ans = Math.max(ans, a + b + c);
            }
        }
        out.println(ans == Integer.MIN_VALUE ? -1 : ans);
	}

    static Integer get(String s) {
        if (s.isEmpty()) {
            return null;
        }
        if (s.startsWith("0") && s.length() > 1) {
            return null;
        }
        BigInteger a = new BigInteger(s);
        if (a.compareTo(BigInteger.valueOf(1000000)) > 0) {
            return null;
        }
        return a.intValue();
    }
}
