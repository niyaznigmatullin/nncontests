package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = in.nextInt();
        int[] longest1 = lis(n, a);
        for (int i = 0; i < n; i++) a[i] = -a[i];
        for (int i = 0; i < n - i - 1; i++) {
            int t = a[i];
            a[i] = a[a.length - i - 1];
            a[a.length - i - 1] = t;
        }
        int[] longest2 = lis(n, a);
        for (int i = 0; i < n - i - 1; i++) {
            int t = longest2[i];
            longest2[i] = longest2[n - i - 1];
            longest2[n - i - 1] = t;
        }
        int maximal = 0;
        for (int i = 0; i < n; i++) {
            maximal = Math.max(maximal, longest1[i] + longest2[i]);
        }
        boolean[] can = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (longest1[i] + longest2[i] == maximal) can[i] = true;
        }
        int[] countLens = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (can[i]) countLens[longest1[i]]++;
        }
        for (int i = 0; i < n; i++) {
            if (can[i] && countLens[longest1[i]] == 1) {
                out.print(3);
            } else if (can[i]) out.print(2); else out.print(1);
        }
        out.println();
    }

    private int[] lis(int n, int[] a) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = Integer.MIN_VALUE;
        int cn = 1;
        int[] longest = new int[n];
        for (int i = 0; i < n; i++) {
            int l = 0;
            int r = cn;
            while (l < r - 1) {
                int mid = (l + r) >> 1;
                if (dp[mid] >= a[i]) {
                    r = mid;
                } else {
                    l = mid;
                }
            }
            longest[i] = l + 1;
            if (r + 1 > cn) cn = r + 1;
            dp[r] = a[i];
        }
        return longest;
    }
}
