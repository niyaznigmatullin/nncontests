package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] he = new int[n];
        Arrays.fill(he, -1);
        int m = in.nextInt();
        int[] from = new int[2 * m];
        int[] to = new int[2 * m];
        int[] repaired = new int[2 * m];
        for (int i = 0; i < m; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
            repaired[i] = in.nextInt();
            from[i + m] = to[i];
            to[i + m] = from[i];
            repaired[i + m] = repaired[i];
        }
        int[] next = new int[2 * m];
        for (int i = 0; i < 2 * m; i++) {
            next[i] = he[from[i]];
            he[from[i]] = i;
        }
        int[] q = new int[n];
        int head = 0;
        int tail = 1;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        int[] last = new int[n];
        Arrays.fill(last, -1);
        int[] d = new int[n];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[0] = 0;
        dp[0] = 0;
        while (head < tail) {
            int v = q[head++];
            for (int e = he[v]; e >= 0; e = next[e]) {
                int go = to[e];
                if (d[go] > d[v] + 1) {
                    q[tail++] = go;
                    d[go] = d[v] + 1;
                }
                if (d[go] == d[v] + 1 && dp[go] < dp[v] + repaired[e]) {
                    last[go] = e;
                    dp[go] = dp[v] + repaired[e];
                }
            }
        }
        boolean[] onPath = new boolean[m];
        for (int e = n - 1; e > 0; e = from[last[e]]) {
            onPath[last[e] >= m ? last[e] - m : last[e]] = true;
        }
        int[] who = new int[m];
        int ac = 0;
        for (int i = 0; i < m; i++) {
            if ((repaired[i] == 1) != onPath[i]) {
                who[ac++] = i;
            }
        }
        out.println(ac);
        for (int i = 0; i < ac; i++) {
            int e = who[i];
            out.println((from[e] + 1) + " " + (to[e] + 1) + " " + (1 - repaired[e]));
        }
    }
}
