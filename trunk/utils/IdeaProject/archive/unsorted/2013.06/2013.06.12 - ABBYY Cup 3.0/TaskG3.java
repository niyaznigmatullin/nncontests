package coding;

import ru.ifmo.niyaz.DataStructures.SuffixAutomaton;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskG3 {

    public int[] readToken(FastScanner in) {
        char[] c = in.next().toCharArray();
        int[] ret = new int[c.length];
        for (int i = 0; i < c.length; i++) {
            ret[i] = c[i] - 'a';
        }
        return ret;
    }

    static int[][] as;
    static int[] low;
    static int[] high;
    static SuffixAutomaton sa;
    static int[] terminal;
    static long[] dp1;
    static long[][] dp2;

    void dfs1(int v) {
        if (dp2[0][v] >= 0) return;
        for (int j = 0; j < dp2.length; j++) {
            dp2[j][v] = (terminal[v] >> j) & 1;
        }
        for (int i = 0; i < sa.link.length; i++) {
            int to = sa.link[i][v];
            if (to >= 0) {
                dfs1(to);
                for (int j = 0; j < dp2.length; j++) {
                    dp2[j][v] += dp2[j][to];
                }
            }
        }
    }

    void dfs2(int v) {
        if (dp1[v] >= 0) return;
        boolean ok = true;
        for (int i = 0; i < dp2.length;i++) {
            if (low[i] <= dp2[i][v] && dp2[i][v] <= high[i]) {

            } else {
                ok = false;
                break;
            }
        }
        if (v == 0) ok = false;
        if (ok) dp1[v] = 1; else dp1[v] = 0;
        for (int i = 0; i < sa.link.length; i++) {
            int to = sa.link[i][v];
            if (to >= 0) {
                dfs2(to);
                dp1[v] += dp1[to];
            }
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int[] ss = readToken(in);
        int n = in.nextInt();
        as = new int[n + 1][];
        low = new int[n + 1];
        high = new int[n + 1];
        low[0] = 1;
        high[0] = Integer.MAX_VALUE / 3;
        as[0] = ss;
        for (int i = 1; i <= n; i++) {
            as[i] = readToken(in);
            low[i] = in.nextInt();
            high[i] = in.nextInt();
        }
        sa = new SuffixAutomaton(as, 26);
        terminal = new int[sa.free];
        for (int i = 0; i <= n; i++) {
            int v = 0;
            for (int j : as[i]) {
                v = sa.link[j][v];
            }
            while (v >= 0) {
                terminal[v] |= 1 << i;
                v = sa.sufLink[v];
            }
        }
        dp1 = new long[terminal.length];
        dp2 = new long[n + 1][terminal.length];
        Arrays.fill(dp1, -1);
        for (long[] e : dp2) {
            Arrays.fill(e, -1);
        }
        dfs1(0);
        dfs2(0);
        out.println(dp1[0]);
    }
}
