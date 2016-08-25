package coding;

import ru.ifmo.niyaz.graphalgorithms.GraphUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {

    static int[][] edges;
    static int[] sz;
    static int[] maxSz;
    static boolean[] can;
    static int half;
    static int n;

    static void dfs(int v, int pv) {
        sz[v] = 1;
        maxSz[v] = 0;
        for (int to : edges[v]) {
            if (to == pv) continue;
            dfs(to, v);
            sz[v] += sz[to];
            maxSz[v] = Math.max(maxSz[v], maxSz[to]);
            if (sz[to] <= half) {
                maxSz[v] = Math.max(maxSz[v], sz[to]);
            }
        }
    }

    static void dfs2(int v, int pv, int maxSzUp) {
        int szUp = n - sz[v];
        can[v] = true;
        if (szUp - maxSzUp > half) {
            can[v] = false;
        }
        int[] mxPref = new int[edges[v].length - (pv < 0 ? 0 : 1)];
        int[] mxSuf = new int[edges[v].length - (pv < 0 ? 0 : 1)];
        int cc = 0;
        for (int to : edges[v]) {
            if (to == pv) continue;
            if (sz[to] - maxSz[to] > half) {
                can[v] = false;
            }
            mxPref[cc] = maxSz[to];
            if (sz[to] <= half) {
                mxPref[cc] = Math.max(mxPref[cc], sz[to]);
            }
            mxSuf[cc] = mxPref[cc];
            ++cc;
        }
        for (int i = 1; i < mxPref.length; i++) mxPref[i] = Math.max(mxPref[i], mxPref[i - 1]);
        for (int i = mxSuf.length - 2; i >= 0; i--) mxSuf[i] = Math.max(mxSuf[i], mxSuf[i + 1]);
        cc = 0;
        for (int to : edges[v]) {
            if (to == pv) continue;
            int newMxSzUp = maxSzUp;
            if (szUp <= half) newMxSzUp = Math.max(newMxSzUp, szUp);
            if (cc > 0) newMxSzUp = Math.max(newMxSzUp, mxPref[cc - 1]);
            if (cc + 1 < mxSuf.length) newMxSzUp = Math.max(newMxSzUp, mxSuf[cc + 1]);
            dfs2(to, v, newMxSzUp);
            ++cc;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        n = in.nextInt();
        half = n / 2;
        int[] from = new int[n - 1];
        int[] to = new int[n - 1];
        for (int i = 0; i + 1 < n; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
        }
        edges = GraphUtils.getEdgesUndirectedUnweighted(n, from, to);
        sz = new int[n];
        maxSz = new int[n];
        dfs(0, -1);
        can = new boolean[n];
        dfs2(0, -1, 0);
        for (int i = 0; i < n; i++) {
            if (i > 0) out.print(' ');
            out.print(can[i] ? 1 : 0);
        }
        out.println();
    }
}
