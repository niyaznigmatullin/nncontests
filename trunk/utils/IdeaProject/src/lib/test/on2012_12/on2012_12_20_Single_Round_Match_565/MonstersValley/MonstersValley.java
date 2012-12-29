package lib.test.on2012_12.on2012_12_20_Single_Round_Match_565.MonstersValley;



import java.util.Arrays;

public class MonstersValley {
    public int minimumPrice(long[] dread, int[] price) {
        int n = dread.length;
        int all = 2 * n;
        long[] dp = new long[all + 1];
        Arrays.fill(dp, Long.MIN_VALUE);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            long e = dread[i];
            long[] next = new long[all + 1];
            Arrays.fill(next, Long.MIN_VALUE);
            for (int j = 0; j <= all; j++) {
                if (dp[j] >= e) {
                    next[j] = dp[j];
                }
            }
            int c = price[i];
            for (int j = 0; j <= all; j++) {
                long val = dp[j];
                if (val == Long.MIN_VALUE) {
                    continue;
                }
                if (j + c <= all) {
                    next[j + c] = Math.max(next[j + c], val + e);
                }
            }
            dp = next;
        }
        int cost = Integer.MAX_VALUE;
        for (int i = 0; i <= all; i++) {
            if (dp[i] != Long.MIN_VALUE) {
                cost = i;
                break;
            }
        }
        return cost;
    }
}
