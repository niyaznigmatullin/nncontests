package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskD {

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next().toUpperCase();
        StringBuilder sb = new StringBuilder();
        final String[] map = {"ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[j].indexOf(s.charAt(i)) >= 0) {
                    sb.appendCodePoint('0' + j + 2);
                    break;
                }
            }
        }
        s = sb.toString();
        out.println(s.equals(sb.reverse().toString()) ? "YES" : "NO");
	}
}
