package coding;

import ru.ifmo.niyaz.graphalgorithms.DinicGraph;
import ru.ifmo.niyaz.graphalgorithms.MinCostMaxFlowGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskF1 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int males = in.nextInt();
        int females = in.nextInt();
        char[][] c = in.readCharacterFieldTokens(n, m);
        if (males + 1 != females && females + 1 != males) {
            out.println(-1);
            return;
        }
        List<Creature> man = new ArrayList<>();
        List<Creature> woman = new ArrayList<>();
        Creature boss = new Creature(in.nextInt() - 1, in.nextInt() - 1, in.nextInt());
        if (males + 1 == females) {
            man.add(boss);
        } else {
            woman.add(boss);
        }
        for (int i = 0; i < males; i++) {
            man.add(new Creature(in.nextInt() - 1, in.nextInt() - 1, in.nextInt()));
        }
        for (int i = 0; i < females; i++) {
            woman.add(new Creature(in.nextInt() - 1, in.nextInt() - 1, in.nextInt()));
        }
        DinicGraph g = new DinicGraph(2 + man.size() + woman.size() + n * m * 2);
        int[][] a = new int[n * m][n * m];
        for (int[] e : a) {
            Arrays.fill(e, Integer.MAX_VALUE);
        }
        for (int i = 0; i < n * m; i++) a[i][i] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] == '#') continue;
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        if (Math.abs(dx) + Math.abs(dy) != 1) continue;
                        int x = i + dx;
                        int y = j + dy;
                        if (x < 0 || y < 0 || x >= n || y >= m || c[x][y] == '#') continue;
                        a[i * m + j][x * m + y] = 1;
                    }
                }
            }
        }
        for (int k = 0; k < a.length; k++) {
            int[] ak = a[k];
            for (int i = 0; i < a.length; i++) {
                int[] ai = a[i];
                for (int j = 0; j < a.length; j++) {
                    int first = ai[k];
                    int second = ak[j];
                    if (first != Integer.MAX_VALUE && second != Integer.MAX_VALUE) {
                        ai[j] = Math.min(ai[j], first + second);
                    }
                }
            }
        }
        DinicGraph.Edge[][][] manEdges = new DinicGraph.Edge[n][m][man.size()];
        DinicGraph.Edge[][][] womanEdges = new DinicGraph.Edge[n][m][woman.size()];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] == '#') continue;
                g.addEdge(i * m + j, i * m + j + n * m, 1);
                for (int k = 0; k < man.size(); k++) {
                    Creature cr = man.get(k);
                    if (a[i * m + j][cr.x * m + cr.y] == Integer.MAX_VALUE) continue;
                    manEdges[i][j][k] = g.addEdge(2 * n * m + k, i * m + j, 1);
                }
                for (int k = 0; k < woman.size(); k++) {
                    Creature cr = woman.get(k);
                    if (a[i * m + j][cr.x * m + cr.y] == Integer.MAX_VALUE) continue;
                    womanEdges[i][j][k] = g.addEdge(i * m + j + n * m, 2 * n * m + man.size() + k, 1);
                }
            }
        }
        if (man.size() != woman.size()) throw new AssertionError();
        int src = n * m * 2 + man.size() + woman.size();
        int tar = src + 1;
        for (int k = 0; k < man.size(); k++) {
            g.addEdge(src, 2 * n * m + k, 1);
        }
        for (int k = 0; k < woman.size(); k++) {
            g.addEdge(2 * n * m + man.size() + k, tar, 1);
        }
        if (g.getMaxFlow(src, tar) != man.size()) {
            out.println(-1);
            return;
        }
        g.clear();
        long l = -1;
        long r = 1L << 40;
        long lastFlow = 0;
        while (l < r - 1) {
            long mid = l + r >> 1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (c[i][j] == '#') continue;
                    DinicGraph.Edge[] cur = manEdges[i][j];
                    for (int k = 0; k < man.size(); k++) {
                        Creature cr = man.get(k);
                        int val = a[i * m + j][cr.x * m + cr.y];
                        if (val == Integer.MAX_VALUE) {
                            continue;
                        }
                        if ((long) val * cr.speed <= mid) {
                            cur[k].cap = 1;
                        } else {
                            cur[k].cap = 0;
                        }
                    }
                    cur = womanEdges[i][j];
                    for (int k = 0; k < woman.size(); k++) {
                        Creature cr = woman.get(k);
                        int val = a[i * m + j][cr.x * m + cr.y];
                        if (val == Integer.MAX_VALUE) {
                            continue;
                        }
                        if ((long) val * cr.speed <= mid) {
                            cur[k].cap = 1;
                        } else {
                            cur[k].cap = 0;
                        }
                    }
                }
            }
            long curFlow = g.getMaxFlow(src, tar) + lastFlow;
            if (curFlow == man.size()) {
                r = mid;
                g.clear();
                lastFlow = 0;
            } else {
                l = mid;
                lastFlow = curFlow;
            }
        }
        out.println(r);
    }

    static class Creature {
        int x;
        int y;
        int speed;

        Creature(int x, int y, int speed) {
            this.x = x;
            this.y = y;
            this.speed = speed;
        }
    }
}
