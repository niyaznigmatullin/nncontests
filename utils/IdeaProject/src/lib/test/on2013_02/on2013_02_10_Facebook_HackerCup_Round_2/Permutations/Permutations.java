package lib.test.on2013_02.on2013_02_10_Facebook_HackerCup_Round_2.Permutations;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Permutations {

    static final int MOD = 1000000007;

    static class Edge {
        int from;
        int to;
        boolean less;

        Edge(int from, int to, boolean less) {
            this.from = from;
            this.to = to;
            this.less = less;
        }
    }

    static int[][] C;

    static {
        C = new int[1111][1111];
        for (int i = 0; i < C.length; i++) {
            C[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
                if (C[i][j] >= MOD) {
                    C[i][j] -= MOD;
                }
            }
        }
    }

    static List<Edge>[] edges;
    static int[][] dp;
    static int[] sz;

    static int modMul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }

    static void dfs(int v, int p) {
        sz[v] = 1;
        for (Edge e : edges[v]) {
            if (e.to == p) {
                continue;
            }
            dfs(e.to, v);
            sz[v] += sz[e.to];
        }
        int[] curDP = new int[sz[v]];
        curDP[0] = 1;
        int[] nextDP = new int[sz[v]];
        int csz = 0;
        for (Edge e : edges[v]) {
            if (e.to == p) {
                continue;
            }
            int curSZ = sz[e.to];
            if (e.less) {
                int cur = 0;
                for (int give = 1; give <= curSZ; give++) {
                    cur += dp[e.to][give - 1];
                    if (cur >= MOD) cur -= MOD;
                    for (int already = 0; already <= csz; already++) {
                        int val = curDP[already];
                        if (val == 0) {
                            continue;
                        }
                        int ways = (int) ((long) C[already + give][give] * C[csz - already + curSZ - give][curSZ - give] % MOD);
                        ways = modMul(ways, cur);
                        ways = modMul(ways, val);
                        nextDP[already + give] += ways;
                        if (nextDP[already + give] >= MOD) nextDP[already + give] -= MOD;
                    }
                }
            } else {
                int cur = 0;
                for (int give = 1; give <= curSZ; give++) {
                    cur += dp[e.to][curSZ - give];
                    if (cur >= MOD) cur -= MOD;
                    for (int already = 0; already <= csz; already++) {
                        int val = curDP[already];
                        if (val == 0) {
                            continue;
                        }
                        int ways = modMul(C[already + curSZ - give][curSZ - give], C[csz - already + give][give]);
                        ways = modMul(ways, cur);
                        ways = modMul(ways, val);
                        nextDP[already + curSZ - give] += ways;
                        if (nextDP[already + curSZ - give] >= MOD) nextDP[already + curSZ - give] -= MOD;
                    }
                }
            }
            csz += curSZ;
            int[] t = curDP;
            curDP = nextDP;
            nextDP = t;
            Arrays.fill(nextDP, 0);
        }
        dp[v] = curDP;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println("test #" + testNumber);
        int n = in.nextInt();
        int[] from = new int[n - 1];
        int[] to = new int[n - 1];
        for (int i = 0; i + 1 < n; i++) {
            from[i] = in.nextInt();
            String type = in.next();
            to[i] = in.nextInt();
            if (type.equals(">")) {
                int t = from[i];
                from[i] = to[i];
                to[i] = t;
            }
        }
        out.println("Case #" + testNumber + ": " + solve(n, from, to));
    }

    static final Random rand = new Random(213334L);
    static void test() {
        while (true) {
            int n = rand.nextInt(10) + 1;
            int[] from = new int[n - 1];
            int[] to = new int[n - 1];
            for (int i = 0; i < n - 1; i++) {
                from[i] = i + 1;
                to[i] = rand.nextInt(i + 1);
                if (rand.nextBoolean()) {
                    int t = from[i];
                    from[i] = to[i];
                    to[i] = t;
                }
            }
//            long time = System.currentTimeMillis();
            int ans1 = solve(n, from, to);
//            System.out.println(System.currentTimeMillis() - time);
            int ans2 = solveStupid(n, from, to);
            if (ans1 != ans2) {
                throw new AssertionError();
            }
            System.out.println(ans1);
        }
    }

    static int solveStupid(int n, int[] from, int[] to) {
        was = new boolean[n];
        p = new int[n];
        return go(0, n, from, to);
    }

    static boolean[] was;
    static int[] p;

    static int go(int x, int n, int[] from, int[] to) {
        if (x == n) {
            boolean ok = true;
            for (int i = 0; i < from.length; i++) {
                if (p[from[i]] > p[to[i]]) {
                    ok = false;
                    break;
                }
            }
            return ok ? 1 : 0;
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (was[i]) {
                continue;
            }
            was[i] = true;
            p[x] = i;
            ret += go(x + 1, n, from, to);
            was[i] = false;
        }
        return ret;
    }

    static int solve(int n, int[] from, int[] to) {
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<Edge>();
        }
        for (int i = 0; i + 1 < n; i++) {
            edges[from[i]].add(new Edge(from[i], to[i], false));
            edges[to[i]].add(new Edge(to[i], from[i], true));
        }
        dp = new int[n][];
        sz = new int[n];
        dfs(0, 0);
        int ans = 0;
        for (int i = 0; i < dp[0].length; i++) {
            ans += dp[0][i];
            ans %= MOD;
        }
        return ans;
    }
}
