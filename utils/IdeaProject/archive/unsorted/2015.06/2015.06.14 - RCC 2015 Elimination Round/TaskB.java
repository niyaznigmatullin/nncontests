package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskB {
    static class Edge {
        int from;
        int to;
        int w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }

    static List<Edge>[] edges;
    static int[] wants;
    static boolean[] was;
    static int dfsCountOnes;
    static int dfsCountAll;
    static List<Integer> vs;

    static boolean dfs(int v) {
        was[v] = true;
        ++dfsCountAll;
        vs.add(v);
        if (wants[v] == 1) dfsCountOnes++;
        for (Edge e : edges[v]) {
            if (wants[e.to] == 0) {
                wants[e.to] = e.w == 0 ? wants[v] : (3 - wants[v]);
            }
            if (!was[e.to]) {
                if (!dfs(e.to)) return false;
            }
            if (wants[e.to] != (e.w == 0 ? wants[v] : (3 - wants[v]))) {
                return false;
            }
        }
        return true;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        wants = in.readIntArray(n);
        edges = new List[n];
        for (int i = 0; i < n; i++) edges[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int c = in.nextInt();
            edges[from].add(new Edge(from, to, c));
            edges[to].add(new Edge(to, from, c));
        }
        if (n % 2 != 0) {
            out.println("NO");
            return;
        }
        was = new boolean[n];
        vs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (was[i]) continue;
            if (wants[i] == 0) continue;
            if (!dfs(i)) {
                out.println("NO");
                return;
            }
        }
        int countOnes = 0;
        int countTwos = 0;
        for (int i = 0; i < n; i++) {
            if (wants[i] == 1) ++countOnes;
            if (wants[i] == 2) ++countTwos;
        }
        if (countOnes * 2 > n || countTwos * 2 > n) {
            out.println("NO");
            return;
        }
        List<Element> z = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (was[i]) continue;
            dfsCountAll = 0;
            dfsCountOnes = 0;
            vs.clear();
            wants[i] = 1;
            if (!dfs(i)) {
                out.println("NO");
                return;
            }
            z.add(new Element(dfsCountAll, dfsCountOnes, new ArrayList<>(vs)));
        }
        int need = n / 2 - countOnes;
        int[][] dp = new int[z.size() + 1][need + 1];
        for (int[] e : dp) {
            Arrays.fill(e, -1);
        }
        dp[0][0] = 0;
        for (int i = 0; i < z.size(); i++) {
            Element e = z.get(i);
            for (int have = 0; have <= need; have++) {
                if (dp[i][have] < 0) continue;
                if (have + e.ones <= need) {
                    dp[i + 1][have + e.ones] = 1;
                }
                if (have + e.all - e.ones <= need) {
                    dp[i + 1][have + e.all - e.ones] = 2;
                }
            }
        }
        if (dp[z.size()][need] < 0) {
            out.println("NO");
            return;
        }
        for (int i = z.size(), have = need; i > 0; i--) {
            Element e = z.get(i - 1);
            if (dp[i][have] == 1) {
                have -= e.ones;
            } else {
                for (int q : e.vs) {
                    wants[q] = 3 - wants[q];
                }
                have -= e.all - e.ones;
            }
        }
        out.println("YES");
        out.printArray(wants);
    }

    static class Element {
        int all;
        int ones;
        List<Integer> vs;

        public Element(int all, int ones, List<Integer> vs) {
            this.all = all;
            this.ones = ones;
            this.vs = vs;
        }
    }
}
