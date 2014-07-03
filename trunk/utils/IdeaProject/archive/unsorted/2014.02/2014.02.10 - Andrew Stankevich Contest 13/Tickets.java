package coding;

import ru.ifmo.niyaz.graphalgorithms.DinicGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.io.IOException;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class Tickets {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String[] firstLine = in.readLine().split(" ");
        int k = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        ids = new HashMap<>();
        int[] reqU = new int[k];
        int[] reqV = new int[k];
        for (int i = 0; i < k; i++) {
            String[] line = in.readLine().split(",");
            reqV[i] = getID(line[0].trim());
            reqU[i] = getID(line[1].trim());
        }
        int[] from = new int[m];
        int[] to = new int[m];
        for (int i = 0; i < m; i++) {
            String[] line = in.readLine().split(",");
            from[i] = getID(line[0].trim());
            to[i] = getID(line[1].trim());
        }
        int n = ids.size();
        int[] degIn = new int[n];
        int[] degOut = new int[n];
        for (int i = 0; i < k; i++) {
            degIn[reqV[i]]++;
            degOut[reqU[i]]++;
        }
        for (int i = 0; i < n; i++) {
            if (degIn[i] > 1 || degOut[i] > 1) {
                out.println("NO");
                return;
            }
        }
        int[] canDo = new int[k];
        boolean[] usedEdge = new boolean[m];
        Arrays.fill(canDo, -1);
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < m; j++) {
                if (usedEdge[j]) continue;
                if (reqU[i] == from[j] && reqV[i] == to[j] || reqU[i] == to[j] && reqV[i] == from[j]) {
                    canDo[i] = j;
                    usedEdge[j] = true;
                    break;
                }
            }
        }
        BitSet[] bs = new BitSet[n];
        boolean[] free = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (degIn[i] > 0 || degOut[i] > 0) {
                bs[i] = new BitSet();
            } else {
                free[i] = true;
            }
        }
        for (int i = 0; i < m; i++) {
            int v = from[i];
            int u = to[i];
            if (free[u] && !free[v]) bs[v].set(u);
            if (free[v] && !free[u]) bs[u].set(v);
        }
        DinicGraph g = new DinicGraph(1 + k + n + 1);
        int need = 0;
        for (int i = 0; i < k; i++) {
            if (canDo[i] < 0) {
                ++need;
                g.addEdge(k + n, i, 1);
                BitSet z = new BitSet();
                z.or(bs[reqU[i]]);
                z.and(bs[reqV[i]]);
                for (int j = z.nextSetBit(0); j >= 0; j = z.nextSetBit(j + 1)) {
                    g.addEdge(i, j + k, 1);
                }
            }
        }
        for (int i = 0; i < n; i++) if (free[i]) g.addEdge(i + k, k + n + 1, 1);
        long flow = g.getMaxFlow(k + n, k + n + 1);
        if (flow != need) {
            out.println("NO");
            return;
        }
        out.println("YES");
        edgeIDs = new HashMap<>();
        for (int i = 0; i < m; i++) {
            edgeIDs.put(new Edge(from[i], to[i]), i);
        }
        for (int i = 0; i < k; i++) {
            if (canDo[i] >= 0) {
                out.println("1 " + (canDo[i] + 1));
            } else {
                for (DinicGraph.Edge e : g.edges[i]) {
                    if (e.flow > 0) {
                        int city = e.to - k;
                        out.println("2 " + (edgeIDs.get(new Edge(reqV[i], city)) + 1) + " " + (edgeIDs.get(new Edge(city, reqU[i])) + 1));
                        break;
                    }
                }
            }
        }
    }

    static Map<Edge, Integer> edgeIDs;

    static class Edge {
        int v;
        int u;

        @Override
        public boolean equals(Object o) {

            if (this == o) return true;
            if (!(o instanceof Edge)) return false;

            Edge edge = (Edge) o;

            if (u != edge.u) return false;
            if (v != edge.v) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = v;
            result = 31 * result + u;
            return result;
        }

        Edge(int v, int u) {
            if (v > u) {
                int t = v;
                v = u;
                u = t;
            }
            this.v = v;
            this.u = u;
        }
    }

    static Map<String, Integer> ids;

    static int getID(String s) {
        Integer f = ids.get(s);
        if (f == null) f = ids.size();
        ids.put(s, f);
        return f;
    }
}
