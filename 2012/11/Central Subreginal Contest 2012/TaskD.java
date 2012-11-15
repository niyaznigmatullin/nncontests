package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        String s = in.next();
        int answer = 0;
        for (char c : s.toCharArray()) {
            if (c == '*') {
                ++answer;
            }
        }
        int bestL = -1;
        int bestR = -1;
        int[] dp = new int[n + 1];
        int[] where = new int[n + 1];
        where[n] = n;
        int sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            sum += s.charAt(i) == '.' ? 1 : -1;
            dp[i] = sum;
            where[i] = i;
            if (dp[i] < dp[i + 1]) {
                dp[i] = dp[i + 1];
                where[i] = where[i + 1];
            }
        }
        int[] z = new int[n + 1];
        for (int i = 0; i < n; i++) {
            z[i] = s.charAt(i) == '.' ? 1 : 0;
        }
        for (int i = 1; i <= n; i++) {
            z[i] += z[i - 1];
        }
        int current = 0;
        for (int left = 0; left < n; left++) {
            int bestI = where[left + 1];
            int best = dp[left + 1];
            int curBest = current + sum(z, left, n) - best + 2;
            if (curBest < answer) {
                answer = curBest;
                bestL = left;
                bestR = bestI;
            }
            if (s.charAt(left) == '*') {
                ++current;
            }
        }
        out.println(answer);
        if (bestL < 0) {
            boolean first = true;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '.') {
                    continue;
                }
                if (first) {
                    out.println(i + 1);
                    first = false;
                } else {
                    out.println("Ctrl+" + (i + 1));
                }
            }
        } else {
            out.println(bestL + 1);
            out.println("Shift+" + (bestR));
            for (int i = 0; i < n; i++) {
                if ((i < bestL || i >= bestR) && s.charAt(i) == '*' || (i >= bestL && i < bestR) && s.charAt(i) == '.') {
                    out.println("Ctrl+" + (i + 1));
                }
            }
        }
    }

    static int sum(int[] a, int l, int r) {
        int ret = a[r - 1];
        if (l > 0) {
            ret -= a[l - 1];
        }
        return ret;
    }
}
