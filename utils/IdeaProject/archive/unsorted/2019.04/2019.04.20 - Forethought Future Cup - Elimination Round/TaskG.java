package coding;

import ru.ifmo.niyaz.graphalgorithms.DinicGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskG {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int h = in.nextInt();
        int m = in.nextInt();
        final int INF = 100000000;
        DinicGraph g = new DinicGraph(2 + h * n + m);
        int src = h * n + m;
        int tar = src + 1;
        for (int i = 0; i < n; i++) {
//            g.addEdge(src, i * h, 0);
            g.addEdge(i * h + h - 1, tar, h * h);
            for (int j = 0; j + 1 < h; j++) {
                int sub = h - j - 1;
                g.addEdge(i * h + j, i * h + j + 1, h * h - sub * sub);
                g.addEdge(i * h + j + 1, i * h + j, INF);
            }
        }
        for (int i = 0; i < m; i++) {
            int left = in.nextInt() - 1;
            int right = in.nextInt() - 1;
            int x = in.nextInt();
            int c = in.nextInt();
            for (int e = left; e <= right; e++) {
                if (x == h) {

                } else {
                    g.addEdge(h * n + i, e * h + (h - x - 1), INF);
                }
            }
            g.addEdge(src, h * n + i, c);
        }
        long penalty = g.getMaxFlow(src, tar);
        out.println(h * h * n - penalty);
//        boolean[] z = g.getCut(src, tar);
//        for (int i = 0; i < n; i++) {
//            int mx = -1;
//            for (int j = 0; j < h; j++) {
//                if (z[i * h + j]) {
//                    mx = Math.max(mx, j);
//                }
//            }
//        }
    }
}
