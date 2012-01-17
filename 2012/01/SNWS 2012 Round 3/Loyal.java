package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class Loyal {
    static int[][][][][] dp;
    static final int MAXSHIPS = 15;
    static final int MAXMEN = 40;

    static {
        dp = new int[MAXSHIPS + 1][MAXMEN + 1][][][];
        for (int[][][][] d : dp) {
            for (int i = 0; i <= MAXMEN; i++) {
                int oppose = Math.min(i, MAXMEN - i);
                d[i] = new int[oppose + 1][][];
                for (int j = 0; j <= oppose; j++) {
                    d[i][j] = new int[MAXMEN + 1][];
                    for (int k = 0; k <= MAXMEN; k++) {
                        d[i][j][k] = new int[MAXMEN - i - j + 1];
                        Arrays.fill(d[i][j][k], -1);
                    }
                }
            }
        }
    }

    static boolean go(int shipsLeft, int loyalLeft, int oppositionLeft, int maxOpposition, int last) {
//        System.err.println(shipsLeft + " " + loyalLeft + " " + oppositionLeft + " " + maxOpposition + " " + last);
        if (loyalLeft < shipsLeft) {
            return false;
        }
        if (shipsLeft == 0) {
            return loyalLeft == 0 && oppositionLeft == 0;
        }
        if (dp[shipsLeft][loyalLeft][oppositionLeft][maxOpposition][last] >= 0) {
            return dp[shipsLeft][loyalLeft][oppositionLeft][maxOpposition][last] == 1;
        }
        boolean ret = false;
        all:
        for (int curLoyal = 1; curLoyal <= 1 + loyalLeft - shipsLeft; curLoyal++) {
            for (int curOpposition = 0;
                 curOpposition <= maxOpposition &&
                         curOpposition <= oppositionLeft
                    ; curOpposition++) {
                if (!(oppositionLeft - curOpposition <= loyalLeft - curLoyal)) {
                    continue;
                }
                int nextMax = curLoyal - (last + curOpposition);
                if (nextMax < 0) {
                    break;
                }
                if (go(shipsLeft - 1, loyalLeft - curLoyal, oppositionLeft - curOpposition, nextMax, curOpposition)) {
                    ret = true;
                    break all;
                }
            }
        }
        dp[shipsLeft][loyalLeft][oppositionLeft][maxOpposition][last] = ret ? 1 : 0;
//        System.err.println("ret = " + ret + " for " + shipsLeft + " " + loyalLeft + " " + oppositionLeft + " " + maxOpposition + " " + last);
        return ret;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int t = in.nextInt();
        for (int testCase = 0; testCase < t; testCase++) {
            int n = in.nextInt();
            int k = in.nextInt();
            int l = 0;
            int r = k / 2 + 1;
            while (l < r - 1) {
                int mid = l + r >> 1;
                if (go(n, k - mid, mid, mid, 0)) {
                    l = mid;
                } else {
                    r = mid;
                }
            }
            out.println(l);
        }
    }
}
