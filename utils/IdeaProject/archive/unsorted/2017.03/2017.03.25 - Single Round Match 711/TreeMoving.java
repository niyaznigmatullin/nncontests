package coding;

import ru.ifmo.niyaz.graphalgorithms.GraphUtils;

import java.util.Arrays;

public class TreeMoving {

    static final int MOD = 1000000007;

    static int mul(int a, int b){
        return (int) ((long) a * b % MOD);
    }

    static int add(int a, int b) {
        a += b;
        if (a >= MOD) a -= MOD;
        return a;
    }

    static int[] generateSequenceX(int n, int a, int b, int c) {
        int[] x = new int[n - 1];
        x[0] = c;
        for (int i = 1; i < n - 1; i++) {
            x[i] = add(b, mul(x[i - 1], a));
        }
        return x;
    }

    static int[][] id;
    static int[][] curSum;
    static int[] en;
    static int[] ex;
    static int[] parent;
    static int T;
    static int[] order;

    static void dfs(int[][] edges, int v, int pv) {
        parent[v] = pv;
        order[T] = v;
        en[v] = T++;
        for (int to : edges[v]) {
            if (to == pv) continue;
            dfs(edges, to, v);
        }
        ex[v] = T;
    }

    static void getDP(int n, int[][] old, int[][] next, int[][] curTree, int[] curFrom, int[] curTo, int[] prevFrom, int[] prevTo) {
        for (int i = 0; i < n - 1; i++) {
            id[curFrom[i]][curTo[i]] = id[curTo[i]][curFrom[i]] = i;
        }
        for (int[] e : curSum) {
            Arrays.fill(e, 0);
        }
        T = 0;
        dfs(curTree, 0, -1);
        if (T != n) throw new AssertionError();
        for (int i = 0; i < n - 1; i++) {
            int from = prevFrom[i];
            addIt(n, 1, old, i, from);
            int to = prevTo[i];
            addIt(n, 1, old, i, to);
            int v = from;
            while (!anc(v, to)) {
                v = parent[v];
            }
            addIt(n, MOD - 2, old, i, v);
        }
        for (int it = n - 1; it >= 0; it--) {
            int v = order[it];
            if (parent[v] >= 0) {
                int p = parent[v];
                int[] curSumP = curSum[p];
                int[] curSumV = curSum[v];
                for (int j = 0; j < n - 1; j++) {
                    curSumP[j] = add(curSumP[j], curSumV[j]);
                }
            }
        }
        for (int v = 0; v < n; v++) {
            int p = parent[v];
            if (p < 0) continue;
            int edgeId = id[v][p];
            if (edgeId < 0) throw new AssertionError();
            for (int i = 0; i < n - 1; i++) {
                next[i][edgeId] = curSum[v][i];
            }
        }
        for (int i = 0; i < n - 1; i++) {
            id[curFrom[i]][curTo[i]] = id[curTo[i]][curFrom[i]] = -1;
        }
    }

    static boolean anc(int v, int u) {
        return en[v] <= en[u] && ex[u] <= ex[v];
    }

    static void addIt(int n, int coef, int[][] old, int edgeId, int vertex) {
        int[] cSumV = curSum[vertex];
        for (int i = 0; i < n - 1; i++) {
            cSumV[i] = add(cSumV[i], mul(coef, old[i][edgeId]));
        }
    }

    public int count(int n, int[] roots, int[] a, int[] b, int[] c) {
        int treesCount = roots.length;
        int[][][] edges = new int[treesCount][][];
        int[][] froms = new int[treesCount][];
        int[][] tos = new int[treesCount][];
        for (int i = 0; i < treesCount; i++) {
            int[] x = generateSequenceX(n, a[i], b[i], c[i]);
            int[] from = new int[n - 1];
            int[] to = new int[n - 1];
            for (int j = 0; j < n - 1; j++) {
                from[j] = (roots[i] + j + 1) % n;
                to[j] = (roots[i] + (x[j] % (j + 1))) % n;
            }
            edges[i] = GraphUtils.getEdgesUndirectedUnweighted(n, from, to);
            froms[i] = from;
            tos[i] = to;
            System.out.println(Arrays.toString(from));
            System.out.println(Arrays.toString(to));
        }
        int[][] dp = new int[n - 1][n - 1];
        for (int i = 0; i < n - 1; i++) {
            dp[i][i] = 1;
        }
        int[][] next = new int[n - 1][n - 1];
        id = new int[n][n];
        for (int[] e : id) Arrays.fill(e, -1);
        curSum = new int[n][n - 1];
        en = new int[n];
        ex = new int[n];
        parent = new int[n];
        order = new int[n];
        for (int i = 1; i < treesCount; i++) {
            getDP(n, dp, next, edges[i], froms[i], tos[i], froms[i - 1], tos[i - 1]);
            int[][] t = dp;
            dp = next;
            next = t;
        }
        getDP(n, dp, next, edges[0], froms[0], tos[0], froms[treesCount - 1], tos[treesCount - 1]);
        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            ans = add(ans, next[i][i]);
        }
        return ans;
    }
}
