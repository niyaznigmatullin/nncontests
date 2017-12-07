package coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DistanceZeroAndOne {
    public String[] construct(int[] dist0, int[] dist1) {
//        int n = a.length;
//        if (a[0] != 0 || b[1] != 0) return new String[0];
//        List<Integer>[] layer = new List[n];
//        for (int i = 0; i < n; i++) layer[i] = new ArrayList<>();
//        for (int i = 0; i < n; i++) layer[a[i]].add(i);
//        if (layer[0].size() != 1) {
//            return new String[0];
//        }
//        boolean[][] g = new boolean[n + 1][n + 1];
//        boolean[][] h = new boolean[n + 1][n + 1];
//        boolean[][] ng = new boolean[n + 1][n + 1];
//        boolean[][] nh = new boolean[n + 1][n + 1];
//        g[b[0]][0] = true;
//        h[b[0]][0] = true;
//        int[][] have = new int[n + 1][n + 1];
//        for (int[] e : have) Arrays.fill(e, -1);
//        for (int i = 0; i < n; i++) have[a[i]][b[i]] = 1;
//        List<int[]> edges = new ArrayList<>();
//        List<Integer> toProcess = new ArrayList<>();
//        for (int i = 1; i < n; ++i) {
//            for (boolean[] e : ng) Arrays.fill(e, false);
//            for (boolean[] e : nh) Arrays.fill(e, false);
//            for (int j : layer[i]) {
//                int it = 0;
//                while (it <= n && !g[b[j] + 1][it]) ++it;
//                nh[b[j]][j] = true;
//                if (it <= n) {
//                    edges.add(new int[]{j, it});
//                    g[b[j] + 1][it] = false;
//                    ng[b[j]][j] = true;
//                } else {
//                    it = 0;
//                    if (b[j] == 0) it = n + 1;
//                    while (it <= n && !h[b[j] - 1][it]) ++it;
//                    if (it <= n) {
//                        ng[b[j]][j] = true;
//                        edges.add(new int[]{j, it});
//                    } else {
//                        it = 0;
//                        while (it <= n && !h[b[j]][it]) ++it;
//                        if (it <= n) {
//                            edges.add(new int[]{j, it});
//                        } else {
//                            it = 0;
//                            while (it <= n && !h[b[j]][it]) ++it;
//                            if (it <= n) {
//                                edges.add(new int[]{j, it});
//                            } else {
//                                return new String[0];
//                            }
//                        }
//                    }
//                }
//            }
//            for (int from = 0; from <= n; from++) {
//                for (int to = 0; to <= n; to++) {
//                    if (g[from][to]) {
//                        toProcess.add(to);
//                    }
//                }
//            }
//            boolean[][] t = g;
//            g = ng;
//            ng = t;
//            t = h;
//            h = nh;
//            nh = t;
//        }
//        for (int j : toProcess) {
//            if (j == 1)
//                continue;
//            boolean found = false;
//            for (int d = -1; d <= 1; ++d) {
//                if (b[j] > 0 && have[a[j] + d][b[j] - 1] >= 0) {
//                    found = true;
//                    edges.add(new int[]{have[a[j] + d][b[j] - 1], j});
//                    break;
//                }
//            }
//            if (!found) {
//                return new String[0];
//            }
//        }
//        return getString(n, edges);
        int n = dist0.length;
        boolean[][] edges = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (Math.abs(dist0[i] - dist0[j]) <= 1 && Math.abs(dist1[j] - dist1[i]) <= 1) {
                    edges[i][j] = true;
                }
            }
        }
        if (!check(edges, dist0, dist1)) {
//            return new String[0];
            throw new AssertionError();
        }
        return getString(edges);
    }

    static boolean check(boolean[][] edges, int[] d0, int[] d1) {
        return Arrays.equals(bfs(edges, 0), d0) && Arrays.equals(bfs(edges, 1), d1);
    }

    static int[] bfs(boolean[][] edges, int start) {
        int n = edges.length;
        int[] d = new int[n];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[start] = 0;
        int[] q = new int[n];
        int head = 0;
        int tail = 0;
        q[tail++] = start;
        while (head < tail) {
            int v = q[head++];
            for (int i = 0; i < n; i++) {
                if (edges[v][i] && d[i] == Integer.MAX_VALUE) {
                    d[i] = d[v] + 1;
                    q[tail++] = i;
                }
            }
        }
        return d;
    }

//    static String[] getString(int n, List<int[]> edges) {
//        char[][] c = new char[n][n];
//        for (char[] e : c) Arrays.fill(e, 'N');
//        for (int[] t : edges) {
//            c[t[0]][t[1]] = c[t[1]][t[0]] = 'Y';
//        }
//        String[] s = new String[n];
//        for (int i = 0; i < n; i++) s[i] = new String(c[i]);
//        return s;
//    }
//

    static String[] getString(boolean[][] edges) {
        int n = edges.length;
        String[] ret = new String[n];
        for (int i = 0; i < n; i++) {
            char[] c = new char[n];
            for (int j = 0; j < n; j++) {
                c[j] = edges[i][j] ? 'Y' : 'N';
            }
            ret[i] = new String(c);
        }
        return ret;
    }
}
