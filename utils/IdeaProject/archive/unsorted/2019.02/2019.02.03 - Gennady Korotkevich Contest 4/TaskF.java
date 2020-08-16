package coding;

import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Set;

public class TaskF {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        int[] edgeU = new int[m];
        int[] edgeV = new int[m];
        int[] deg = new int[n];
        for (int i = 0; i < m; i++) {
            edgeU[i] = in.nextInt() - 1;
            edgeV[i] = in.nextInt() - 1;
            deg[edgeU[i]]++;
            deg[edgeV[i]]++;
        }
        int[] curDeg = new int[n];
        long ans = 0;
        for (int i = 0; i < m; i++) {
            long weight = i + 1;
            int v = edgeV[i];
            int u = edgeU[i];
            int all = curDeg[v] > 0 ? 1 : 0;
            all += curDeg[u] > 0 ? 1 : 0;
            if (dsu.get(v) == dsu.get(u)) {
                all = 1;
            }
            all += deg[v] + deg[u] - 2 - curDeg[v] - curDeg[u];
            ans += (all - 1) * weight;
            curDeg[v]++;
            curDeg[u]++;
            dsu.union(v, u);
        }
        out.println(ans);
    }
}
