package coding;

import ru.ifmo.niyaz.DataStructures.UsedInteger;
import ru.ifmo.niyaz.graphalgorithms.GraphUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] from = new int[n - 1];
        int[] to = new int[n - 1];
        final int[] w = new int[n - 1];
        for (int i = 0; i + 1 < n; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
            w[i] = in.nextInt();
        }
        Integer[] id = new Integer[n - 1];
        for (int i = 0; i + 1 < n; i++) {
            id[i] = i;
        }
        Arrays.sort(id, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return w[o1] - w[o2];
            }
        });
        UsedInteger number = new UsedInteger(n);
        DSU dsu = new DSU(n);
        int[] index = new int[n];
        int[] index2 = new int[n];
        long[] answer = new long[n - 1];
        for (int i = 0; i + 1 < n;) {
            int j = i;
            while (j + 1 < n && w[id[i]] == w[id[j]]) {
                ++j;
            }
            int current = 0;
            number.clear();
            for (int k = i; k < j; k++) {
                int v = dsu.get(from[id[k]]);
                int u = dsu.get(to[id[k]]);
                if (!number.get(v)) {
                    index2[current] = v;
                    index[v] = current++;
                    number.set(v);
                }
                if (!number.get(u)) {
                    index2[current] = u;
                    index[u] = current++;
                    number.set(u);
                }
            }
            int[] curfrom = new int[j - i];
            int[] curto = new int[j - i];
            TaskD.from = curfrom;
            TaskD.to = curto;
            for (int k = i; k < j; k++) {
                curfrom[k - i] = index[dsu.get(from[id[k]])];
                curto[k - i] = index[dsu.get(to[id[k]])];
            }
            edges = GraphUtils.getEdgesUndirectedWeighted(current, curfrom, curto);
            count = new int[current];
            for (int k = 0; k < current; k++) {
                count[k] = dsu.cnt[dsu.get(index2[k])];
            }
            for (int k = i; k < j; k++) {
                dsu.union(from[id[k]], to[id[k]]);
            }
            add = new long[current];
            was = new boolean[current];
            for (int k = 0; k < current; k++) {
                if (was[k]) {
                    continue;
                }
                dfs(k, -1, dsu.cnt[dsu.get(index2[k])]);
            }
            for (int k = i; k < j; k++) {
                answer[id[k]] = add[k - i];
            }
            i = j;
        }
        long maximal = 0;
        for (long e : answer) {
            maximal = StrictMath.max(maximal, e);
        }
        List<Integer> ansID = new ArrayList<Integer>();
        for (int i = 0; i + 1 < n; i++) {
            if (maximal == answer[i]) {
                ansID.add(i + 1);
            }
        }
        out.println(maximal + " " + ansID.size());
        for (int i = 0; i < ansID.size(); i++) {
            if (i > 0) {
                out.print(' ');
            }
            out.print(ansID.get(i));
        }
        out.println();
    }

    static class DSU {
        int[] p;
        int[] cnt;

        DSU(int n) {
            p = new int[n];
            cnt = new int[n];
            for (int i = 0; i < n; i++) {
                p[i] = i;
                cnt[i] = 1;
            }
        }

        int get(int x) {
            return x == p[x] ? x : (p[x] = get(p[x]));
        }

        void union(int a, int b) {
            a = get(a);
            b = get(b);
            p[a] = b;
            cnt[b] += cnt[a];
        }
    }

    static int dfs(int v, int p, int all) {
        was[v] = true;
        int ret = count[v];
        for (int i : edges[v]) {
            int go = from[i] ^ to[i] ^ v;
            if (go == p) {
                continue;
            }
            int got = dfs(go, v, all);
            ret += got;
            add[i] = (long) 2 * got * (all - got);
        }
        return ret;
    }

    static long[] add;

    static boolean[] was;

    static int[] count;
    static int[][] edges;
    static int[] from;
    static int[] to;
}
