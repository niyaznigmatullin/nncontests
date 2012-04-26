package mypackage;

import com.sun.org.apache.xpath.internal.operations.Mod;
import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskB {

    static final int MOD = 1000000007;

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String a = in.next();
        String b = in.next();
        int cycle = -1;
        for (int i = 0; i < a.length(); i++) {
            if ((a.substring(i) + a.substring(0, i)).equals(b)) {
                cycle = i;
                break;
            }
        }
        if (cycle < 0) {
            out.println(0);
            return;
        }
        int period = a.length();
        all: for (int i = 1; i < a.length(); i++) {
            if (a.length() % i != 0) {
                continue;
            }
            for (int j = 0; j < i; j++) {
                for (int k = j; k < a.length(); k += i) {
                    if (a.charAt(k) != a.charAt(j)) {
                        continue all;
                    }
                }
            }
            period = i;
            break;
        }
        int k = a.length() / period;
        cycle %= period;
        int first = 1;
        int nonFirst = 0;
        int sum = 1;
        int it = in.nextInt();
        for (int i = 0; i < it; i++) {
            int newFirst = (int) (((sum * (long) (k) - first) % MOD + MOD) % MOD);
            int newNonFirst = (int) (((sum  * (long) k - nonFirst) % MOD + MOD) % MOD);
            int newSum = (int) ((newFirst + newNonFirst * (long) (period - 1)) % MOD);
            sum = newSum;
            nonFirst = newNonFirst;
            first = newFirst;
        }
        out.println(cycle == 0 ? first : nonFirst);
	}
}