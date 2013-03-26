import static java.util.Arrays.fill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static void solve() throws IOException {
        int tests = nextInt();
        while (tests-- > 0) {
            int rows = nextInt();
            int cols = nextInt();
            BitSet[] input = new BitSet[rows];
            for (int i = 0; i < rows; i++) {
                input[i] = new BitSet();
                String s = nextToken();
                for (int j = 0; j < s.length(); j++) {
                    if (s.charAt(j) == '1') {
                        input[i].set(j);
                    }
                }
            }
            boolean firstManWins = solve(input, cols);
            out.println(firstManWins ? "YES" : "NO");
        }
    }

    private static boolean solve(BitSet[] input, int cols) {
        int n = input.length;
        if (n == 0) {
            return false;
        }
        if ((n & 1) != 0) {
            return true;
        }

        boolean[] isOddPrime = primes(cols);

        int odd = 0, even = 0;

        int[] id = new int[n];

        for (int i = 0; i < n; i++) {
            int value = input[i].cardinality();
            if ((value & 1) == 0) {
                id[i] = even;
                ++even;
            } else {
                id[i] = ~odd;
                ++odd;
            }
        }

        if (even != odd) {
            return true;
        }

        Graph graph = new Graph(even, odd);

        for (int i = 0; i < n; i++) {
            if (id[i] < 0) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                BitSet ok = new BitSet();
                ok.or(input[i]);
                ok.xor(input[j]);
                if (isOddPrime[ok.cardinality()]) {
                    graph.addEdge(id[i], ~id[j]);
                }
            }
        }

        return graph.matching() != even;
    }

    private static boolean[] primes(int limit) {
        boolean[] isOddPrime = new boolean[limit + 1];
        fill(isOddPrime, true);
        isOddPrime[0] = isOddPrime[1] = false;
        for (int i = 0; i <= limit; i += 2) {
            isOddPrime[i] = false;
        }
        for (int i = 3; i * i <= limit; i++) {
            if (isOddPrime[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isOddPrime[j] = false;
                }
            }
        }
        return isOddPrime;
    }

    public static class Graph {
        private final static int INF = -1;
        public final int n, m;
        public final int[] mt;
        public final boolean[] matched;
        public final List<Integer>[] edges;
        public final int[][] e;

        private final int[] dist, start, queue;

        @SuppressWarnings("unchecked")
        public Graph(int n, int m) {
            this.n = n;
            this.m = m;
            edges = new List[n];
            for (int i = 0; i < n; i++) {
                edges[i] = new ArrayList<Integer>();
            }
            matched = new boolean[n];
            mt = new int[m];
            Arrays.fill(mt, n);
            dist = new int[n + 1];
            start = new int[n];
            e = new int[n][];
            queue = new int[n + 1];
        }

        public void addEdge(int u, int v) {
            edges[u].add(v);
            if (!matched[u] && mt[v] == n) {
                matched[u] = true;
                mt[v] = u;
            }
        }

        public void fastEdges() {
            for (int i = 0; i < n; i++) {
                e[i] = new int[edges[i].size()];
                int cnt = 0;
                for (int j : edges[i])
                    e[i][cnt++] = j;
            }
        }

        public int matching() {
            fastEdges();
            while (bfs()) {
                Arrays.fill(start, 0);
                for (int i = 0; i < n; i++) {
                    if (!matched[i] && dfs(i)) {
                        matched[i] = true;
                    }
                }
            }
            int ans = 0;
            for (int i = 0; i < m; i++) {
                if (mt[i] < n)
                    ++ans;
            }
            return ans;
        }

        private boolean bfs() {
            int qt = 0;
            for (int i = 0; i < n; i++) {
                if (!matched[i]) {
                    dist[i] = 0;
                    queue[qt++] = i;
                } else
                    dist[i] = INF;
            }
            dist[n] = INF;
            for (int qh = 0; qh < qt; qh++) {
                int u = queue[qh];
                for (int i : e[u]) {
                    int v = mt[i];
                    if (dist[v] == INF) {
                        dist[v] = dist[u] + 1;
                        queue[qt++] = v;
                        if (v == n)
                            return true;
                    }
                }
            }
            return false;
        }

        private boolean dfs(int u) {
            if (u == n)
                return true;
            int d = dist[u] + 1;
            for (int i = start[u]; i < e[u].length; start[u] = ++i) {
                int to = e[u][i];
                int v = mt[to];
                if (dist[v] == d && dfs(v)) {
                    mt[to] = u;
                    return true;
                }
            }
            return false;
        }
    }

    static BufferedReader br;
    static PrintWriter out;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        // if (new File("4.in").exists()) {
        // br = new BufferedReader(new FileReader("4.in"));
        // } else {
        br = new BufferedReader(new InputStreamReader(System.in));
        // }
        out = new PrintWriter(System.out);
        solve();
        out.close();
    }

    static String nextToken() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String line = br.readLine();
            if (line == null) {
                return null;
            }
            st = new StringTokenizer(line);
        }
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    static long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }
}
