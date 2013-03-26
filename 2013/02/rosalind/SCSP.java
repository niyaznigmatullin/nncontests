import java.io.*;
import java.util.*;

public class SCSP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] c = sc.next().toCharArray();
        char[] d = sc.next().toCharArray();
        int[][] dp = new int[c.length + 1][d.length + 1];
        for (int i = 0; i <= c.length; i++) {
            for (int j = 0; j <= d.length; j++) {
                if (i == 0 && j == 0) dp[i][j] = 0; else {
                    int ans = Integer.MAX_VALUE;
                    if (i > 0) ans = Math.min(ans, dp[i - 1][j] + 1);
                    if (j > 0) ans = Math.min(ans, dp[i][j - 1] + 1);
                    if (i > 0 && j > 0 && c[i - 1] == d[j - 1]) ans = Math.min(ans, dp[i - 1][j - 1] + 1);
                    dp[i][j] = ans;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = c.length, j = d.length; i > 0 || j > 0; ) {
            if (i > 0 && j > 0 && c[i - 1] == d[j - 1] && dp[i - 1][j - 1] == dp[i][j] - 1) {
                sb.append(c[i - 1]);
                --i;
                --j;
            } else {
                if (i > 0 && dp[i - 1][j] == dp[i][j] - 1) {
                    sb.append(c[i - 1]);
                    --i;
                } else {
                    sb.append(d[j - 1]);
                    --j;
                }
            }
        }
        sb.reverse();
        System.out.println(sb.toString());
    }
}