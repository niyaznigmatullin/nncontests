package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        char[] c = in.next().toCharArray();
        char[] d = in.next().toCharArray();
        int[][] dp1 = getLCP(d, c);
        int[][] dp2 = getLCP(d, d);
        int[] q = new int[c.length + 1];
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < d.length; i++) {
            int max = 0;
            int[] dp2i = dp2[i];
            for (int j = 0; j < d.length; j++) {
                if (i != j) {
                    max = Math.max(max, dp2i[j]);
                }
            }
            Arrays.fill(q, 0);
            int[] dp1i = dp1[i];
            for (int j = 0; j < c.length; j++) {
                q[dp1i[j]]++;
            }
            for (int j = q.length - 2; j >= 0; j--) q[j] += q[j + 1];
            for (int j = 0; j < q.length; j++) {
                if (q[j] == 1 && j > max) {
                    ans = Math.min(ans, j);
                }
            }
        }
        out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static int[][] getLCP(char[] c, char[] d) {
        int[][] ret = new int[c.length][d.length];
        for (int i = 0; i < c.length; i++) {
            int[] reti = ret[i];
            int[] reti1 = i == 0 ? null : ret[i - 1];
            for (int j = 0; j < d.length; j++) {
                if (c[i] == d[j]) {
                    reti[j] = (i > 0 && j > 0 ? reti1[j - 1] : 0) + 1;
                } else
                    reti[j] = 0;
            }
        }
        return ret;
    }
}
