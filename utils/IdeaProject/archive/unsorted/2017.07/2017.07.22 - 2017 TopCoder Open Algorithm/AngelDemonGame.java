package coding;

import ru.ifmo.niyaz.graphalgorithms.MinCostMaxFlowGraph;

public class AngelDemonGame {
    public String winner(String[] G, int A, int D) {
        int n = G.length;
        if (D >= n - 1) {
            return "Demon";
        }
        MinCostMaxFlowGraph g = new MinCostMaxFlowGraph(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (G[i].charAt(j) == 'Y') {
                    g.addEdge(i, j, 1, 0);
                } else {
                    g.addEdge(i, j, 1, 1);
                }
            }
        }
        if (g.getMinCostKFlow(0, n - 1, D + 1)[1] <= A) {
            return "Angel";
        } else {
            return "Unknown";
        }
    }
}
