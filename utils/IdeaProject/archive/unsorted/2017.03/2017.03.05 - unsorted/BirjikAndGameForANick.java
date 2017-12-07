package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BirjikAndGameForANick {
    static List<Integer>[] edges;
    static int[] depth;
    static int[] parent;
    static int[] en, ex;
    static int T;
    static int[][] pp;
    static final int K = 20;

    static void dfs(int v, int pv, int d) {
        depth[v] = d;
        parent[v] = pv;
        en[v] = T++;
        for (int i = 0; i < edges[v].size(); i++) {
            int to = edges[v].get(i);
            if (to != pv) {
                dfs(to, v, d + 1);
            }
        }
        ex[v] = T;
    }

    static boolean anc(int a, int b) {
        return en[a] <= en[b] && ex[b] <= ex[a];
    }

    static int lca(int a, int b) {
        if (anc(a, b)) return a;
        for (int i = K - 1; i >= 0; i--) {
            if (!anc(pp[i][a], b)) {
                a = pp[i][a];
            }
        }
        return parent[a];
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int v = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            edges[v].add(u);
            edges[u].add(v);
        }
        depth = new int[n];
        parent = new int[n];
        en = new int[n];
        ex = new int[n];
        T = 0;
        dfs(0, -1, 0);
        pp = new int[K][n];
        for (int i = 0; i < n; i++) pp[0][i] = parent[i] < 0 ? i : parent[i];
        for (int i = 1; i < K; i++) {
            int[] prevP = pp[i - 1];
            int[] curP = pp[i];
            for (int v = 0; v < n; v++) {
                curP[v] = prevP[prevP[v]];
            }
        }
        Integer[] cache = new Integer[n];
        for (int i = 0; i < n; i++) cache[i] = i;
        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(en[o1], en[o2]);
            }
        };
        int q = in.nextInt();
        for (int qq = 0; qq < q; qq++) {
            int count = in.nextInt();
            Integer[] vso = new Integer[count + 1];
            for (int i = 0; i < count; i++) {
                vso[i] = cache[in.nextInt() - 1];
            }
            Arrays.sort(vso, 0, count, comp);
            int top = 0;
            int[] stack = new int[count + 2];
            int[] cBlack = new int[count + 2];
            cBlack[top] = 0;
            stack[top++] = 0;
            int[] ans = new int[count + 1];
            vso[count] = 0;
            for (int i : vso) {
                int lca = lca(stack[top - 1], i);
                int subtree = 0;
                while (top > 1 && depth[stack[top - 1]] > depth[lca]) {
                    cBlack[top - 1] += subtree;
                    subtree = cBlack[top - 1];
                    ans[subtree] += depth[stack[top - 1]] - Math.max(depth[lca], depth[stack[top - 2]]);
                    --top;
                }
                if (depth[stack[top - 1]] < depth[lca]) {
                    cBlack[top] = 0;
                    stack[top++] = lca;
                }
                cBlack[top - 1] += subtree;
                cBlack[top] = 1;
                stack[top++] = i;
            }
            ans[count]++;
            int all = 0;
            for (int i : ans) all += i;
            ans[0] = n - all;
            for (int i = 0; i <= count; i++) {
                if (i > 0) out.print(' ');
                out.print(ans[i]);
            }
            out.println();
        }
    }
}
