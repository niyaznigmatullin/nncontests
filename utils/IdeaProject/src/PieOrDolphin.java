import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author Niyaz Nigmatullin
 */
public class PieOrDolphin {
    public int[] Distribute(int[] choice1, int[] choice2) {
        DinicGraph g = new DinicGraph(4 + 50);
        int n = choice1.length;
        int[] cur = new int[50];
        for (int i = 0; i < choice1.length; i++) {
            cur[choice1[i]]--;
            cur[choice2[i]]++;
        }
        int src = 50;
        int tar = 51;
        int src2 = 52;
        int tar2 = 53;
        for (int i = 0; i < 50; i++) {
            if (cur[i] > 0) {
                g.addEdge(src, i, cur[i]);
            }
            if (cur[i] < 0) {
                g.addEdge(i, tar, -cur[i]);
            }
        }
        DinicGraph.Edge[] edges = new DinicGraph.Edge[n];
        for (int i = 0; i < n; i++) {
            edges[i] = g.addEdge(choice2[i], choice1[i], 2);
        }
        g.getMaxFlow(src, tar);
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        for (int i = 0; i < n; i++) {
            if (edges[i].flow > 0) {
                ans[i] = 2;
            }
        }
        return ans;
    }
}

class DinicGraph {

    public static class Edge {
        public int from;
        public int to;
        public int flow;
        public int cap;
        public Edge rev;

        public Edge(int from, int to, int flow, int cap) {
            super();
            this.from = from;
            this.to = to;
            this.flow = flow;
            this.cap = cap;
        }

    }

    public ArrayList<Edge>[] edges;
    int[] cur;
    int[] q;
    public int[] d;
    int n;

    public DinicGraph(int n) {
        edges = new ArrayList[n];
        this.n = n;
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new ArrayList<Edge>();
        }
    }

    public Edge addEdge(int from, int to, int cap) {
        Edge e1 = new Edge(from, to, 0, cap);
        Edge e2 = new Edge(to, from, 0, 0);
        e1.rev = e2;
        e2.rev = e1;
        edges[from].add(e1);
        edges[to].add(e2);
        return e1;
    }

    boolean bfs(int source, int target) {
        int head = 0;
        int tail = 1;
        Arrays.fill(d, Integer.MAX_VALUE);
        d[source] = 0;
        q[0] = source;
        while (head < tail) {
            int x = q[head++];
            for (Edge e : edges[x]) {
                if (e.cap - e.flow > 0 && d[e.to] == Integer.MAX_VALUE) {
                    d[e.to] = d[x] + 1;
                    q[tail++] = e.to;
                    if (e.to == target) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int dfs(int x, int target, int cMin) {
        if (x == target) {
            return cMin;
        }
        for (int i = cur[x]; i < edges[x].size(); cur[x] = ++i) {
            Edge e = edges[x].get(i);
            if (d[e.to] != d[x] + 1 || e.cap - e.flow == 0) {
                continue;
            }
            int add = dfs(e.to, target, Math.min(cMin, e.cap - e.flow));
            if (add == 0) {
                continue;
            }
            e.flow += add;
            e.rev.flow -= add;
            return add;
        }
        return 0;
    }

    public long getMaxFlow(int source, int target) {
        cur = new int[n];
        q = new int[n];
        d = new int[n];
        long flow = 0;
        while (bfs(source, target)) {
            Arrays.fill(cur, 0);
            while (true) {
                int add = dfs(source, target, Integer.MAX_VALUE);
                if (add == 0) {
                    break;
                }
                flow += add;
            }
        }
        return flow;
    }


}

