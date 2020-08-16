package coding;

import ru.ifmo.niyaz.graphalgorithms.DinicGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Color1 {
    int n;
    int[][] color, edges, p;
    int[] isLeft, deg;
    boolean[] used;

    boolean dfs(int v, int back, int front) {
        if (used[v]) return false;
        used[v] = true;
        for (int i = 0; i < n; i++) {
            if (edges[v][i] != 1 || used[i]) continue;
            if (color[v][i] != front) continue;
            int u = p[back][i];
            if (u < 0 || dfs(u, back, front)) {
                if (u >= 0) {
                    color[i][u] = color[u][i] = front;
                    if (front >= 0) {
                        p[front][u] = i;
                        p[front][i] = u;
                    }
                } else {
                    if (front < 0) deg[i]--; else {
                        p[front][i] = -1;
                    }
                }
                if (front >= 0) {
                    p[front][v] = -1;
                }
                color[v][i] = back;
                color[i][v] = back;
                p[back][i] = v;
                p[back][v] = i;
                return true;
            }
        }
        return false;
    }

    void bipartiteColoring(int v, int c) {
        isLeft[v] = c;
        for (int i = 0; i < n; i++) {
            if (edges[v][i] != 1 || isLeft[i] == (c ^ 1)) continue;
            if (isLeft[i] >= 0) throw new AssertionError();
            bipartiteColoring(i, c ^ 1);
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        n = in.nextInt();
        if (n < 1 || n > 200) throw new AssertionError();
        int m = in.nextInt();
        color = new int[n][n];
        for (int[] e : color) Arrays.fill(e, -1);
        edges = new int[n][n];
        int[] from = new int[m];
        int[] to = new int[m];
        deg = new int[n];
        for (int i = 0; i < m; i++) {
            int v = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            from[i] = v;
            to[i] = u;
            if (edges[v][u] == 1) throw new AssertionError();
            edges[v][u] = 1;
            edges[u][v] = 1;
            deg[v]++;
            deg[u]++;
        }
        int maxDeg = 0;
        for (int i = 0; i < n; i++) {
            maxDeg = Math.max(maxDeg, deg[i]);
        }
        isLeft = new int[n];
        Arrays.fill(isLeft, -1);
        for (int i = 0; i < n; i++) {
            if (isLeft[i] < 0) {
                bipartiteColoring(i, 1);
            }
        }
        int left = 0;
        int right = 0;
        for (int i = 0; i < n; i++) if (isLeft[i] == 1) left++; else right++;
        int nn = n + Math.abs(left - right);
        deg = Arrays.copyOf(deg, nn);
        isLeft = Arrays.copyOf(isLeft, nn);
        for (int i = n; i < nn; i++) {
            isLeft[i] = left < right ? 1 : 0;
        }
        n = nn;
        edges = new int[n][n];
        color = new int[n][n];
        for (int[] e : color) Arrays.fill(e, -1);
        for (int i = 0; i < m; i++) edges[from[i]][to[i]] = edges[to[i]][from[i]] = 1;
        DinicGraph g = new DinicGraph(n + 2);
        int src = n;
        int tar = n + 1;
        DinicGraph.Edge[][] flowEdges = new DinicGraph.Edge[n][n];
        List<DinicGraph.Edge> added = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (isLeft[i] == 1) {
                g.addEdge(src, i, 1);
                for (int j = 0; j < n; j++) {
                    if (isLeft[j] == 1) continue;
                    if (edges[i][j] == 1) {
                        flowEdges[i][j] = g.addEdge(i, j, 1);
                    }
                    while (deg[i] < maxDeg && deg[j] < maxDeg) {
                        added.add(g.addEdge(i, j, 1));
                        ++deg[i];
                        ++deg[j];
                    }
                }
            } else {
                g.addEdge(i, tar, 1);
            }
        }
        int[] f = new int[maxDeg];
        p = new int[n][n];
        for (int[] e : p) Arrays.fill(e, -1);
        for (int iteration = 0; iteration < maxDeg; iteration++) {
            long got = g.getMaxFlow(src, tar);
            if (got != n / 2) throw new AssertionError();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (flowEdges[i][j] != null && flowEdges[i][j].flow > 0) {
                        flowEdges[i][j].cap = 0;
                        flowEdges[i][j].flow = 0;
                        flowEdges[i][j].rev.cap = 0;
                        flowEdges[i][j].rev.flow = 0;
                        color[i][j] = color[j][i] = iteration;
                        f[iteration]++;
                        p[iteration][i] = j;
                        p[iteration][j] = i;
                    }
                }
            }
            for (DinicGraph.Edge e : added) {
                if (e.flow > 0) {
                    e.cap = e.flow = e.rev.cap = e.rev.flow = 0;
                }
            }
            g.clear();
        }
        used = new boolean[n];
//        System.out.println(Arrays.toString(deg));
//        System.out.println(Arrays.deepToString(color));
//        System.out.println(Arrays.deepToString(edges));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (edges[i][j] == 1 && color[i][j] < 0) {
                    throw new AssertionError();
                }
            }
        }
        while (true) {
            boolean changed = false;
            for (int c1 = 0; c1 < maxDeg; c1++) {
                for (int c2 = 0; c2 < maxDeg; c2++) {
                    if (f[c1] - f[c2] > 1) {
                        for (int start = 0; start < n; start++) {
                            if (p[c2][start] >= 0) continue;
                            Arrays.fill(used, false);
                            if (dfs(start, c2, c1)) {
                                changed = true;
                                break;
                            }
                        }
                        assert(changed);
                        f[c1]--;
                        f[c2]++;
                        c1 = maxDeg;
                        break;
                    }
                }
            }
            if (!changed) break;
        }
        out.println(maxDeg);
        for (int i = 0; i < m; i++) {
            out.println(color[from[i]][to[i]] + 1);
        }

    }
}
