package lib.test.on2013_06.on2013_06_12_ABBYY_Cup_3_0.C2___Tidying_Up__70_points_;



import com.sun.xml.internal.rngom.digested.DXMLPrinter;
import ru.ifmo.niyaz.graphalgorithms.HungarianAlgorithm;
import ru.ifmo.niyaz.graphalgorithms.KuhnMatchingGraph;
import ru.ifmo.niyaz.graphalgorithms.MinCostMaxFlowGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Random;

public class TaskC2 {

    final int[] DX = {1, 0, -1, 0};
    final int[] DY = {0, 1, 0, -1};

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
//        int n = 40;
//        int m = 40;
        int[][] a = new int[n][];
        Random rand = new Random();
        int[] z = new int[n * m / 2];
        Arrays.fill(z, 2);
        for (int i = 0; i < n; i++) {
//            a[i] = new int[m];
//            for (int j = 0; j < m; j++) {
//                int x;
//                do {
//                    x = rand.nextInt(n * m / 2);
//                } while (z[x] == 0);
//                a[i][j] = x + 1;
//            }
            a[i] = in.readIntArray(m);
        }
        boolean[][] has = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int dir = 0; dir < 4; dir++) {
                    int x = i + DX[dir];
                    int y = j + DY[dir];
                    if (x < 0 || y < 0 || x >= n || y >= m || a[x][y] != a[i][j]) continue;
                    has[i][j] = true;
                }
            }
        }
//        MinCostMaxFlowGraph g = new MinCostMaxFlowGraph(n * m + 2);
//        int source = n * m;
//        int target = n * m + 1;
        int ans = 0;
        int[] q = new int[2 * n * m];
        int[][] d = new int[n][m];
        int[][] id = new int[n][m];
        int id1 = 0;
        int id2 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (has[i][j]) {
                    continue;
                }
                if ((i + j & 1) == 1) {
                    id[i][j] = id2++;
                } else {
                    id[i][j] = id1++;
                }
            }
        }
        int[][] zz = new int[id1][id2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (has[i][j]) {
                    continue;
                }
                if ((i + j & 1) == 1) {
//                    g.addEdge(i * m + j, target, 0, 1, 0);
                    continue;
                }
//                g.addEdge(source, i * m + j, 0, 1, 0);
                ans++;
                for (int[] e : d) {
                    Arrays.fill(e, Integer.MAX_VALUE);
                }
                d[i][j] = 0;
                int head = 0;
                int tail = 1;
                q[head] = i * m + j;
                while (head < tail) {
                    int v = q[head++];
                    int cx = v / m;
                    int cy = v % m;
                    for (int dir = 0; dir < 4; dir++) {
                        int x = cx + DX[dir];
                        int y = cy + DY[dir];
                        if (x < 0 || y < 0 || x >= n || y >= m) continue;
                        int cost = 1;
                        if (a[x][y] == a[cx][cy]) cost = 0;
                        if (d[x][y] > d[cx][cy] + cost) {
                            q[tail++] = x * m + y;
                            d[x][y] = d[cx][cy] + cost;
                        }
                    }
                }
                for (int x = 0; x < n; x++) {
                    for (int y = 1 - (x & 1); y < m; y += 2) {
                        if ((x + y & 1) == 0) continue;
                        if (has[x][y]) continue;
                        zz[id[i][j]][id[x][y]] = d[x][y] - 1;
//                        g.addEdge(i * m + j, x * m + y, 0, 1, d[x][y] - 1);
                    }
                }
            }
        }
//        System.out.println("here");
//        ans += g.getMinCostMaxFlow(source, target)[1];
        int[] pp = HungarianAlgorithm.getMatching(zz);
        for (int i = 0; i < pp.length; i++) {
            ans += zz[i][pp[i]];
        }
        out.println(ans);
    }

    static boolean eq(int a, int b, int c, int d) {
        return a == c && b == d;
    }
}
