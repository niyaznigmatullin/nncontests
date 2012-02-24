package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class SubPair {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        char[] c1 = in.next().toCharArray();
        char[] c2 = in.next().toCharArray();
        int[][] dp1 = new int[c1.length + 1][c2.length + 1];
        for (int i = 1; i <= c1.length; i++) {
            for (int j = 1; j <= c2.length; j++) {
                if (c1[i - 1] == c2[j - 1]) {
                    dp1[i][j] = dp1[i - 1][j - 1] + 1;
                }
            }
        }
        int[][] dp2 = new int[c1.length + 1][c2.length + 1];
        for (int i = c1.length - 1; i >= 0; i--) {
            for (int j = c2.length - 1; j >= 0; j--) {
                if (c1[i] == c2[j]) {
                    dp2[i][j] = dp2[i + 1][j + 1] + 1;
                }
            }
        }
        int[][] dp3 = new int[c1.length + 1][c2.length + 1];
        int[][] dp4 = new int[c1.length + 1][c2.length + 1];
        for (int i = 1; i <= c1.length; i++) {
            for (int j = 1; j <= c2.length; j++) {
                dp3[i][j] = dp1[i][j];
                dp1[i][j] = Math.max(dp1[i][j], dp1[i - 1][j]);
                dp1[i][j] = Math.max(dp1[i][j], dp1[i][j - 1]);
            }
        }
        for (int i = c1.length - 1; i >= 0; i--) {
            for (int j = c2.length - 1; j >= 0; j--) {
                dp4[i][j] = dp2[i][j];
                dp2[i][j] = Math.max(dp2[i][j], dp2[i + 1][j]);
                dp2[i][j] = Math.max(dp2[i][j], dp2[i][j + 1]);
            }
        }
        int answer = -1;
        int ansI = -1;
        int ansJ = -1;
        for (int i = 0; i <= c1.length; i++) {
            for (int j = 0; j <= c2.length; j++) {
                if (answer < dp1[i][j] + dp2[i][j]) {
                    answer = dp1[i][j] + dp2[i][j];
                    ansI = i;
                    ansJ = j;
                }
            }
        }
        int len1 = dp1[ansI][ansJ];
        String ans1 = null;
        all1: for (int i = 0; i <= ansI; i++) {
            for (int j = 0; j <= ansJ; j++) {
                if (dp3[i][j] == len1) {
                    ans1 = new String(c1).substring(i - len1, i);
                    break all1;
                }
            }
        }
        int len2 = dp2[ansI][ansJ];
        String ans2 = null;
        all2: for (int i = ansI; i <= c1.length; i++) {
            for (int j = ansJ; j <= c2.length; j++) {
                if (dp4[i][j] == len2) {
                    ans2 = new String(c1).substring(i, i + len2);
                    break all2;
                }
            }
        }
        out.println(ans1);
        out.println(ans2);
    }
}
