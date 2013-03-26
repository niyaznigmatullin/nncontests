import java.io.*;
import java.util.*;

public class Ctea {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] c = sc.next().toCharArray();
        char[] d = sc.next().toCharArray();
        int[][] dp = new int[c.length + 1][d.length + 1];
        int[][] count = new int[c.length + 1][d.length + 1];
        final int MOD = 134_217_727;
        for (int i = 0; i <= c.length; i++) {
            for (int j = 0; j <= d.length; j++) {
                int ans = Integer.MAX_VALUE;
                int cnt = 0;
                if (i > 0) {
                    if (dp[i - 1][j] + 1 < ans) {
                        cnt = count[i - 1][j];
                        ans = dp[i - 1][j] + 1;
                    } else if (dp[i - 1][j] + 1 == ans) {
                        cnt += count[i - 1][j];
                        if (cnt >= MOD) cnt -= MOD;
                    }
                }
                if (j > 0) {
                    if (dp[i][j - 1] + 1 < ans) {
                        cnt = count[i][j - 1];
                        ans = dp[i][j - 1] + 1;
                    } else if (dp[i][j - 1] + 1 == ans) {
                        cnt += count[i][j - 1];
                        if (cnt >= MOD) cnt -= MOD;
                    }
                }
                if (i > 0 && j > 0) {
                    int better = c[i - 1] == d[j - 1] ? dp[i - 1][j - 1] : dp[i - 1][j - 1] + 1;
                    if (better < ans) {
                        ans = better;
                        cnt = count[i - 1][j - 1];
                    } else if (better == ans) {
                        cnt += count[i - 1][j - 1];
                        if (cnt >= MOD) cnt -= MOD;
                    }
                }
                if (ans == Integer.MAX_VALUE) {
                    ans = 0;
                    cnt = 1;
                }
                dp[i][j] = ans;
                count[i][j] = cnt;
            }
        }        
        System.out.println(count[c.length][d.length]);
    }
}