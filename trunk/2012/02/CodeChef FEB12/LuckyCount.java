package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class LuckyCount {

    static final int[] BALANCE;
    static final int[] ANSWER;
    static final int MAXN = 100001;
    static {
        BALANCE = new int[MAXN];
        for (int i = 1; i < MAXN; i++) {
            int fours = getD(i, 4);
            int sevens = getD(i, 7);
            BALANCE[i] = BALANCE[i - 1] + fours - sevens;
        }
        int[] was = new int[MAXN * 10];
        ANSWER = new int[MAXN];
        was[0] = 1;
        for (int i = 1; i < MAXN; i++) {
            ANSWER[i] = ANSWER[i - 1] + was[BALANCE[i]];
            was[BALANCE[i]]++;
        }
    }

    static int getD(int n, int d) {
        int ans = 0;
        while (n > 0) {
            if (n % 10 == d) {
                ans++;
            }
            n /= 10;
        }
        return ans;
    }

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        out.println(ANSWER[in.nextInt()]);
	}
}
