package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Restore {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int leaves = in.nextInt();
        int[][] a = in.readInt2DArray(leaves, leaves);
        int[] id = new int[leaves];
        for (int i = 0; i < leaves; i++) id[i] = i;
        List<Edge> edges = new ArrayList<>();
        int cur = leaves;
        int count = leaves;
        while (true) {
            boolean changed = false;
            if (count == 1) {
                break;
            }
            if (count == 2) {
                int id1 = -1;
                int id2 = -1;
                for (int i = 0; i < leaves; i++) {
                    if (id[i] < 0) continue;
                    if (id1 < 0) id1 = i;
                    else id2 = i;
                }
                if (a[id1][id2] == 1) {
                    edges.add(new Edge(id[id1], id[id2]));
                    break;
                }
            }
            for (int i = 0; i < leaves; i++) {
                if (id[i] < 0) continue;
                boolean has2 = false;
                boolean[] w = new boolean[leaves];
                w[i] = true;
                for (int j = i + 1; j < leaves; j++) {
                    if (id[j] < 0) continue;
                    if (a[i][j] == 2) {
                        has2 = true;
                        w[j] = true;
                    }
                }
                if (!has2) continue;
                boolean bad = false;
                loop:
                for (int j = 0; j < leaves; j++) {
                    if (id[j] < 0 || w[j]) continue;
                    for (int k = 0; k < leaves; k++) {
                        if (j == k || id[k] < 0 || w[k]) continue;
                        if (a[i][k] + a[i][j] - 4 < a[j][k]) {
                            bad = true;
                            break loop;
                        }
                    }
                }
                if (bad) continue;
                for (int j = i + 1; j < leaves; j++) {
                    if (!w[j]) continue;
                    for (int k = 0; k < leaves; k++) {
                        if (j == k || i == k || id[k] < 0) continue;
                        if (a[i][k] != a[j][k]) {
                            out.println(-1);
                            return;
                        }
                    }
                }
                for (int j = i + 1; j < leaves; j++) {
                    if (w[j]) {
                        --count;
                        edges.add(new Edge(id[j], cur));
                        id[j] = -1;
                    }
                }
                edges.add(new Edge(id[i], cur));
                id[i] = cur++;
                for (int j = 0; j < leaves; j++) {
                    if (i == j || id[j] < 0) continue;
                    a[i][j]--;
                    a[j][i]--;
                    if (a[i][j] <= 1) {
                        out.println(-1);
                        return;
                    }
                }
                changed = true;
            }
            {
                int bestMaximal = Integer.MIN_VALUE;
                int id0 = -1;
                for (int i = 0; i < leaves; i++) {
                    if (id[i] < 0) {
                        continue;
                    }
                    int maximal = Integer.MIN_VALUE;
                    for (int j = 0; j < leaves; j++) {
                        if (id[j] < 0 || i == j) continue;
                        if (a[i][j] == 2) {
                            maximal = 0;
                            break;
                        }
                        maximal = Math.max(maximal, a[i][j]);
                    }
                    if (maximal > bestMaximal) {
                        bestMaximal = maximal;
                        id0 = i;
                    }
                }
                if (bestMaximal > 2) {
                    int i = id0;
                    edges.add(new Edge(id[i], cur));
                    id[i] = cur++;
                    for (int j = 0; j < leaves; j++) {
                        if (i != j && id[j] >= 0) {
                            a[i][j]--;
                            a[j][i]--;
                            if (a[i][j] <= 1) {
                                out.println(-1);
                                return;
                            }
                        }
                    }
                    changed = true;
                }
            }
            if (changed) continue;
            out.println(-1);
            return;
        }
        out.println(edges.size() + 1);
        for (Edge e : edges) {
            out.println(e.from + 1 + " " + (e.to + 1));
        }
    }

    static class Edge {
        int from;
        int to;

        Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
}
