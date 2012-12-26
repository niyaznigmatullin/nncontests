package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskC {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = "abcdefghijklmnopqrstuvwxyz";
        String t = in.next();
        int answer = 0;
        for (int i = t.indexOf(s); i >= 0; i = t.indexOf(s, i + 1)) {
            ++answer;
        }
        out.println(answer);
	}
}
