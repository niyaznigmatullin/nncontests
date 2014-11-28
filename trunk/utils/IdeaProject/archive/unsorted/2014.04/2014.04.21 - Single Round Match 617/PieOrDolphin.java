package coding;

import ru.ifmo.niyaz.graphalgorithms.DinicGraph;

import java.util.Arrays;

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
