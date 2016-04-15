package coding;

import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;

import java.util.ArrayList;
import java.util.List;

public class BearSpans {
    public int[] findAnyGraph(int n, int m, int k) {
        int max = 0;
        for (int p = n; p > 1; p /= 2) ++max;
        if (k > max) return new int[]{-1};
        List<int[]> edges = new ArrayList<>();
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        boolean[][] hasEdge = new boolean[n][n];
        int w = 1;
        for (int i = 0; i + 1 < k; i++) {
            int last = -1;
            int first = -1;
            for (int v = 0; v < n; v++) {
                if (dsu.get(v) == v) {
                    if (first < 0) first = v;
                    if (last >= 0) {
                        edges.add(new int[]{last, v, w++});
                        hasEdge[last][v] = true;
                        hasEdge[v][last] = true;
                        dsu.union(last, v);
                        last = -1;
                    } else {
                        last = v;
                    }
                }
            }
            if (last >= 0) {
//                if (first == last) throw new AssertionError(); // TODO
                edges.add(new int[]{first, last, w++});
                hasEdge[first][last] = hasEdge[last][first] = true;
                dsu.union(first, last);
            }
        }
        int last = -1;
        for (int v = 0; v < n; v++) {
            if (dsu.get(v) != v) continue;
            if (last >= 0) {
                edges.add(new int[]{last, v, w++});
            }
            last = v;
        }
        while (edges.size() < m) {
            for (int i = 0; edges.size() < m && i < n; i++) {
                for (int j = i + 1; edges.size() < m && j < n; j++) {
                    if (!hasEdge[i][j]) {
                        edges.add(new int[]{i, j, w++});
                    }
                }
            }
        }
        int[] ans = new int[2 * m];
        for (int[] e : edges) {
            int cw = e[2] - 1;
            ans[2 * cw] = e[0] + 1;
            ans[2 * cw + 1] = e[1] + 1;
        }
        return ans;
    }
}
