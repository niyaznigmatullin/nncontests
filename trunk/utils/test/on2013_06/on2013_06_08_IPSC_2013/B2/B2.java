package lib.test.on2013_06.on2013_06_08_IPSC_2013.B2;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;
import java.util.Arrays;

public class B2 {
//    static long[] dp = new long[101];
//    static {
//        for (int i = 2; i < dp.length; i++){
//            for (int j = 2; j <= i; j++) {
//                dp[i] = Math.max(dp[i], dp[i / j] + dp[i - i / j] + (i / j) * (i - i / j));
//            }
//        }
//        System.err.println(Arrays.toString(dp));
//    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long n = in.nextInt();
        out.println(n * (n - 1) / 2);
    }
}
