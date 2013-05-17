package lib.test.on2013_04.on2013_04_27_Challenge24.Sabotage;



import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;
import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.graphalgorithms.DinicGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class Sabotage {

    static class Edge {
        int from;
        int to;
        int cost;
        int weight;
        int id;

        Edge(int from, int to, int cost, int weight, int id) {
            this.from = from;
            this.to = to;
            this.cost = cost;
            this.weight = weight;
            this.id = id;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Edge[] allEdges = new Edge[m];
        int[] costs = new int[m];
        for (int i = 0; i < m; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            int w = in.nextInt();
            int c = in.nextInt();
            costs[i] = c;
            allEdges[i] = new Edge(from, to, c, w, i);
        }
        Arrays.sort(allEdges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.weight, o2.weight);
            }
        });
        int[] map = new int[n];
        for (int i = 0; i < n; i++) map[i] = i;
        int vertices = n;
        int answer = Integer.MAX_VALUE;
        List<Integer> ansID = new ArrayList<>();
        for (int i = 0; i < m; ) {
            int j = i;
            while (j < m && allEdges[i].weight == allEdges[j].weight) {
                ++j;
            }
            DisjointSetUnion dsu = new DisjointSetUnion(vertices);
            List<Integer>[] components = new ArrayList[vertices];
            DinicGraph dg = new DinicGraph(vertices);
            for (int k = i; k < j; k++) {
                int v = map[allEdges[k].from];
                int u = map[allEdges[k].to];
                dsu.union(v, u);
                dg.addUndirectedEdge(v, u, allEdges[k].cost);
            }
            for (int k = 0; k < vertices; k++) {
                int c = dsu.get(k);
                if (components[c] == null) components[c] = new ArrayList<>();
                components[c].add(k);
            }
            for (int u = 0; u < vertices; u++) {
                int c = dsu.get(u);
                if (c != u) continue;
                for (int v : components[c]) {
                    if (v == u) {
                        continue;
                    }
                    dg.clear();
                    int flow = (int) dg.getMaxFlow(u, v);
                    if (flow < answer) {
                        answer = flow;
                        boolean[] cut = dg.getCut(u, v);
                        ansID.clear();
                        for (int k = i; k < j; k++) {
                            int from = map[allEdges[k].from];
                            int to = map[allEdges[k].to];
                            if (cut[from] != cut[to]) {
                                ansID.add(allEdges[k].id);
                            }
                        }
                    }
                }
            }
            int[] nids = new int[vertices];
            int cc = 0;
            for (int k = 0; k < vertices; k++) {
                if (dsu.get(k) == k) {
                    nids[k] = cc++;
                }
            }
            for (int k = 0; k < vertices; k++) {
                nids[k] = nids[dsu.get(k)];
            }
            for (int k = 0; k < n; k++) {
                map[k] = nids[map[k]];
            }
            vertices = cc;
            i = j;
        }
        out.println(answer);
        Collections.sort(ansID);
        {
            int sum = 0;
            for (int i : ansID) {
                sum += costs[i];
            }
            if (sum != answer) throw new AssertionError();
        }
        out.printArray(ArrayUtils.toPrimitiveArrayInteger(ansID));
    }
}
