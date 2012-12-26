package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskB {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int[] count = new int[256];
        for (char c : in.nextLine().toCharArray()) {
            count[c]++;
        }
        in.nextLine();
        while (true) {
            String s = in.nextLine();
            if (s == null) {
                break;
            }
            for (char c : s.toCharArray()) {
                count[c]--;
            }
        }
        for (int i : count) {
            if (i != 0) {
                out.println("NO");
                return;
            }
        }
        out.println("YES");
	}
}
