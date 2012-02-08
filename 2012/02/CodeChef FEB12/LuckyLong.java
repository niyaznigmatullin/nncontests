package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class LuckyLong {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        char[] s = in.next().toCharArray();
        int[] curDigits = new int[10];
        for (char c : s) {
            curDigits[c - '0']++;
        }
        int answer = s.length - curDigits[4] - curDigits[7];
        for (int add = 1; add < answer; add++) {
            s = addOne(s, curDigits);
            answer = Math.min(answer, add + s.length - curDigits[4] - curDigits[7]);
        }
        out.println(answer);
	}

    static char[] addOne(char[] s, int[] curDigits) {
        int i = s.length - 1;
        while (i >= 0 && s[i] == '9') {
            curDigits[9]--;
            s[i] = '0';
            curDigits[0]++;
            i--;
        }
        if (i < 0) {
            s = new char[s.length + 1];
            Arrays.fill(s, '0');
            s[0] = '1';
            curDigits[1]++;
            return s;
        }
        curDigits[s[i] - '0']--;
        s[i]++;
        curDigits[s[i] - '0']++;
        return s;
    }
}
