package mypackage;

import niyazio.FastScanner;
import java.io.PrintWriter;

public class TaskA {

    static void doIt(FastScanner in, int[] a, int c) {
        for (char ch : in.next().toCharArray()) {
            a[ch] += c;
        }
    }
	public void solve(int testNumber, FastScanner in, PrintWriter out) {
        int[] a = new int[256];
        doIt(in, a, 1);
        doIt(in, a, 1);
        doIt(in, a, -1);
        for (int i : a) {
            if (i != 0) {
                out.println("NO");
                return;
            }
        }
        out.println("YES");
	}
}
