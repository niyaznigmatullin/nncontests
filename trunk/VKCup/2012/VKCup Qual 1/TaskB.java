package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskB {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int[] a = new int[4];
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            ++a[in.nextInt() - 1];
        }
        int answer = a[3];  // fours
        a[0] -= Math.min(a[0], a[2]);  // threes with ones
        answer += a[2];  // threes
        answer += a[1] / 2;  // twos with twos
        a[1] %= 2;  // left twos
        if (a[1] == 1) { // if there is
            ++answer; // add it
            a[0] -= Math.min(a[0], 2);  // put there ones
        }
        answer += (a[0] + 3) / 4; // add ones
        out.println(answer);
	}
}
