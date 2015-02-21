package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskE {

    static List<Integer>[] edges;

    static int[] p;
    static int T;
    static int[] en;
    static int[] ex;

    static void dfs(int v) {
        en[v] = T++;
        for (int i = 0; i < edges[v].size(); i++) {
            dfs(edges[v].get(i));
        }
        ex[v] = T;
    }

    static boolean anc(int v, int u) {
        return en[v] <= en[u] && ex[u] <= ex[v];
    }

    static int get(int x) {
        return x == p[x] ? x : (p[x] = get(p[x]));
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        p = new int[n];
        edges = new List[n];
        for (int i = 0; i < n; i++) edges[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) p[i] = i;
        int docs = 0;
        int[] pb = new int[m];
        int[] pa = new int[m];
        int[] qx = new int[m];
        int[] qd = new int[m];
        int queries = 0;
        for (int i = 0; i < m; i++) {
            int t = in.nextInt();
            if (t == 1) {
                int x = in.nextInt() - 1;
                int y = in.nextInt() - 1;
                edges[y].add(x);
                p[x] = get(y);
            } else if (t == 2) {
                pb[docs] = in.nextInt() - 1;
                pa[docs] = get(pb[docs]);
                ++docs;
            } else {
                qx[queries] = in.nextInt() - 1;
                qd[queries] = in.nextInt() - 1;
                ++queries;
            }
        }
        T = 0;
        en = new int[n];
        ex = new int[n];
        for (int i = 0; i < n; i++) {
            if (get(i) == i) {
                dfs(i);
            }
        }
        for (int i = 0; i < queries; i++) {
            if (anc(pa[qd[i]], qx[i]) && anc(qx[i], pb[qd[i]])) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
    }
}
