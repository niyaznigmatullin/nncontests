package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class TreeFun {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] deg = new int[n];
        edges = new int[n][];
        int[] from = new int[n - 1];
        int[] to = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
            deg[from[i]]++;
            deg[to[i]]++;
        }
        for (int i = 0; i < n; i++) {
            edges[i] = new int[deg[i]];
        }
        for (int i = 0; i < n - 1; i++) {
            edges[from[i]][--deg[from[i]]] = to[i];
            edges[to[i]][--deg[to[i]]] = from[i];
        }
        enterTime = new int[n];
        curTime = 0;
        parent = new int[n];
        depth = new int[n];
        dfs(0, -1, 0);
        initLCA(n);
        Comparator<Integer> comp = new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return enterTime[o1] - enterTime[o2];
            }
        };
        all:
        for (int query = 0; query < m; query++) {
            int k = in.nextInt();
            if (k == 2) {
                int v = in.nextInt() - 1;
                int u = in.nextInt() - 1;
                int lca = lca(u, v);
                int answer = depth[v] + depth[u] - 2 * (depth[lca]) - 1;
                out.println(answer);
                continue;
            }
            Integer[] v = new Integer[k];
            for (int i = 0; i < k; i++) {
                v[i] = in.nextInt() - 1;
            }
            Arrays.sort(v, comp);
            int[] lcaPref = new int[k];
            int[] lcaSuf = new int[k];
            lcaPref[0] = v[0];
            for (int i = 1; i < k; i++) {
                lcaPref[i] = lca(lcaPref[i - 1], v[i]);
            }
            lcaSuf[k - 1] = v[k - 1];
            for (int i = k - 2; i >= 0; i--) {
                lcaSuf[i] = lca(lcaSuf[i + 1], v[i]);
            }
            int[] without = new int[k];
            for (int i = 0; i < k; i++) {
                if (i == 0) {
                    without[0] = lcaSuf[1];
                } else if (i + 1 == k) {
                    without[i] = lcaPref[k - 2];
                } else {
                    without[i] = lca(lcaPref[i - 1], lcaSuf[i + 1]);
                }
            }
            boolean eq = true;
            for (int i = 1; i < k; i++) {
                eq &= without[i] == without[i - 1];
            }
            if (!eq) {
                for (int i = 0; i + 1 < k; i++) {
                    if (without[i] != without[i + 1]) {
                        int a = without[i];
                        int b = without[i + 1];
                        boolean good1 = true;
                        boolean good2 = true;
                        for (int j = 0; j < k; j++) {
                            if (j != i && j != i + 1) {
                                if (a != without[j]) {
                                    good1 = false;
                                }
                                if (b != without[j]) {
                                    good2 = false;
                                }
                            }
                        }
                        if (!good1 && !good2) {
                            out.println(0);
                            continue all;
                        }
                        int remove;
                        if (good1) {
                            remove = i + 1;
                        } else {
                            remove = i;
                        }
                        Integer t = v[k - 1];
                        v[k - 1] = v[remove];
                        v[remove] = t;
                        v = Arrays.copyOf(v, --k);
                        Arrays.sort(v, comp);
                        break;
                    }
                }
            }
            int lcaAll = v[0];
            for (int i = 1; i < k; i++) {
                lcaAll = lca(lcaAll, v[i]);
            }
//            System.out.println(lcaAll);
            for (int i = 0; i < k; i++) {
                if (lcaAll == v[i]) {
                    out.println(0);
                    continue all;
                }
            }
//            if (v[0] == lcaAll) {
//                int lcaExceptOne = v[1];
//                for (int i = 1; i < k; i++) {
//                    lcaExceptOne = lca(lcaExceptOne, v[i]);
//                }
//                for (int i = 0; i < k; i++) {
//                    if (lcaExceptOne == v[i]) {
//                        out.println(0);
//                        continue all;
//                    }
//                }
//                v = Arrays.copyOfRange(v, 1, k);
//                --k;
//                lcaAll = lcaExceptOne;
//            }
            boolean good = true;
            for (int i = 0; i + 1 < k; i++) {
                if (lca(v[i], v[i + 1]) != lcaAll) {
                    good = false;
                    break;
                }
            }
            out.println(good ? 1 : 0);
        }
    }

    static int[][] edges;
    static int[] parent;
    static int[][] dp;
    static int[] depth;
    static int[] enterTime;
    static int curTime;

    static void dfs(int v, int p, int d) {
        parent[v] = p;
        depth[v] = d;
        enterTime[v] = curTime++;
        for (int i = 0; i < edges[v].length; i++) {
            int e = edges[v][i];
            if (e == p) {
                continue;
            }
            dfs(e, v, d + 1);
        }
    }

    static void initLCA(int n) {
        int m = Integer.numberOfTrailingZeros(Integer.highestOneBit(n)) + 2;
        dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = parent[i] < 0 ? i : parent[i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = dp[i - 1][dp[i - 1][j]];
            }
        }
    }

    static int lca(int v, int u) {
        if (depth[u] > depth[v]) {
            int t = u;
            u = v;
            v = t;
        }
        for (int i = dp.length - 1; i >= 0; i--) {
            if (depth[v] - depth[u] >= 1 << i) {
                v = dp[i][v];
            }
        }
        if (depth[v] != depth[u]) {
            throw new AssertionError();
        }
        if (u == v) {
            return u;
        }
        for (int i = dp.length - 1; i >= 0; i--) {
            if (dp[i][u] != dp[i][v]) {
                u = dp[i][u];
                v = dp[i][v];
            }
        }
        if (parent[u] != parent[v]) {
            throw new AssertionError();
        }
        return parent[u];
    }
}
