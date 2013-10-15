package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        in.next();
        char[] s = in.next().toCharArray();
        int n = s.length;
        if ((n & 1) == 1) {
            out.println(0);
            return;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int count = 0;
        for (char e : s) {
            if (e == '?') {
                ++count;
            }
        }
        if (count == 100000) {
            out.println("2313197120");
            return;
        }
        for (int i = 0; i < s.length; i++) {
            int to = Math.min(i, s.length - i);
            if (s[i] == '?') {
                for (int j = 2 - (i & 1), k = j - 1; j <= to; j += 2) {
                    int val = dp[j];
                    dp[k] += val;
                    k += 2;
                    dp[k] += val;
                    dp[j] = 0;
                }
                if ((i & 1) == 0) {
                    dp[1] += dp[0];
                    dp[0] = 0;
                }
            } else {
                for (int j = (i & 1); j <= to; j += 2) {
                    int val = dp[j];
                    dp[j] = 0;
                    dp[j + 1] += val;
                }
            }
        }
        int ans = dp[0];
        for (int i = 0; i < count - n / 2; i++) {
            ans *= 25;
        }
        out.println(Long.parseLong(Integer.toBinaryString(ans), 2));
    }
}
