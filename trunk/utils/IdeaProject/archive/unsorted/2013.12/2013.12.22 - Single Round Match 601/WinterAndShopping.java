package coding;

import java.util.Arrays;

public class WinterAndShopping {

    static final int MOD = 1000000007;
    static final int[][] C;

    static {
        C = new int[555][555];
        for (int i = 0; i < C.length; i++) {
            C[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                C[i][j] = add(C[i - 1][j - 1], C[i - 1][j]);
            }
        }
    }

    public int getNumber(int[] first, int[] red, int[] green, int[] blue) {
        int lastMoment = 0;
        int n = first.length;
        for (int i = 0; i < n; i++) {
            lastMoment = Math.max(lastMoment, first[i] + red[i] + green[i] + blue[i]);
        }
        int[] b1 = new int[lastMoment];
        int[] b2 = new int[lastMoment];
        Arrays.fill(b1, -1);
        Arrays.fill(b2, -1);
        for (int i = 0; i < n; i++) {
            int last = first[i] + red[i] + green[i] + blue[i];
            for (int j = first[i]; j < last; j++) {
                if (b1[j] < 0) {
                    b1[j] = i;
                } else if (b2[j] < 0) {
                    b2[j] = i;
                } else {
                    throw new AssertionError();
                }
            }
        }
        int[][] dp = new int[1][1];
        dp[0][0] = 1;
        int lastID = -1;
        for (int curMoment = 0; curMoment < lastMoment; ) {
//            System.out.println(lastID + " " + curMoment);
            int nextMoment = curMoment;
            while (nextMoment < lastMoment && b1[nextMoment] == b1[curMoment] && b2[nextMoment] == b2[curMoment]) {
                nextMoment++;
            }
            if (b1[curMoment] < 0) {
                curMoment = nextMoment;
                continue;
            }
            if (b2[curMoment] < 0) {
                int who = b1[curMoment];
                int curRed = red[who];
                int curBlue = blue[who];
                int curGreen = green[who];
                while (curMoment < nextMoment) {
                    int[][] next = new int[Math.min(dp.length + 1, curRed + 1)][Math.min(dp[0].length + 1, curGreen + 1)];
                    for (int a = 0; a < dp.length; a++) {
                        for (int b = 0; b < dp[a].length; b++) {
                            int val = dp[a][b];
                            if (val == 0) continue;
                            int c = curMoment - first[who] - a - b;
                            if (a + 1 <= curRed) {
                                next[a + 1][b] = add(next[a + 1][b], val);
                            }
                            if (b + 1 <= curGreen) {
                                next[a][b + 1] = add(next[a][b + 1], val);
                            }
                            if (c + 1 <= curBlue) {
                                next[a][b] = add(next[a][b], val);
                            }
                        }
                    }
                    dp = next;
                    ++curMoment;
                }
                lastID = who;
                if (curMoment == first[who] + curRed + curBlue + curGreen) {
                    lastID = -1;
                    dp = new int[][]{{dp[curRed][curGreen]}};
                }
                continue;
            }
            int who1 = b1[curMoment];
            int who2 = b2[curMoment];
            if (lastID < 0) {
                lastID = who1;
            }
            if (lastID != who1) {
                int t = who1;
                who1 = who2;
                who2 = t;
            }
            int red1 = red[who1];
            int green1 = green[who1];
            int blue1 = blue[who1];
            int all1 = red1 + green1 + blue1;
            int end1 = first[who1] + all1;
            int red2 = red[who2];
            int green2 = green[who2];
            int blue2 = blue[who2];
            int all2 = red2 + green2 + blue2;
            int end2 = first[who2] + all2;
//            System.out.println("  " + who1 + " " + who2);
            if (end1 == nextMoment) {
                int[][] next = new int[Math.min(nextMoment - curMoment, red2) + 1][Math.min(nextMoment - curMoment, green2) + 1];
                for (int a = 0; a < dp.length; a++) {
                    for (int b = 0; b < dp[a].length; b++) {
                        int val = dp[a][b];
                        if (val == 0) continue;
                        int c = (curMoment - first[who1]) - a - b;
                        int nRed2 = (red1 - a);
                        int nGreen2 = (green1 - b);
                        int nBlue2 = (blue1 - c);
//                        System.out.println(val + " " + nRed2 + " " + nGreen2 + " " + nBlue2 + " " + a + " " + b + " " + c);
                        val = mul(val, mul(c(nRed2 + nGreen2 + nBlue2, nRed2), c(nGreen2 + nBlue2, nGreen2)));
//                        System.out.println(val);
                        if (nRed2 <= red2 && nGreen2 <= green2 && nBlue2 <= blue2) {
                            next[nRed2][nGreen2] = add(next[nRed2][nGreen2], val);
                        }
                    }
                }
                dp = next;
                lastID = who2;
            } else {
                int[][] next = new int[Math.min(dp.length + nextMoment - curMoment, red1 + 1)][Math.min(dp[0].length + nextMoment - curMoment, green1 + 1)];
                for (int a = 0; a < dp.length; a++) {
                    for (int b = 0; b < dp[a].length; b++) {
                        int val = dp[a][b];
                        if (val == 0) continue;
                        int c = curMoment - first[who1] - a - b;
                        int nRed1 = a + red2;
                        int nGreen1 = b + green2;
                        int nBlue1 = c + blue2;
                        val = mul(val, mul(c(red2 + green2 + blue2, red2), c(green2 + blue2, green2)));
                        if (nRed1 <= red1 && nGreen1 <= green1 && nBlue1 <= blue1) {
                            next[nRed1][nGreen1] = add(next[nRed1][nGreen1], val);
                        }
                    }
                }
                dp = next;
                lastID = who1;
            }
            if (end1 == nextMoment && end2 == nextMoment) {
                dp = new int[][]{{dp[red[lastID]][green[lastID]]}};
                lastID = -1;
            }
            curMoment = nextMoment;
        }
        return dp[0][0];
    }


    static int c(int n, int k) {
        if (k < 0 || k > n) return 0;
        return C[n][k];
    }

    static int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }

    static int add(int a, int b) {
        a += b;
        if (a >= MOD) a -= MOD;
        return a;
    }
}
