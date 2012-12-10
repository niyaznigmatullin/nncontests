package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.HashSet;

public class TaskA {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = Integer.parseInt(in.nextLine().trim());
        HashSet<String> ans = new HashSet<String>();
        for (int i = 0; i < n; i++) {
            char[][] c = new char[2][];
            c[0] = in.nextLine().trim().toCharArray();
            c[1] = in.nextLine().trim().toCharArray();
            String s = new String(c[0]) + new String(c[1]);
            for (int rot = 0; rot < 4; rot++) {
                char t = c[0][0];
                c[0][0] = c[0][1];
                c[0][1] = c[1][1];
                c[1][1] = c[1][0];
                c[1][0] = t;
                String z = new String(c[0]) + new String(c[1]);
                if (z.compareTo(s) < 0) {
                    s = z;
                }
            }
            ans.add(s);
            in.nextLine();
        }
        out.println(ans.size());
	}
}
