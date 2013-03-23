package coding;

import ru.ifmo.niyaz.graphalgorithms.GraphUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class SnakesArrows {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int q = in.nextInt();
        int[] start = new int[k];
        for (int i = 0; i < k; i++) {
            start[i] = in.nextInt() - 1;
        }
        int[] id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            id[x] = y;
        }
        int[] from = new int[n * q];
        int[] to = new int[n * q];
        int cntEdges = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= q; j++) {
                int go = i + j;
                if (go >= n) {
                    continue;
                }
                go = id[go];
                from[cntEdges] = i;
                to[cntEdges] = go;
                ++cntEdges;
            }
        }
        from = Arrays.copyOf(from, cntEdges);
        to = Arrays.copyOf(to, cntEdges);
        int[][] ed = GraphUtils.getEdgesDirectedUnweighted(n, from, to);
        int[][] rev = GraphUtils.getEdgesDirectedUnweighted(n, to, from);
        int[] g = new int[n];
        Arrays.fill(g, -1);
        int[] set = new int[n];
        int cur = 0;
        boolean[][] has = new boolean[n][n];
        all:
        while (true) {
            for (int i = 0; i < n; i++) {
                if (g[i] >= 0) {
                    continue;
                }
                ++cur;
                for (int v : ed[i]) {
                    if (g[v] >= 0) {
                        set[g[v]] = cur;
                    }
                }
                int best;
                for (int j = 0; ; j++) {
                    if (set[j] != cur) {
                        best = j;
                        break;
                    }
                }
                boolean ok = true;
                for (int v : ed[i]) {
                    if (g[v] < 0) {
                        if (!has[v][best]) {
                            ok = false;
                            break;
                        }
                    }
                }
                if (ok) {
                    g[i] = best;
                    for (int v : rev[i]) {
                        has[v][best] = true;
                    }
                    continue all;
                }
            }
            break;
        }
        int cnt = 0;
        int one = -1;
        int xor = 0;
        for (int i = 0; i < k; i++) {
            if (g[start[i]] >= 0) {
                xor ^= g[start[i]];
            } else {
                ++cnt;
                one = i;
            }
        }
        if (cnt > 1) {
            out.println("Draw");
        } else if (cnt == 1) {
            if (xor < n && has[start[one]][xor]) {
                out.println("Arkadii");
                for (int j = 1; j <= q; j++) {
                    if (start[one] + j >= n) {
                        continue;
                    }
                    if (g[id[start[one] + j]] == xor) {
                        out.println((1 + one) + " " + j);
                        break;
                    }
                }
            } else {
                out.println("Draw");
            }
        } else {
            if (xor > 0) {
                out.println("Arkadii");
                all: for (int i = 0; i < k; i++) {
                    int need = xor ^ g[start[i]];
                    if (need < g[start[i]]) {
                        for (int j = 1; j <= q; j++) {
                            if (start[i] + j >= n) {
                                continue;
                            }
                            if (g[id[start[i] + j]] == need) {
                                out.println((i + 1) + " " + j);
                                break all;
                            }
                        }
                    }
                }
            } else {
                out.println("Boris");
            }
        }
    }
}
