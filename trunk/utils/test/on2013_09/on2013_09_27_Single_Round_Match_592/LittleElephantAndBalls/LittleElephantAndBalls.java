package lib.test.on2013_09.on2013_09_27_Single_Round_Match_592.LittleElephantAndBalls;



import ru.ifmo.niyaz.arrayutils.ArrayUtils;

import java.util.Arrays;

public class LittleElephantAndBalls {
    public int getNumber(String str) {
        int[][] dp = new int[8][8];
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MIN_VALUE);
        }
        dp[0][0] = 0;
        for (char c : str.toCharArray()) {
            int[][] next = new int[8][8];
            for (int[] d : next) {
                Arrays.fill(d, Integer.MIN_VALUE);
            }
            int id = "RGB".indexOf(c);
            for (int m1 = 0; m1 < 8; m1++) {
                for (int m2 = 0; m2 < 8; m2++) {
                    int val = dp[m1][m2];
                    if (val == Integer.MIN_VALUE) continue;
                    val += Integer.bitCount(m1) + Integer.bitCount(m2);
                    next[m1 | (1 << id)][m2] = Math.max(next[m1 | (1 << id)][m2], val);
                    next[m1][m2 | (1 << id)] = Math.max(next[m1][m2 | (1 << id)], val);
                }
            }
            dp = next;
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                ans = Math.max(ans, dp[i][j]);
        return ans;
    }
}
