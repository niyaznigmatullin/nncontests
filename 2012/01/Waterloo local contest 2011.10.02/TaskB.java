package mypackage;

import com.sun.tools.corba.se.idl.toJavaPortable.StringGen;
import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskB {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        while (true) {
//            System.err.println("was = " + s);
            String t = removeRepetitions(s);
//            System.err.println("after 1 " + t);
            t = makeItSmaller(t);
//            System.err.println("after 2 " + t);
            if (t.equals(s)) {
                break;
            }
            s = t;
        }
        out.println(s.isEmpty() ? "closed" : "open");
	}

    private String makeItSmaller(String t) {
        for (int i = 0; i + 1 < t.length(); ) {
            char c1 = t.charAt(i);
            char c2 = t.charAt(i + 1);
            int j = i;
            while (j - i < 8 && j < t.length() && (t.charAt(j) == c1 || t.charAt(j) == c2)) {
                j++;
            }
            if (j - i >= 5) {
                return t.substring(0, i) + add(t.substring(i, j)) + t.substring(j);
            } else {
                i = Math.max(j - 1, i + 1);
            }
        }
        return t;
    }

    static String add(String s) {
        if (s.length() < 2) {
            throw new AssertionError();
        }
        char c1 = s.charAt(0);
        char c2 = s.charAt(1);
        StringBuilder ret = new StringBuilder();
        for (int i = s.length(); i < 8; i++) {
            ret.append((i & 1) == 0 ? c1 : c2);
        }
        return ret.reverse().toString();
    }

    static String removeRepetitions(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == s.charAt(i)) {
                sb.setLength(sb.length() - 1);
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
