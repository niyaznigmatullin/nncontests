package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class LongestWeirdSubsequence {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        char[] c = in.next().toCharArray();
        int[][] dp = new int[26][26];
        int[][] next = new int[26][26];
        for (char e : c) {
            for (int i = 0; i < 26; i++) {
                System.arraycopy(dp[i], 0, next[i], 0, 26);
            }
            int z = e - 'a';
            int[] nz = next[z];
            for (int i = z; i >= 0; i--) {
                int[] di = dp[i];
                for (int j = 0; j < 26; j++) {
                    nz[j] = Math.max(nz[j], di[j] + 1);
                }
            }
            for (int i = 0; i < 26; i++) {
                int[] ni = next[i];
                int[] di = dp[i];
                for (int j = z; j < 26; j++) {
                    ni[z] = Math.max(ni[z], di[j] + 1);
                }
            }
            {
                int[][] t = dp;
                dp = next;
                next = t;
            }
        }
        int answer = 0;
        for (int[] d : dp) {
            for (int e : d) {
                answer = Math.max(answer, e);
            }
        }
        out.println(answer);
    }
}
