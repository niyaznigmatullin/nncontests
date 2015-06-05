package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Penguins {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[][][] dp = new int[2][n + 1][k];
        for (int[][] d1 : dp) {
            for (int[] d2 : d1) Arrays.fill(d2, Integer.MAX_VALUE);
        }
        dp[0][0][0] = dp[1][0][0] = 0;
        char[] c = in.next().toCharArray();
        for (int i = 1; i <= n; i++) {
            int have = c[i - 1] - '0';
            for (int g = 0; g < k; g++) {
                for (int last = 0; last < 2; last++) {
                    int val = dp[last][i - 1][g];
                    if (val == Integer.MAX_VALUE)
                        continue;
                    for (int put = 0; put < 2; put++) {
                        int cost = put == have ? 1 : 0;
                        int nG = g + (last == put ? 0 : 1);
                        if (nG >= k) continue;
                        if (dp[put][i][nG] > val + cost) {
                            dp[put][i][nG] = val + cost;
                        }
                    }
                }
            }
        }
        int lastBit = -1;
        int bestG = -1;
        for (int g = 0; g < k; g++) {
            for (int last = 0; last < 2; last++) {
                if (lastBit < 0 || dp[last][n][g] < dp[lastBit][n][bestG]) {
                    lastBit = last;
                    bestG = g;
                }
            }
        }
        char[] ans = new char[n];
        for (int g = bestG, put = lastBit, i = n; i > 0; i--) {
            int have = c[i - 1] - '0';
            boolean found = false;
            for (int last = 0; last < 2; last++) {
                int pG = g - (last == put ? 0 : 1);
                if (pG < 0) continue;
                int cost = put == have ? 1 : 0;
                if (dp[put][i][g] == dp[last][i - 1][pG] + cost) {
                    found = true;
                    ans[i - 1] = (char) ((1 - put) + '0');
                    put = last;
                    g = pG;
                    break;
                }
            }
            if (!found) throw new AssertionError();
        }
        out.println(ans);
    }
}
