package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class TaskC {
    static class Edge {
        int from;
        int to;
        int w;

        Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }

    static List<Edge>[] edges;
    static int[] need;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<Edge>();
        }
        Edge[] ed = new Edge[m];
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int flow = in.nextInt();
            Edge e = new Edge(from, to, flow);
            ed[i] = e;
            edges[from].add(e);
            edges[to].add(new Edge(to, from, flow));
        }
        int[] need = new int[n];
        for (int i = 0; i < n; i++) {
            for (Edge e : edges[i]) {
                need[i] += e.w;
            }
        }
        for (int i = 1; i + 1 < n; i++) {
            need[i] /= 2;
        }
        int[] q = new int[n];
        int head = 0;
        int tail = 1;
        boolean[] was = new boolean[n];
        int[] index = new int[n];
        int cnt = 0;
        was[0] = true;
        while (head < tail) {
            int v = q[head++];
            index[v] = cnt++;
            for (Edge e : edges[v]) {
                if (was[e.to]) {
                    continue;
                }
                need[e.to] -= e.w;
                if (need[e.to] == 0) {
                    was[e.to] = true;
                    q[tail++] = e.to;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            Edge e = ed[i];
            if (index[e.to] < index[e.from]) {
                out.println(1);
            } else {
                out.println(0);
            }
        }
    }
}
