package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.math.BigInteger;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        String t = in.next();
        BigInteger[] f = new BigInteger[10001];
        f[0] = BigInteger.ONE;
        f[1] = f[0].add(f[0]);
        for (int i = 2; i < f.length; i++) {
            f[i] = f[i - 1].add(f[i - 2]);
        }
        BigInteger a = convert(s, f);
        BigInteger b = convert(t, f);
        out.println(convertBack(a.add(b), f));
    }

    static BigInteger convert(String s, BigInteger[] f) {
        BigInteger ret = BigInteger.ZERO;
        for (int i = 0; i < s.length(); i++) {
            int d = s.charAt(s.length() - i - 1) - '0';
            if (d == 1) {
                ret = ret.add(f[i]);
            }
        }
        return ret;
    }

    static String convertBack(BigInteger a, BigInteger[] f) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (int i = f.length - 1; i >= 0; i--) {
            if (f[i].compareTo(a) <= 0) {
                first = false;
                sb.append('1');
                a = a.subtract(f[i]);
            } else if (!first) {
                sb.append('0');
            }
        }
        return sb.toString();
    }

    static int[] readNumber(FastScanner in) {
        String s = in.next();
        int[] ret = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            ret[i] = s.charAt(s.length() - i - 1) - '0';
        }
        return ret;
    }
}
