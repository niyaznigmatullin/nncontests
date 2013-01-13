package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int[][] dp = new int[n][n + 1];
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        dp[0][1] = 0;
        int[][] lastTwo = new int[n][n + 1];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int val = dp[j][i];
                if (val == Integer.MAX_VALUE) {
                    continue;
                }
                if (i + 2 <= n) {
                    int val1 = val + Math.max(a[i], a[i + 1]);
                    if (dp[j][i + 2] > val1) {
                        dp[j][i + 2] = val1;
                        lastTwo[j][i + 2] = i * n + (i + 1);
                    }
                    int val2 = val + Math.max(a[i], a[j]);
                    if (dp[i + 1][i + 2] > val2) {
                        dp[i + 1][i + 2] = val2;
                        lastTwo[i + 1][i + 2] = j * n + i;
                    }
                    int val3 = val + Math.max(a[i + 1], a[j]);
                    if (dp[i][i + 2] > val3) {
                        dp[i][i + 2] = val3;
                        lastTwo[i][i + 2] = j * n + i + 1;
                    }
                }
            }
        }
        int m = (n & 1) == 0 ? n - 1 : n;
        int answer = Integer.MAX_VALUE;
        int lastOne = -1;
        for (int i = 0; i < n; i++) {
            if (dp[i][m] == Integer.MAX_VALUE) {
                continue;
            }
            int cur = dp[i][m] + (m == n ? a[i] : Math.max(a[i], a[m]));
            if (answer > cur) {
                answer = cur;
                lastOne = i;
            }
        }
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = lastOne, j = m; j > 1; ) {
            int x = i;
            int y = lastTwo[i][j] / n;
            int z = lastTwo[i][j] % n;
            ans.add(y * n + z);
            if (x > y) {
                int t = x;
                x = y;
                y = t;
            }
            if (x > z) {
                int t = x;
                x = z;
                z = t;
            }
            if (y > z) {
                int t = y;
                y = z;
                z = t;
            }
            i = x;
            j = y;
        }
        Collections.reverse(ans);
        out.println(answer);
        for (int i : ans) {
            out.println((i / n + 1) + " " + (i % n + 1));
        }
        if ((n & 1) == 0) {
            out.println((lastOne + 1) + " " + n);
        } else {
            out.println(lastOne + 1);
        }
    }
}
