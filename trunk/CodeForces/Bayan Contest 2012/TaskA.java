package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt() + 1;
        int k = in.nextInt();
        int[] a = new int[n - 1];
        for (int i = 0; i + 1 < n; i++) {
            a[i] = in.nextInt();
        }
        int[] s = new int[n - 1];
        for (int i = 0; i + 1 < n; i++) {
            s[i] = in.nextInt();
        }
        long[] dp = new long[n];
        long[] fuel = new long[n];
        dp[0] = 0;
        fuel[0] = s[0];
        int maximal = s[0];
        for (int i = 1; i < n; i++) {
            long curTime = dp[i - 1] + a[i - 1];
            long curFuel = fuel[i - 1] - a[i - 1];
            long add = curFuel >= 0 ? 0 : (Math.abs(curFuel) + maximal - 1) / maximal;
            curFuel += add * maximal;
            curTime += add * k;
            if (i + 1 < n) {
                curFuel += s[i];
                maximal = Math.max(maximal, s[i]);
            }
            dp[i] = curTime;
            fuel[i] = curFuel;
        }
        out.println(dp[n - 1]);
    }
}
