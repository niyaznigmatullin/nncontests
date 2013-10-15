package lib.test.on2013_06.on2013_06_08_IPSC_2013.A1;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class A1 {
    static int[] dp = new int[10001];
    static int[] last = new int[10001];
    static int[] MONEY = {1, 2, 5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000};

    static {
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        Arrays.fill(last, -1);
        for (int i = 0; i < MONEY.length; i++) {
            for (int j = 0; j + MONEY[i] < dp.length; j++) {
                int val = dp[j];
                if (val == Integer.MAX_VALUE) {
                    continue;
                }
                int nj = j + MONEY[i];
                if (dp[nj] > val + 1) {
                    dp[nj] = val + 1;
                    last[nj] = i;
                }
            }
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int[] ans = new int[MONEY.length];
            int a = in.nextInt();
            int b = in.nextInt();
            int s = a * 100 + b;
            while (s > 0) {
                ans[last[s]]++;
                s -= MONEY[last[s]];
            }
            int cnt = 0;
            for (int l : ans) {
                cnt += l;
            }
            if (cnt > 200) {
                throw new AssertionError();
            }
            out.printArray(ans);
        }

    }
}
