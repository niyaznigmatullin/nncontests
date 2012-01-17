package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class Game {

    static boolean[][][][] dp;
    static final int[][] moves = {{2, 1, 0, 2}, {1, 1, 1, 1}, {0, 0, 2, 1}, {0, 3, 0, 0}, {1, 0, 0, 1}};
    static {
        dp = new boolean[31][31][31][31];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                for (int k = 0; k < dp[i][j].length; k++) {
                    for (int t = 0; t < dp[i][j][k].length; t++) {
                        for (int[] move : moves) {
                            if (i >= move[0] && j >= move[1] && k >= move[2] && t >= move[3]) {
                                dp[i][j][k][t] |= !dp[i - move[0]][j - move[1]][k - move[2]][t - move[3]];
                            }
                        }
                    }
                }
            }
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            int d = in.nextInt();
            out.println(dp[a][b][c][d] ? "First" : "Second");
        }
    }
}
