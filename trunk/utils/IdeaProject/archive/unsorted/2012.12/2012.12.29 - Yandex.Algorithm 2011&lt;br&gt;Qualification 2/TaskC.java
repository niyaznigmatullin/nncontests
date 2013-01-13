package coding;

import ru.ifmo.niyaz.graphalgorithms.GraphUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskC {

    static int[][] edges;
    static int[] from;
    static int[] to;
    static int[] w;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        final int[] a = in.readIntArray(n);
        from = new int[n - 1];
        to = new int[n - 1];
        w = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
            w[i] = in.nextInt();
        }
        edges = GraphUtils.getEdgesUndirectedWeighted(n, from, to);
        parent = new int[n];
        wToParent = new int[n];
        dfs(0, -1);
        int[] where = new int[n];
        for (int i = 0; i < n; i++) {
            where[i] = i;
        }
        int[] answer = new int[n];
        Integer[] z = new Integer[n];
        for (int i = 0; i < n; i++) {
            z[i] = i;
        }
        Arrays.sort(z, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return a[o1] - a[o2];
            }
        });
        int[] cnt = new int[n];
        for (int day = 0; day < n; day++) {
            Arrays.fill(cnt, 0);
            for (int i : z) {
                int q = where[i];
                if (q == 0) {
                    continue;
                }
                if (cnt[q] == wToParent[q]) {
                    continue;
                }
                ++cnt[q];
                where[i] = parent[q];
                if (where[i] == 0) {
                    answer[i] = day + 1;
                }
            }
        }
        out.printArray(answer);
    }

    static int[] parent;
    static int[] wToParent;

    static void dfs(int v, int p) {
        parent[v] = p;
        for (int i : edges[v]) {
            int go = from[i] ^ to[i] ^ v;
            if (go == p) {
                continue;
            }
            wToParent[go] = w[i];
            dfs(go, v);
        }
    }
}
