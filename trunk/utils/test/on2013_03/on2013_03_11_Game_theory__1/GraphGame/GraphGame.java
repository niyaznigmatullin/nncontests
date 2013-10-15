package lib.test.on2013_03.on2013_03_11_Game_theory__1.GraphGame;



import ru.ifmo.niyaz.graphalgorithms.GraphUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GraphGame {

    static int[][] edges;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Set<Long> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            int v = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            set.add((long) v * n + u);
        }
        m = set.size();
        int[] from = new int[m];
        int[] to = new int[m];
        int cur = 0;
        for (long v : set) {
            from[cur] = (int) (v / n);
            to[cur] = (int) (v % n);
            ++cur;
        }
        edges = GraphUtils.getEdgesDirectedUnweighted(n, to, from);
        int[] deg = new int[n];
        for (int i : from) {
            deg[i]++;
        }
        int[] q = new int[n];
        int head = 0;
        int tail = 0;
        boolean[] win = new boolean[n];
        boolean[] lose = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (deg[i] == 0) {
                lose[i] = true;
                q[tail++] = i;
            }
        }
        while (head < tail) {
            int v = q[head++];
            for (int i : edges[v]) {
                if (win[i] || lose[i]) {
                    continue;
                }
                if (win[v]) {
                    --deg[i];
                    if (deg[i] == 0) {
                        q[tail++] = i;
                        lose[i] = true;
                    }
                } else {
                    q[tail++] = i;
                    win[i] = true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (win[i]) {
                out.println("Win");
            } else if (lose[i]) {
                out.println("Loss");
            } else {
                out.println("Draw");
            }
        }
    }
}
