package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        int sum = 0;
        for (char c : s.toCharArray()) {
            sum += c == '+' ? 1 : -1;
        }
        int shift = 50;
        double[] dp = new double[123];
        dp[shift] = 1.;
        for (char c : in.next().toCharArray()) {
            double[] next = new double[123];
            if (c == '+') {
                for (int i = 0; i < dp.length; i++) {
                    if (dp[i] == 0) continue;
                    next[i + 1] = dp[i];
                }
            } else if (c == '-') {
                for (int i = 0; i < dp.length; i++) {
                    if (dp[i] == 0) continue;
                    next[i - 1] = dp[i];
                }
            } else {
                for (int i = 0; i < dp.length; i++) {
                    if (dp[i] == 0) continue;
                    next[i - 1] += dp[i] * .5;
                    next[i + 1] += dp[i] * .5;
                }
            }
            dp = next;
        }
        out.println(dp[sum + shift]);
    }
}
