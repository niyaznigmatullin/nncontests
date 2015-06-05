package coding;

import java.math.BigInteger;
import java.util.Random;

public class CountryGroupHard {
    public String solve(int[] a) {
        int n = a.length;
        final int MOD = BigInteger.probablePrime(29, new Random(System.nanoTime())).intValue();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                int val = dp[j];
                if (val == 0) continue;
                int dif = i - j;
                boolean ok = true;
                for (int k = j; k < i; k++) {
                    if (a[k] > 0 && a[k] != dif) {
                        ok = false;
                        break;
                    }
                }
                if (!ok) continue;
                dp[i] = (dp[i] + val) % MOD;
            }
        }
        return dp[n] == 1 ? "Sufficient" : "Insufficient";
    }
}
