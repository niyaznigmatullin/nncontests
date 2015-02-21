package coding;

import ru.ifmo.niyaz.graphalgorithms.GraphUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskD {

    static int[][] edges;
    static int[] from;
    static int[] to;
    static int[] weight;
    static int[] size;
    static long[] count;
    static int n;

    static void dfs(int v, int p) {
        size[v] = 1;
        for (int e : edges[v]) {
            int go = v ^ from[e] ^ to[e];
            if (go == p) continue;
            dfs(go, v);
            long down = size[go];
            long up = n - size[go];
            count[e] = (down * up * (up - 1) + up * down * (down - 1)) / 2;
            size[v] += size[go];
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        n = in.nextInt();
        from = new int[n - 1];
        to = new int[n - 1];
        weight = new int[n - 1];
        for (int i = 0; i + 1 < n; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
            weight[i] = in.nextInt();
        }
        edges = GraphUtils.getEdgesUndirectedWeighted(n, from, to);
        size = new int[n];
        count = new long[n - 1];
        dfs(0, -1);
        double exp = 0;
        for (int i = 0; i < n - 1; i++) {
            exp += weight[i] * count[i];
        }
        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            int e = in.nextInt() - 1;
            int nw = in.nextInt();
            exp -= (weight[e] - nw) * count[e];
            weight[e] = nw;
            out.println(12. * exp / n / (n - 1) / (n - 2));
        }
    }
}
