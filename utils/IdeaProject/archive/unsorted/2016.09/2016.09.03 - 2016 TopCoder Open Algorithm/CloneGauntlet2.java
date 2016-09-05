package coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CloneGauntlet2 {
    public int minClones(int[] parent, int[] connection) {
        int n = parent.length + 1;
        boolean[] onPath = new boolean[n];
        for (int i = n - 1; i > 0; i = parent[i - 1]) onPath[i] = true;
        onPath[0] = true;
        boolean[] used = new boolean[n];
        int v = 0;
        int ans = 0;
        while (true) {
            if (v == n - 1) {
                break;
            }
            used[v] = true;
            if (connection[v] > 0 && used[parent[connection[v] - 1]] && !used[connection[v]]) {
                if (!onPath[v] || onPath[connection[v]]) ++ans;
                v = connection[v];
            } else {
                return -1;
            }
        }
        return ans;
    }
}
