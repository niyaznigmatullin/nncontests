import java.io.*;
import java.util.*;

public class Gcon {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
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
        char[] c = sc.next().toCharArray();
        char[] d = sc.next().toCharArray();
        int[][] dp = new int[c.length + 1][d.length + 1];
        for (int i = 0; i <= c.length; i++) {
            for (int j = 0; j <= d.length; j++) {
                int ans = Integer.MIN_VALUE;
                for (int k = 0; k < i; k++) {
                    ans = Math.max(ans, dp[k][j] - 5);
                }
                for (int k = 0; k < j; k++) {
                    ans = Math.max(ans, dp[i][k] - 5);
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
        System.out.println(dp[c.length][d.length]);
    }
}