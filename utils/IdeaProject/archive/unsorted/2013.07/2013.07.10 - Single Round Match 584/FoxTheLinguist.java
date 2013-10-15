package coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class FoxTheLinguist {
    public int minimalHours(int n, String[] courseInfo) {
        StringBuilder sb = new StringBuilder();
        for (String e : courseInfo) {
            sb.append(e);
        }
        StringTokenizer st = new StringTokenizer(sb.toString(), " :->");
        List<Edge> allEdges = new ArrayList<Edge>();
        for (int i = 2; i <= 10; i++) {
            for (int j = 0; j < n; j++) {
                allEdges.add(new Edge(j * 10 + i, j * 10 + i - 1, 0));
            }
        }
        for (int i = 0; i < n; i++) {
            allEdges.add(new Edge(i * 10 + 1, 0, 0));
            allEdges.add(new Edge(0, i * 10 + 1, 0));
        }
        while (st.hasMoreTokens()) {
            String a = st.nextToken();
            String b = st.nextToken();
            int cost = Integer.parseInt(st.nextToken());
            int va = getV(a);
            int vb = getV(b);
            if (va != vb) {
                allEdges.add(new Edge(va, vb, cost));
            }
        }
        int ans = solve(1 + n * 10, allEdges);
        if (ans == Integer.MAX_VALUE) {
            return -1;
        }
        return ans;
    }

    static int solve(int n, List<Edge> allEdges) {
        int ret = 0;
        int[] minimal = new int[n];
        Arrays.fill(minimal, Integer.MAX_VALUE);
        for (Edge e : allEdges) {
            minimal[e.to] = Math.min(minimal[e.to], e.cost);
        }
        List<Integer>[] edges = new ArrayList[n];
        for (int i = 0; i < n; i++) edges[i] = new ArrayList<Integer>();
        for (int i = 1; i < n; i++) {
            if (minimal[i] == Integer.MAX_VALUE) return Integer.MAX_VALUE;
            ret += minimal[i];
        }
        for (Edge e : allEdges) {
            if (e.to == 0) {
                continue;
            }
            e.cost -= minimal[e.to];
            if (e.cost == 0) {
                edges[e.from].add(e.to);
            }
        }
        boolean[][] canGo = new boolean[n][n];
        int[] q = new int[n];
        for (int start = 0; start < n; start++) {
            boolean[] was = canGo[start];
            was[start] = true;
            int head = 0;
            int tail = 0;
            q[tail++] = start;
            while (head < tail) {
                int v = q[head++];
                for (int i : edges[v]) {
                    if (!was[i]) {
                        was[i] = true;
                        q[tail++] = i;
                    }
                }
            }
        }
        int[] color = new int[n];
        Arrays.fill(color, -1);
        int cn = 0;
        for (int i = 0; i < n; i++) {
            if (color[i] >= 0) continue;
            color[i] = cn++;
            for (int j = i + 1; j < n; j++) {
                if (canGo[i][j] && canGo[j][i]) {
                    color[j] = color[i];
                }
            }
        }
        if (cn == n) return ret;
        List<Edge> nAllEdges = new ArrayList<Edge>();
        for (Edge e : allEdges) {
            if (color[e.from] != color[e.to]) {
                nAllEdges.add(new Edge(color[e.from], color[e.to], e.cost));
            }
        }
        int got = solve(cn, nAllEdges);
        if (got == Integer.MAX_VALUE) return Integer.MAX_VALUE;
        return ret + got;
    }

    static int getV(String s) {
        int i = s.charAt(0) - 'A';
        int j = s.charAt(1) - '0' + 1;
        return i * 10 + j;
    }

    static class Edge {
        int from;
        int to;
        int cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
