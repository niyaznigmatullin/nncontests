package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;
import java.util.Arrays;

public class TaskA {

    static final int N = 1000001;
    static int[] dp;
    static long[] getTen;
    static {
        dp = new int[N];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 1;
        for (int i = 1; i + 1 < N; i++) {
            dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);
            int x = Integer.parseInt(new StringBuilder(i + "").reverse().toString());
            if (x > i && x < dp.length) dp[x] = Math.min(dp[x], dp[i] + 1);
        }
//        for (int i = 1; i < N; i *= 10) System.out.println(dp[i]);
        getTen = new long[20];
        getTen[0] = 1;
        for (int i = 1; i < getTen.length; i++) {
            long ten = 1;
            for (int j = 0; j < i / 2; j++) ten *= 10;
            long curAns = ten;
            if (i % 2 == 1) ten *= 10;
            curAns += ten;
            getTen[i] = getTen[i - 1] + curAns - 1;
            if (i == 1) getTen[i]--;
        }
//        System.out.println(dp[10000] + " " + getTen[4]);
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        out.print("Case #" + testNumber + ": ");
        System.err.println(testNumber);
//        out.println(dp[in.nextInt()]);
        long n = in.nextLong();
        String s = n + "";
        int[] d = new int[s.length()];
        for (int i = 0; i < s.length(); i++) d[i] = s.charAt(i) - '0';
        long ans = getTen[s.length() - 1] + n - BigInteger.TEN.pow(s.length() - 1).longValue();
        for (int prefix = 0; prefix + 1 < s.length(); prefix++) {
            int[] q = new int[d.length];
            for (int j = 0; j <= d[prefix]; j++) {
                for (int i = 0; i < prefix; i++) q[i] = d[i];
                q[prefix] = j;
                long reversed = 0;
                for (int k = d.length - 1; k >= 0; k--) {
                    reversed = reversed * 10 + q[k];
                }
                long have = 0;
                for (int k : q) {
                    have = have * 10 + k;
                }
                have++;
                if (have > n) continue;
                ans = Math.min(ans, n - have + reversed + getTen[s.length() - 1] + 1);
            }
        }
        out.println(ans);
    }
}
