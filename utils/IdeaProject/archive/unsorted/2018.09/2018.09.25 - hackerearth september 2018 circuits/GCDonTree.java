package coding;

import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class GCDonTree {

    static final int K = 20;
    static List<Integer>[] edges;
    static int T;
    static int[] en, ex, parent;
    static int[][] pp;

    static void dfs(int v, int pv) {
        en[v] = T++;
        pp[0][v] = pv < 0 ? v : pv;
        parent[v] = pv;
        for (int i = 1; i < K; i++) {
            pp[i][v] = pp[i - 1][pp[i - 1][v]];
        }
        for (int i = 0; i < edges[v].size(); i++) {
            int to = edges[v].get(i);
            if (to == pv) continue;
            dfs(to, v);
        }
        ex[v] = T;
    }

    static boolean anc(int v, int u) {
        return en[v] <= en[u] && ex[u] <= ex[v];
    }

    static int lca(int v, int u) {
        if (anc(v, u)) return v;
        for (int i = K - 1; i >= 0; i--) {
            if (!anc(pp[i][v], u)) v = pp[i][v];
        }
        return pp[0][v];
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i + 1 < n; i++) {
            int v = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            edges[v].add(u);
            edges[u].add(v);
        }
        T = 0;
        en = new int[n];
        ex = new int[n];
        parent = new int[n];
        pp = new int[K][n];
        dfs(0, -1);
        List<Integer>[] vs = new List[MAXN];
        for (int i = 0; i < n; i++) {
            if (vs[a[i]] == null) vs[a[i]] = new ArrayList<>();
            vs[a[i]].add(i);
        }
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        Integer[] list = new Integer[2 * n];
        Comparator<Integer> compareByEnter = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(en[o1], en[o2]);
            }
        };
        int[] answer = new int[n];
        int[] stack = new int[n];
        for (int d = MAXN - 1; d >= 1; d--) {
            int cn = 0;
            int stSize = 0;
            for (int j = d; j < MAXN; j += d) {
                if (vs[j] == null) continue;
                for (int i = 0; i < vs[j].size(); i++) {
                    list[cn++] = vs[j].get(i);
                }
            }
            if (cn == 0) continue;
            Arrays.sort(list, 0, cn, compareByEnter);
            {
                int ncn = cn;
                for (int i = 0; i + 1 < cn; i++) {
                    list[ncn++] = lca(list[i], list[i + 1]);
                }
                cn = ncn;
            }
            Arrays.sort(list, 0, cn, compareByEnter);
            int root = list[0];
            stack[stSize++] = root;
            int rootChildren = 0;
            for (int i = 1; i < cn; i++) {
                if (list[i].equals(list[i - 1])) continue;
                while (stSize > 0 && !anc(stack[stSize - 1], list[i])) {
                    stSize--;
                }
                if (stSize == 0) throw new AssertionError();
                if (stSize == 1) rootChildren++;
                stack[stSize++] = list[i];
                int v = parent[list[i]];
                if (v >= 0) {
                    while (en[dsu.get(v)] > en[root]) {
                        int u = dsu.get(v);
                        if (answer[u] != 0) break;
                        answer[u] = d;
                        if (parent[u] >= 0) {
                            dsu.union(u, parent[u]);
                        }
                    }
                }
            }
            if (rootChildren > 1) {
                if (answer[root] == 0) {
                    answer[root] = d;
                    if (parent[root] >= 0) {
                        dsu.union(root, parent[root]);
                    }
                }
            }
        }
        out.printArray(answer);
    }

    static final int MAXN = 100001;
//    static List<Integer>[] divisors = new List[MAXN];
//
//    static {
//        for (int i = 0; i < MAXN; i++) divisors[i] = new ArrayList<>();
//        for (int i = 1; i < MAXN; i++) {
//            for (int j = i; j < MAXN; j += i) divisors[j].add(i);
//        }
//    }
}
