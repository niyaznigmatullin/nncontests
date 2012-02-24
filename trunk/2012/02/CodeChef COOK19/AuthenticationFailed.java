package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AuthenticationFailed {

    static final int MOD = 1009419529;

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        n += k;
        char[] c = in.next().toCharArray();
        int[][] prev = new int[26][n + 1];
        for (int[] d : prev) {
            Arrays.fill(d, -1);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 26; j++) {
                prev[j][i] = prev[j][i - 1];
            }
            prev[c[i - 1] - 'a'][i] = i - 1;
        }
        int[][] dp = new int[k + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[0][i] = dp[0][i - 1];
            for (int j = 1; j <= k; j++) {
                dp[j][i] = dp[j - 1][i - 1] + dp[j][i - 1];
                int p = prev[c[i - 1] - 'a'][i - 1];
                int got = i - 1 - j;
                if (p >= 0 && p - got >= 0) {
                    dp[j][i] -= dp[p - got][p];
                }
                dp[j][i] %= MOD;
                if (dp[j][i] < 0) {
                    dp[j][i] += MOD;
                }
            }
        }
        out.println((dp[k][n] + MOD - 1) % MOD);
	}

//    static int stupid(char[] c, int k) {
//        Set<String> set = new HashSet<String>();
//        for (int i = 0; i < 1 << c.length; i++) {
//            if (Integer.bitCount(i) != k) {
//                continue;
//            }
//            String a = "";
//            for (int j = 0; j < c.length; j++) {
//                if (((i >> j) & 1) == 1) {
//                    a += c[j];
//                }
//            }
//            set.add(a);
//        }
//        return set.size() - 1;
//    }
}
