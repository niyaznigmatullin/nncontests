package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long n = in.nextLong();
        long[] dp = new long[1234];
        dp[0] = 1;
        dp[1] = 2;
        int i = 2;
        while (true) {
            dp[i] = dp[i - 1] + dp[i - 2];
            if (dp[i] > n) break;
            ++i;
        }
        out.println(i - 1);
    }
}
