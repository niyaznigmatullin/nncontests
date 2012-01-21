package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class Task2752 {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.nextToken();
        if (s == null) {
            throw new UnknownError();
        }
        char[] c = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            c[i] = (char) ('0' + (s.charAt((i + 1) % s.length()) - s.charAt(i) & 7));
        }
        String t = new String(c);
        c = (t + t).toCharArray();
        int maximal = 0;
        for (int i = 0; i < c.length; ) {
            int j = i + 1;
            int k = i;
            while (j < c.length && c[j] >= c[k]) {
                if (c[j] == c[k]) {
                    k++;
                    j++;
                } else {
                    j++;
                    k = i;
                }
            }
            while (i <= k) {
                if (i < t.length()) {
                    maximal = i;
                }
                i += j - k;
            }
        }
        out.println(t.substring(maximal) + t.substring(0, maximal));
	}
}
