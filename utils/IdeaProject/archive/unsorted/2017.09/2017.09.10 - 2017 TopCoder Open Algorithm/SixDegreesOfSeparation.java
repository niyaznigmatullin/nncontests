package coding;

import ru.ifmo.niyaz.graphalgorithms.GraphUtils;

import java.util.ArrayList;
import java.util.List;

public class SixDegreesOfSeparation {

    static int[][] g;

    static List<int[]> answer;
    static boolean[] was;

    static int dfs(int v, int pv) {
        int mx = 0;
        was[v] = true;
        for (int to : g[v]) {
            if (was[to]) continue;
            mx = Math.max(mx, dfs(to, v));
        }
        if (mx == 2) {
            if (v != 0 && pv != 0) {
                answer.add(new int[]{v, 0});
            }
            return 0;
        }
        return mx + 1;
    }

    public int[] getAdditionalEdges(int n, int[] a, int[] b) {
        g = GraphUtils.getEdgesUndirectedUnweighted(n, a, b);
        was = new boolean[n];
        answer = new ArrayList<>();
        dfs(0, -1);
        int[] ans = new int[answer.size() * 2];
        int cn = 0;
        for (int[] e : answer) {
            ans[cn++] = e[0];
            ans[cn++] = e[1];
        }
        return ans;
    }
}
