package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskA {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        int[][] dp = new int[27][s.length()];
        boolean[][] bad = new boolean[27][27];
        int k = in.nextInt();
        for (int i = 0; i < k; i++) {
            char[] d = in.next().toCharArray();
            int c1 = d[0] - 'a';
            int c2 = d[1] - 'a';
            bad[c1][c2] = bad[c2][c1] = true;
        }
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        dp[s.charAt(0) - 'a'][0] = 0;
        dp[26][0] = 1;
        for (int i = 1; i < s.length(); i++) {
            int cur = s.charAt(i) - 'a';
            for (int last = 0; last < 27; last++) {
                int got = dp[last][i - 1];
                if (got == Integer.MAX_VALUE) {
                    continue;
                }
                if (!bad[last][cur]) {
                    dp[cur][i] = Math.min(dp[cur][i], got);
                }
                dp[last][i] = Math.min(dp[last][i], got + 1);
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 27; i++) {
            ans = Math.min(ans, dp[i][s.length() - 1]);
        }
        out.println(ans);
	}
}
