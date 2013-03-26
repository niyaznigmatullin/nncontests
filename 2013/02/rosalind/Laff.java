import java.io.*;
import java.util.*;

public class Laff {
    public static void main(String[] args) throws IOException {
        Scanner file = new Scanner(new File("blosum62"));
        char[] col = new char[20];
        for (int i = 0; i < 20; i++) col[i] = file.next().charAt(0);
        int[][] cost = new int[20][20];
        int[] where = new int[256];
        for (int i = 0; i < 20; i++) {
            file.next();
            where[col[i]] = i;
            for (int j = 0; j < 20; j++) {
                cost[i][j] = file.nextInt();
            }
        }
        Scanner sc = new Scanner(System.in);
        char[] c = sc.next().toCharArray();
        char[] d = sc.next().toCharArray();
        int[][][] dp = new int[3][c.length + 1][d.length + 1];
        for (int i = 1; i <= c.length; i++) {
            for (int j = 1; j <= d.length; j++) {
                dp[0][i][j] = Math.max(dp[2][i - 1][j - 1], Math.max(dp[0][i - 1][j - 1], dp[1][i - 1][j - 1])) + cost[where[c[i - 1]]][where[d[j - 1]]];
                dp[1][i][j] = Math.max(dp[1][i - 1][j] - 1, Math.max(dp[0][i - 1][j], dp[2][i - 1][j]) - 11);
//                dp[1][i][j] = Math.max(dp[1][i - 1][j], dp[1][i][j - 1]) - 1;
                dp[2][i][j] = Math.max(dp[2][i][j - 1] - 1, Math.max(dp[0][i][j - 1], dp[1][i][j - 1]) - 11);
//                dp[1][i][j] = Math.max(dp[1][i][j], Math.max(dp[0][i - 1][j], dp[0][i][j - 1]) - 11);
                if (dp[0][i][j] < 0) dp[0][i][j] = 0;
                if (dp[1][i][j] < 0) dp[1][i][j] = 0;
                if (dp[2][i][j] < 0) dp[2][i][j] = 0;
            }
        }
        int best = Integer.MIN_VALUE;
        int bi = -1;
        int bj = -1;
        int bk = -1;
        for (int i = 1; i <= c.length; i++) {
            for (int j = 1; j <= d.length; j++) {
                for (int k = 0; k < 2; k++) {
                    if (best < dp[k][i][j]) {
                        best = dp[k][i][j];
                        bi = i;
                        bj = j;
                        bk = k;
                    }
                }
            }
        }
        System.out.println(best);
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        all: while (dp[bk][bi][bj] > 0) {
            if (bk == 0) {
                int ccost = cost[where[c[bi - 1]]][where[d[bj - 1]]];
                if (dp[1][bi - 1][bj - 1] + ccost == dp[bk][bi][bj]) bk = 1; else
                if (dp[2][bi - 1][bj - 1] + ccost == dp[bk][bi][bj]) bk = 2;
                --bi;
                --bj;
                s1.append(c[bi]);
                s2.append(d[bj]);
                continue;
            } else if (bk == 1) {
                int val = dp[bk][bi][bj];
                for (int nk = 0; nk < 3; nk++) {
                    int ccost = nk == bk ? 1 : 11;
                    if (dp[nk][bi - 1][bj] - ccost == val) {
                        --bi;
                        bk = nk;
                        s1.append(c[bi]);
                        continue all;
                    }
                }
            } else {
                int val = dp[bk][bi][bj];
                for (int nk = 0; nk < 3; nk++) {
                    int ccost = nk == bk ? 1 : 11;
                    if (dp[nk][bi][bj - 1] - ccost == val) {
                        --bj;
                        bk = nk;
                        s2.append(d[bj]);
                        continue all;
                    }
                }
            }
            throw new AssertionError();
        }
        System.out.println(s1.reverse());
        System.out.println(s2.reverse());
    }
}