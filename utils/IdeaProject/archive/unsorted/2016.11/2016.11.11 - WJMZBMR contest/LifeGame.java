package coding;

import ru.ifmo.niyaz.graphalgorithms.DinicGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class LifeGame {

    static int n, m, q;

    static final int M = 6;

    static int getVertex(int r, int c, int k1, int k2, int type) {
        int ans = r * m + c;
        ans += n * m * (k1 * M + k2);
        return ans + type * n * m * M * M + q;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        n = in.nextInt();
        m = in.nextInt();
        q = in.nextInt();
        long ans = 0;
        int[][] b = in.readInt2DArray(n, m);
        int[][] w = in.readInt2DArray(n, m);
        for (int[] e : b) for (int f : e) ans += f;
        for (int[] e : w) for (int f : e) ans += f;
        int src = n * m * M * M * 2 + q;
        int tar = src + 1;
        DinicGraph g = new DinicGraph(tar + 1);
        final int INF = 1 << 30;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k1 = 0; (1 << k1) + i <= n; k1++) {
                    for (int k2 = 0; (1 << k2) + j <= m; k2++) {
                        if (k1 > 0) {
                            g.addEdge(getVertex(i, j, k1, k2, 0), getVertex(i, j, k1 - 1, k2, 0), INF);
                            g.addEdge(getVertex(i, j, k1, k2, 0), getVertex(i + (1 << k1 - 1), j, k1 - 1, k2, 0), INF);
                            g.addEdge(getVertex(i, j, k1 - 1, k2, 1), getVertex(i, j, k1, k2, 1), INF);
                            g.addEdge(getVertex(i + (1 << k1 - 1), j, k1 - 1, k2, 1), getVertex(i, j, k1, k2, 1), INF);
                        } else if (k2 > 0){
                            g.addEdge(getVertex(i, j, k1, k2, 0), getVertex(i, j, k1, k2 - 1, 0), INF);
                            g.addEdge(getVertex(i, j, k1, k2, 0), getVertex(i, j + (1 << k2 - 1), k1, k2 - 1, 0), INF);
                            g.addEdge(getVertex(i, j, k1, k2 - 1, 1), getVertex(i, j, k1, k2, 1), INF);
                            g.addEdge(getVertex(i, j + (1 << k2 - 1), k1, k2 - 1, 1), getVertex(i, j, k1, k2, 1), INF);
                        } else {
                            g.addEdge(getVertex(i, j, 0, 0, 0), getVertex(i, j, 0, 0, 1), INF);
                            g.addEdge(getVertex(i, j, 0, 0, 0), tar, b[i][j]);
                            g.addEdge(src, getVertex(i, j, 0, 0, 0), w[i][j]);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < q; i++) {
            int r1 = in.nextInt() - 1;
            int c1 = in.nextInt() - 1;
            int r2 = in.nextInt() - 1;
            int c2 = in.nextInt() - 1;
            int color = in.nextInt();
            int money = in.nextInt();
            ans += money;
            if (color == 0) {
                g.addEdge(src, i, money);
            } else {
                g.addEdge(i, tar, money);
            }
            int k1 = 0;
            while (1 << (k1 + 1) <= r2 - r1 + 1) {
                ++k1;
            }
            int k2 = 0;
            while (1 << (k2 + 1) <= c2 - c1 + 1) {
                k2++;
            }
            r2 = r2 - (1 << k1) + 1;
            c2 = c2 - (1 << k2) + 1;
            if (color == 0) {
                g.addEdge(i, getVertex(r1, c1, k1, k2, 0), INF);
                g.addEdge(i, getVertex(r1, c2, k1, k2, 0), INF);
                g.addEdge(i, getVertex(r2, c1, k1, k2, 0), INF);
                g.addEdge(i, getVertex(r2, c2, k1, k2, 0), INF);
            } else {
                g.addEdge(getVertex(r1, c1, k1, k2, 1), i, INF);
                g.addEdge(getVertex(r1, c2, k1, k2, 1), i, INF);
                g.addEdge(getVertex(r2, c1, k1, k2, 1), i, INF);
                g.addEdge(getVertex(r2, c2, k1, k2, 1), i, INF);
            }
        }
        out.println(ans - g.getMaxFlow(src, tar));
    }
}
