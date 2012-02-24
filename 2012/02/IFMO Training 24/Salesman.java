package mypackage;

import arrayutils.ArrayUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Salesman {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[][] d = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                d[i][j] = in.nextInt();
            }
        }
        int[][] dp = new int[n][1 << n];
        for (int[] e : dp) {
            Arrays.fill(e, Integer.MAX_VALUE);
        }
        for (int mask = 1; mask < 1 << n; mask++) {
            int z = Integer.numberOfTrailingZeros(mask);
            if ((mask & (mask - 1)) == 0) {
                dp[z][mask] = 0;
                continue;
            }
            for (int last = z; last < n; last++) {
                if (((mask >> last) & 1) == 0) {
                    continue;
                }
                int ans = Integer.MAX_VALUE;
                for (int preLast = z; preLast < n; preLast++) {
                    if (((mask >> preLast) & 1) == 0 || preLast == last) {
                        continue;
                    }
                    int got = dp[preLast][mask ^ (1 << last)];
                    if (got == Integer.MAX_VALUE) {
                        continue;
                    }
                    ans = Math.min(ans, got + d[preLast][last]);
                }
                dp[last][mask] = ans;
            }
        }
        int answer = Integer.MAX_VALUE;
        int to = -1;
        for (int j = 0; j < n; j++) {
            if (answer > dp[j][(1 << n) - 1]) {
                answer = dp[j][(1 << n) - 1];
                to = j;
            }
        }
        List<Integer> ans = new ArrayList<Integer>();
        all:
        for (int mask = (1 << n) - 1, last = to; mask > 0; ) {
            ans.add(last + 1);
            if ((mask & (mask - 1)) == 0) {
                break;
            }
            for (int preLast = 0; preLast < n; preLast++) {
                if (preLast == last) {
                    continue;
                }
                int got = dp[preLast][mask ^ (1 << last)];
                if (got == Integer.MAX_VALUE) {
                    continue;
                }
                if (dp[last][mask] == got + d[preLast][last]) {
                    mask ^= 1 << last;
                    last = preLast;
                    continue all;
                }
            }
        }
        Collections.reverse(ans);
        out.println(answer);
        out.printArray(ArrayUtils.toPrimitiveArray(ans));
    }
}
