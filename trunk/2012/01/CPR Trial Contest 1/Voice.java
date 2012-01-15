package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Voice {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String[] a = readIt(in);
        String[] b = readIt(in);
        int m = b.length;
        int n = a.length;
        int[][] g = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                g[i][j] = getDP(a[i], b[j]);
            }
        }
        int[][] dp = new int[1 << n][1 << m];
        for (int[] e : dp) {
            Arrays.fill(e, Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        for (int i = 0; i < 1 << n; i++) {
            for (int j = 0; j < 1 << m; j++) {
                if (Integer.bitCount(i) != Integer.bitCount(j)) {
                    continue;
                }
                if (dp[i][j] == Integer.MAX_VALUE) {
                    continue;
                }
                for (int z1 = 0; z1 < n; z1++) {
                    if (((i >> z1) & 1) == 1) {
                        continue;
                    }
                    for (int z2 = 0; z2 < m; z2++) {
                        if (((j >> z2) & 1) == 1) {
                            continue;
                        }
                        dp[i | (1 << z1)][j | (1 << z2)] = Math.min(dp[i | (1 << z1)][j | (1 << z2)], dp[i][j] + g[z1][z2]);
                    }
                }
            }
        }
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 1 << n; i++) {
            answer = Math.min(answer,  dp[i][(1 << m) - 1]);
        }
        out.println(answer);
	}


    static int getDP(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int[] e : dp) {
            Arrays.fill(e, Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i > 0) {
                    dp[i][j] = Math.min(dp[i][j], 1 + dp[i - 1][j]);
                }
                if (j > 0) {
                    dp[i][j] = Math.min(dp[i][j], 1 + dp[i][j - 1]);
                }
                if (i > 0 && j > 0) {
                    if (a.charAt(i - 1) == b.charAt(j - 1)) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                    } else {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + 1);
                    }
                }
            }
        }
        return dp[a.length()][b.length()];
    }

    static String[] readIt(FastScanner in) {
        String line = in.nextLine();
        StringTokenizer st = new StringTokenizer(line);
        List<String> ret = new ArrayList<String>();
        while (st.hasMoreTokens()) {
            ret.add(st.nextToken());
        }
        return ret.toArray(new String[ret.size()]);
    }
}
