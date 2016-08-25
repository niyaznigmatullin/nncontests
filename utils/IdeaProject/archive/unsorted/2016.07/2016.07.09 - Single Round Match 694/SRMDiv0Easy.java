package coding;

import ru.ifmo.niyaz.graphalgorithms.DinicGraph;

public class SRMDiv0Easy {
    public int get(int n, int[] L, int[] R, int[] X, int[] Y) {
        DinicGraph g = new DinicGraph(n + 1 + 2);
        int src = n + 1;
        int tar = src + 1;
        int[] balance = new int[n + 1];
        for (int i = 0; i < L.length; i++) {
            ++R[i];
            g.addEdge(L[i], R[i], Y[i] - X[i]);
            balance[L[i]] -= X[i];
            balance[R[i]] += X[i];
        }
        int z = 0;
        for (int i = 0; i <= n; i++) {
            if (balance[i] < 0) {
                g.addEdge(i, tar, -balance[i]);
            } else if (balance[i] > 0) {
                z += balance[i];
                g.addEdge(src, i, balance[i]);
            }
        }
        g.addEdge(n, 0, 1 << 20);
        DinicGraph.Edge front = g.addEdge(src, 0, 0);
        DinicGraph.Edge back = g.addEdge(n, tar, 0);
        int l = -1;
        int r = 200 * 1000 + 1;
        while (l < r - 1) {
            int mid = (l + r) >> 1;
            g.clear();
            front.cap = mid;
            back.cap = mid;
            long flow = g.getMaxFlow(src, tar);
            if (flow == z + mid) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
