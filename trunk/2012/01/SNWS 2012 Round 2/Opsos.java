package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class Opsos {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        char[] c = in.next().toCharArray();
        int n = c.length;
        int[] edges = new int[n];
        int allMask = 1;
        for (int i = 0; i < n; i++) {
            allMask *= 3;
            char[] e = in.next().toCharArray();
            for (int j = 0; j < n; j++) {
                if (e[j] == '+') {
                    edges[i] |= 1 << j;
                }
            }
        }
        boolean[][] dp = new boolean[2][allMask];
        int curMask = 0;
        for (int i = 0; i < c.length; i++) {
            curMask *= 3;
            if (c[i] == 'G') {
                curMask++;
            } else if (c[i] == 'B') {
                curMask += 2;
            }
        }
        for (int i = allMask - 1; i >= curMask; i--) {
            int gMask = 0;
            int bMask = 0;
            int rMask = 0;
            for (int mask = i, j = n - 1; j >= 0; j--, mask /= 3) {
                int x = mask % 3;
                if (x == 0) {
                    rMask |= 1 << j;
                } else if (x == 1) {
                    gMask |= 1 << j;
                } else {
                    bMask |= 1 << j;
                }
            }
            for (int move = 0; move < 2; move++) {
                if (move == 0) {
                    for (int submask = rMask; submask > 0; submask = (submask - 1) & rMask) {
                        int connected = 0;
                        int newMask = 0;
                        for (int j = 0; j < n; j++) {
                            newMask *= 3;
                            if (((submask >> j) & 1) == 1) {
                                newMask++;
                                connected |= edges[j];
                            }
                        }
                        if ((connected & gMask) != 0) {
                            continue;
                        }
                        dp[move][i] |= !dp[1 - move][i + newMask];
                    }
                } else {
                    for (int submask = rMask; submask > 0; submask = (submask - 1) & rMask) {
                        int connected = 0;
                        int newMask = 0;
                        for (int j = 0; j < n; j++) {
                            newMask *= 3;
                            if (((submask >> j) & 1) == 1) {
                                newMask += 2;
                                connected |= edges[j];
                            }
                        }
                        if ((connected & bMask) != 0) {
                            continue;
                        }
                        dp[move][i] |= !dp[1 - move][i + newMask];
                    }
                }
            }
        }
        out.println(dp[0][curMask] ? "B" : "G");
    }
}
