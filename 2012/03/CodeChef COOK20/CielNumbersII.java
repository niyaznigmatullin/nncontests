package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class CielNumbersII {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int t = Integer.parseInt(in.nextLine().trim());
        int answer = 0;
        for (int i = 0; i < t; i++) {
            String line = in.nextLine().trim();
            int p = line.lastIndexOf(' ');
            if (p >= 0) {
                line = line.substring(p + 1);
            }
            long a = Long.parseLong(line);
            if (good(a)) {
                ++answer;
            }
        }
        out.println(answer);
	}


    static boolean good(long v) {
        int c3 = 0;
        int c5 = 0;
        int c8 = 0;
        while (v > 0) {
            int d = (int) (v % 10);
            if (d == 3) {
                c3++;
            } else if (d == 5) {
                c5++;
            } else if (d == 8) {
                c8++;
            } else {
                return false;
            }
            v /= 10;
        }
        return c3 <= c5 && c5 <= c8;
    }
}
