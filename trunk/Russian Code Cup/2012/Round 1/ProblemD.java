package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class ProblemD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int hadTo = in.nextInt();
        int payed = in.nextInt();
        int n = in.nextInt();
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = in.nextInt();
        }
        boolean[][] canDo = new boolean[n][Math.max(hadTo, payed) + 1];
        for (int z = 0; z < n; z++) {
            boolean[] c = canDo[z];
            c[0] = true;
            for (int i = 0; i < n; i++) {
                if (i == z) {
                    continue;
                }
                int val = coins[i];
                for (int j = val; j < c.length; j++) {
                    c[j] |= c[j - val];
                }
            }
        }
        boolean[][] canUse = new boolean[n][Math.max(hadTo, payed) + 1];
        for (int i = 0; i < n; i++) {
            for (int j = hadTo, k = 0; j >= 0; j -= coins[i], ++k) {
                if (canDo[i][j]) {
                    canUse[i][k] = true;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int dif = payed - hadTo;
                int dif2 = coins[j] - coins[i];
                if (dif % dif2 != 0) {
                    continue;
                }
                int c = dif / dif2;
                if (c < 0 || c >= canUse[i].length || !canUse[i][c]) {
                    continue;
                }
                ++ans;
            }
        }
        out.println(ans);
    }
}
