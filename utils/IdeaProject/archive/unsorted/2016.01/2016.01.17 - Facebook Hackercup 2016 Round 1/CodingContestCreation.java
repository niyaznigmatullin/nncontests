package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class CodingContestCreation {

    static boolean check(int[] a, int left, int right) {
        int have = right - left;
        for (int i = left; i + 1 < right; i++) {
            if (a[i] >= a[i + 1]) {
                return false;
            }
            have += (a[i + 1] - a[i] - 1) / 10;
        }
        return have <= 4;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = i - 1; j >= i - 4 && j >= 0; j--) {
                if (check(a, j, i)) {
                    dp[i] = Math.min(dp[i], dp[j] + 4 - (i - j));
                }
            }
        }
        out.println("Case #" + testNumber + ": " + dp[n]);
    }
}
