package coding;

import ru.ifmo.niyaz.graphalgorithms.DinicGraph;

public class Singing {
    public int solve(int n, int low, int high, int[] pitch) {
        DinicGraph g = new DinicGraph(n + 2);
        int src = n;
        int tar = n + 1;
        for (int i = 0; i + 1 < pitch.length; i++) {
            if (pitch[i] != pitch[i + 1]) {
                g.addUndirectedEdge(pitch[i] - 1, pitch[i + 1] - 1, 1);
            }
        }
        final int INF = 1000000;
        for (int i = 1; i <= n; i++) {
            if (i < low) {
                g.addEdge(src, i - 1, INF);
            }
            if (i > high) {
                g.addEdge(i - 1, tar, INF);
            }
        }
        return (int) g.getMaxFlow(src, tar);
    }
}
