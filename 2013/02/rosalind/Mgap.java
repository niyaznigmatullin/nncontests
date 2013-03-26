import java.io.*;
import java.util.*;

public class Mgap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] c = sc.next().toCharArray();
        char[] d = sc.next().toCharArray();
        int[][] dp = new int[c.length + 1][d.length + 1];
        for (int i = 0; i <= c.length; i++) {
            for (int j = 0; j <= d.length; j++) {   
                int ans = Integer.MIN_VALUE;
                if (i > 0) ans = Math.max(ans, dp[i - 1][j]);
                if (j > 0) ans = Math.max(ans, dp[i][j - 1]);
                if (i > 0 && j > 0 && c[i - 1] == d[j - 1]) ans = Math.max(ans, dp[i - 1][j - 1] + 1);
                if (ans == Integer.MIN_VALUE) ans = 0;
                dp[i][j] = ans;
            }
        }
        System.out.println(c.length + d.length - 2 * dp[c.length][d.length]);
    }
}
