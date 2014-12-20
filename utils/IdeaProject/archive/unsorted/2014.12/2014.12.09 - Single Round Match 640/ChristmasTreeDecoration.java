package coding;

import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;

public class ChristmasTreeDecoration {
    public int solve(int[] col, int[] x, int[] y) {
        int m = x.length;
        for (int i = 0; i < m; i++) {
            x[i]--;
            y[i]--;
        }
        int n = col.length;
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        for (int i = 0; i < m; i++) {
            if (col[x[i]] != col[y[i]]) {
                dsu.union(x[i], y[i]);
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            if (dsu.get(x[i]) != dsu.get(y[i])) {
                ans++;
                dsu.union(x[i], y[i]);
            }
        }
        return ans;
    }
}
