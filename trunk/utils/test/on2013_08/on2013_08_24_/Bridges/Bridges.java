package lib.test.on2013_08.on2013_08_24_.Bridges;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Bridges {

    int[] ss, ff, he, ne, up, de;
    boolean[] was;
    boolean[] isBridge;
    int m;


    void dfs(int v, int p, int d) {
        up[v] = de[v] = d;
        was[v] = true;
        for (int e = he[v]; e >= 0; e = ne[e]) {
            int to = ff[e];
            if (p >= 0 && (p + m == e || e + m == p)) {
                continue;
            }
            if (was[to]) {
                up[v] = Math.min(up[v], de[to]);
            } else {
                dfs(to, e, d + 1);
                if (up[to] > de[v]) {
                    isBridge[e >= m ? e - m : e] = true;
                }
                up[v] = Math.min(up[v], up[to]);
            }
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        m = in.nextInt();
        he = new int[n];
        Arrays.fill(he, -1);
        ne = new int[2 * m];
        ss = new int[2 * m];
        ff = new int[2 * m];
        for (int i = 0; i < m; i++) {
            ss[i] = in.nextInt() - 1;
            ff[i] = in.nextInt() - 1;
            ff[i + m] = ss[i];
            ss[i + m] = ff[i];
        }
        for (int i = 0; i < m + m; i++) {
            ne[i] = he[ss[i]];
            he[ss[i]] = i;
        }
        up = new int[n];
        de = new int[n];
        was = new boolean[n];
        isBridge = new boolean[m];
        dfs(0, -1, 0);
        int count = 0;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            if (isBridge[i]) {
                ans[count++] = i;
            }
        }
        out.println(count);
        for (int i = 0; i < count; i++) {
            if (i > 0) out.print(' ');
            out.print(ans[i] + 1);
        }
        out.println();
    }
}
