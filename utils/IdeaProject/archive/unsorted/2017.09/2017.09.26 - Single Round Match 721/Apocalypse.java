package coding;

import ru.ifmo.niyaz.graphalgorithms.MinCostMaxFlowGraph;

import java.util.ArrayList;
import java.util.List;

public class Apocalypse {
    public int maximalSurvival(int[] p, int[] position, int t) {
        int n = p.length + 1;
        MinCostMaxFlowGraph g = new MinCostMaxFlowGraph(2 + 2 * n * (t + 1));
        int src = 2 * n * (t + 1);
        int tar = src + 1;
        final int SHIFT = n * (t + 1);
        List<Integer>[] edges = new List[n];
        for (int i = 0; i < n; i++) edges[i] = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            edges[i + 1].add(p[i]);
            edges[p[i]].add(i + 1);
        }
        for (int i = 0; i < SHIFT; i++) {
            g.addEdge(i, i + SHIFT, 1, 0);
        }
        for (int i : position) {
            g.addEdge(src, i, 1, 0);
        }
        for (int i = 0; i < t; i++) {
            for (int v = 0; v < n; v++) {
                int fromV = SHIFT + i * n + v;
                for (int to : edges[v]) {
                    int toV = (i + 1) * n + to;
                    g.addEdge(fromV, toV, 1, 0);
                }
            }
        }
        boolean[] hasBomb = new boolean[n];
        for (int i : position) hasBomb[i] = true;
        for (int i = 0; i < n; i++) {
            g.addEdge(t * n + i + SHIFT, tar, 1, hasBomb[i] ? 0 : -1);
        }
        return -(int) g.getMinCostMaxFlow(src, tar)[1];
    }
}
