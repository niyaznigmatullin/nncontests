package lib.test.on2013_01.on2013_01_13_Codeforces_Round__160__Div__1_.A___Maxim_and_Discounts;



import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int m = in.nextInt();
        int[] b = in.readIntArray(m);
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        ArrayUtils.sort(a);
        int minimal = Integer.MAX_VALUE;
        for (int i : b) {
            minimal = Math.min(minimal, i);
        }
        int all = 0;
        for (int i : a) {
            all += i;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            for (int add = 1; add < 3; add++) {
                if (i >= add + minimal) {
                    int sum = 0;
                    for (int j = 1; j <= add; j++) {
                        sum += a[i - minimal - j];
                    }
                    dp[i] = Math.max(dp[i], dp[i - add - minimal] + sum);
                }
            }
        }
        out.println(all - dp[n]);
    }
}
