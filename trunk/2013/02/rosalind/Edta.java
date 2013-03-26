import java.io.*;
import java.util.*;

public class Edta {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] c = sc.next().toCharArray();
        char[] d = sc.next().toCharArray();
        int[][] dp = new int[c.length + 1][d.length + 1];
        for (int i = 0; i <= c.length; i++) {
            for (int j = 0; j <= d.length; j++) {
                int ans = Integer.MAX_VALUE;
                if (i > 0) ans = Math.min(ans, dp[i - 1][j] + 1);
                if (j > 0) ans = Math.min(ans, dp[i][j - 1] + 1);
                if (i > 0 && j > 0) {
                    if (c[i - 1] == d[j - 1]) ans = Math.min(ans, dp[i - 1][j - 1]); else
                                ans = Math.min(ans, dp[i - 1][j - 1] + 1);
                }
                if (ans == Integer.MAX_VALUE) ans = 0;
                dp[i][j] = ans;
            }
        }        
        System.out.println(dp[c.length][d.length]);
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (int i = c.length, j = d.length; i > 0 || j > 0;) {
            if (i > 0 && dp[i - 1][j] + 1 == dp[i][j]) {
                s1.append(c[i - 1]);
                s2.append('-');
                --i;   
            } else if (j > 0 && dp[i][j - 1] + 1 == dp[i][j]) {
                s1.append('-');
                s2.append(d[j - 1]);
                --j;
            } else if (i > 0 && j > 0) {
                s1.append(c[i - 1]);
                s2.append(d[j - 1]);
                --i;
                --j;
            }
        }
        s1.reverse();
        s2.reverse();
        System.out.println(s1);
        System.out.println(s2);
    }
}