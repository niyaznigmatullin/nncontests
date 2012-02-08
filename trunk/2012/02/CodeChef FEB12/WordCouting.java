package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.math.BigInteger;

public class WordCouting {

    static final int[] FACT;
    static final int MAXLEN = 500;
    static final int MOD = 1000000007;
    static {
        FACT = new int[MAXLEN + 1];
        FACT[0] = 1;
        for (int i = 1; i <= MAXLEN; i++) {
            FACT[i] = (int) ((long) FACT[i - 1] * i % MOD);
        }
    }

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        int[] a = new int[52];
        for (char c : s.toCharArray()) {
            int d = c >= 'a' && c <= 'z' ? c - 'a' : (c - 'A' + 26);
            a[d]++;
        }
        int ans = 1;
        for (int i = 0; i < a.length; i++) {
            ans = mulMod(ans, FACT[a[i]], MOD);
        }
        ans = mulMod(ans, modInverse(FACT[s.length()], MOD), MOD);
        out.println(modInverse(ans, MOD));
	}

    static int mulMod(int a, int b, int mod) {
        return (int) ((long) a * b % mod);
    }

    static int modInverse(int a, int mod) {
        return BigInteger.valueOf(a).modInverse(BigInteger.valueOf(mod)).intValue();
    }
}
