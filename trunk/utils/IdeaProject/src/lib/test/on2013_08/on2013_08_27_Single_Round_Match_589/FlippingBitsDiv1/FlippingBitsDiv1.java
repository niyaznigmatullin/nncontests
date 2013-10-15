package lib.test.on2013_08.on2013_08_27_Single_Round_Match_589.FlippingBitsDiv1;



import java.util.Arrays;

public class FlippingBitsDiv1 {
    public int getmin(String[] S, int M) {
        StringBuilder sb = new StringBuilder();
        for (String e : S) {
            sb.append(e);
        }
        int blocks = (sb.length() + M - 1) / M;
        char[] c = sb.toString().toCharArray();
        if (blocks < M) {
            int best = Integer.MAX_VALUE;
            for (int mask = 0; mask < 1 << blocks - 1; mask++) {
                int curCost = 0;
                for (int i = 0; i < M; i++) {
                    int cm = mask;
                    int cost1 = 0;
                    int cost2 = 0;
                    for (int j = i; j < c.length; j += M, cm >>= 1) {
                        if ((cm & 1) == 1) {
                            if (c[j] == '1') {
                                ++cost1;
                            } else {
                                ++cost2;
                            }
                        } else {
                            if (c[j] == '1') {
                                ++cost2;
                            } else {
                                ++cost1;
                            }
                        }
                    }
                    curCost += Math.min(cost1, cost2);
                }
                for (int i = 1; i < blocks; i++) {
                    if (((mask >> i) & 1) != ((mask >> (i - 1)) & 1)) {
                        ++curCost;
                    }
                }
                if (curCost < best) best = curCost;
            }
            return best;
        } else {
            int ans = Integer.MAX_VALUE;
            int[][] dp = new int[blocks][2];
            for (int mask = 0; mask < 1 << M; mask++) {
                for (int[] d : dp) {
                    Arrays.fill(d, Integer.MAX_VALUE);
                }
                dp[0][0] = getCost(mask, c, 0, M);
                for (int i = 1; i < blocks; i++) {
                    for (int j = 0; j < 2; j++) {
                        int val = dp[i - 1][j];
                        if (val == Integer.MAX_VALUE) continue;
                        for (int k = 0; k < 2; k++) {
                            int curMask = k == 0 ? mask : (mask ^ ((1 << M) - 1));
                            int curCost = getCost(curMask, c, i * M, Math.min(c.length, (i + 1) * M));
                            dp[i][k] = Math.min(dp[i][k], val + (j != k ? 1 : 0) +
                                    curCost);
                        }
                    }
                }
//                System.out.println(Arrays.deepToString(dp));
//                System.out.println(dp[blocks - 1][0] + " " + dp[blocks - 1][1]);
                ans = Math.min(ans, Math.min(dp[blocks - 1][0], dp[blocks - 1][1]));
            }
            return ans;
        }
    }

    static int getCost(int mask, char[] c, int l, int r) {
        int ret = 0;
        for (int i = 0; i < r - l; i++) {
            int ch = c[i + l] - '0';
            int cc = ((mask >> i) & 1);
            if (ch != cc) {
                ++ret;
            }
        }
//        System.out.println(new String(c, l, r - l) + " " + mask + " " + ret);
        return ret;
    }
}
