import java.io.*;
import java.util.*;

public class LCSQ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] c = sc.next().toCharArray();
        char[] d = sc.next().toCharArray();
        int[][] dp = new int[c.length + 1][d.length + 1];
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < d.length; j++) {
                dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                if (c[i] == d[j]) {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], dp[i][j] + 1);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = c.length, j = d.length; i > 0 && j > 0; ) {
            if (c[i - 1] == d[j - 1] && dp[i - 1][j - 1] == dp[i][j] - 1) {
                sb.append(c[i - 1]);
                --i;
                --j;
            } else {
                if (dp[i - 1][j] == dp[i][j]) --i; else --j;
            }
        }
        sb.reverse();
        System.out.println(sb.toString());
    }
}