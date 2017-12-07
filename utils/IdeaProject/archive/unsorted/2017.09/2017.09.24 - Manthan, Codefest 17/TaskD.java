package coding;

import com.sun.org.apache.bcel.internal.generic.DSUB;
import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class TaskD {
    static List<Integer>[] edges;
    static int[] depth;
    static int[] en, ex;
    static int T;
    static int[][] pp;
    static final int K = 20;

    static void dfs(int v, int pv, int d) {
        depth[v] = d;
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
        return pp[0][a];
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        int[] parent = new int[n];
        int[] type = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = in.nextInt() - 1;
            type[i] = in.nextInt();
            if (parent[i] >= 0) {
                edges[parent[i]].add(i);
            }
        }
        depth = new int[n];
        en = new int[n];
        ex = new int[n];
        T = 0;
        for (int i = 0; i < n; i++) {
            if (parent[i] < 0) {
                dfs(i, -1, 0);
            }
        }
        pp = new int[K][n];
        for (int i = 0; i < n; i++) pp[0][i] = parent[i] < 0 ? i : parent[i];
        for (int i = 1; i < K; i++) {
            int[] prevP = pp[i - 1];
            int[] curP = pp[i];
            for (int v = 0; v < n; v++) {
                curP[v] = prevP[prevP[v]];
            }
        }
        DisjointSetUnion dsuSpec = new DisjointSetUnion(n);
        DisjointSetUnion dsuPart = new DisjointSetUnion(n);
        for (int i = 0; i < n; i++) {
            if (parent[i] < 0) continue;
            if (type[i] == 0) {
                dsuSpec.union(i, parent[i]);
            } else {
                dsuPart.union(i, parent[i]);
            }
        }
        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            int t = in.nextInt();
            int v = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            int lca = lca(v, u);
            boolean part = false;
            boolean spec = false;
            if (v != u && anc(lca, v) && anc(lca, u)) {
                if (lca == v) {
                    if (dsuSpec.get(v) == dsuSpec.get(u)) {
                        spec = true;
                    } else if (dsuPart.get(v) == dsuPart.get(u)) {
                        part = true;
                    }
                } else {
                    if (dsuSpec.get(lca) == dsuSpec.get(v) && dsuPart.get(lca) == dsuPart.get(u)) {
                        part = true;
                    }
                }
                if (t == 1) {
                    out.println(spec ? "YES" : "NO");
                } else {
                    out.println(part ? "YES" : "NO");
                }
            } else {
                out.println("NO");
            }
        }
    }
}
