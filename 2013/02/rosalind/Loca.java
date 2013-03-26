import java.io.*;
import java.util.*;

public class Loca {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Scanner file = new Scanner(new File("pam250"));
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
        char[] c = sc.next().toCharArray();
        char[] d = sc.next().toCharArray();
        int[][] dp = new int[c.length + 1][d.length + 1];
        for (int i = 0; i <= c.length; i++) {
            for (int j = 0; j <= d.length; j++) {
                int ans = 0;
                if (i > 0) {
                    ans = Math.max(ans, dp[i - 1][j] - 5);
                }
                if (j > 0) {
                    ans = Math.max(ans, dp[i][j - 1] - 5);
                }
                if (i > 0 && j > 0) {
                    ans = Math.max(ans, dp[i - 1][j - 1] + cost[where[c[i - 1]]][where[d[j - 1]]]);
                }
                if (ans == Integer.MIN_VALUE) {
                    ans = 0;
                }
                dp[i][j] = ans;
            }
        }
        int best = Integer.MIN_VALUE;
        int bi = -1;
        int bj = -1;
        for (int i = 0; i <= c.length; i++) {
            for (int j = 0; j <= d.length; j++) {
                if (dp[i][j] > best) {
                    best = dp[i][j];
                    bi = i;
                    bj = j;
                }
            }
        }
        System.out.println(best);
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = bi, j = bj; dp[i][j] > 0; ) {
            if (i > 0 && dp[i][j] == dp[i - 1][j] - 5) {            
                --i;
                sb.append(c[i]);
            } else if (j > 0 && dp[i][j] == dp[i][j - 1] - 5) {
                --j;
                sb2.append(d[j]);   
            } else {
                --i;
                --j;
                sb.append(c[i]);
                sb2.append(d[j]);
            }
        }
        sb.reverse();
        sb2.reverse();
        System.out.println(sb);
        System.out.println(sb2);
    }
}