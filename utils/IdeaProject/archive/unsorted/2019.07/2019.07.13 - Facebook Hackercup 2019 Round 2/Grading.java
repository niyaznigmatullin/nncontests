package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Grading {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println(testNumber);
        int h = in.nextInt();
        int s = in.nextInt();
        int k = in.nextInt();
        int[][][] values = new int[s][][];
        char[][] c = new char[s][h];
        for (int i = 0; i < h; i++) {
            String e = in.next();
            for (int j = 0; j < s; j++) {
                c[j][i] = e.charAt(j);
            }
        }
        for (int i = 0; i < s; i++) {
            values[i] = getDP(c[i]);
        }
        int[][] removes = new int[h + 1][2];
        for (int[][] e : values) {
            for (int j = 0; j <= h; j++) {
                for (int z = 0; z < 2; z++) {
                    if (e[j][z] == Integer.MAX_VALUE || removes[j][z] == Integer.MAX_VALUE) {
                        removes[j][z] = Integer.MAX_VALUE;
                    } else {
                        removes[j][z] += e[j][z];
                    }
                }
            }
        }
        int[] answer = new int[s * h + 1];
        Arrays.fill(answer, Integer.MAX_VALUE);
        for (int e = 0; e < 2; e++) {
            for (int i = h; i >= 0; i--) {
                if (removes[i][e] != Integer.MAX_VALUE) {
                    for (int j = removes[i][e]; j <= s * h; j++) {
                        answer[j] = Math.min(answer[j], i);
                    }
                }
            }
        }
        out.print("Case #" + testNumber + ":");
        for (int i = 0; i < k; i++) {
            int x = in.nextInt();
            out.print(" " + (answer[x] + 1));
        }
        out.println();
    }

    static int[][] getDP(char[] s) {
        int len = s.length;
        int[][] ret = new int[len + 1][2];
        for (int q = 0; q < 2; q++) {
            int[][] dp = new int[len + 1][2];
            for (int[] e : dp) {
                Arrays.fill(e, Integer.MAX_VALUE);
            }
            dp[0][q] = 0;
            for (char c : s) {
                int[][] ndp = new int[len + 1][2];
                for (int[] e : ndp) Arrays.fill(e, Integer.MAX_VALUE);
                for (int changes = 0; changes <= len; changes++) {
                    int[] dpc = dp[changes];
                    for (int last = 0; last < 2; last++) {
                        int value = dpc[last];
                        if (value == Integer.MAX_VALUE) continue;
                        ndp[changes][last] = Math.min(ndp[changes][last], value + 1);
                        int nChanges = changes + (last == c - 'A' ? 0 : 1);
                        if (nChanges <= len) {
                            ndp[nChanges][c - 'A'] = Math.min(ndp[nChanges][c - 'A'], value);
                        }
                    }
                }
                dp = ndp;
            }
            for (int i = 0; i <= len; i++) {
                ret[i][q] = Math.min(dp[i][0], dp[i][1]);
            }
        }
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < 2; j++) {
                ret[i][j] = Math.min(ret[i][j], ret[i - 1][j]);
            }
        }
        return ret;
    }
}
