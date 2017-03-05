package coding;

import ru.ifmo.niyaz.graphalgorithms.GraphUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class GraphColoring {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] v = new int[m];
        int[] u = new int[m];
        for (int i = 0; i < m; i++) {
            v[i] = in.nextInt() - 1;
            u[i] = in.nextInt() - 1;
        }
        int[] ans = colorGraph(n, v, u);
        for (int i = 0; i < n; i++) ans[i]++;

        out.printArray(ans);
    }

    static int[] colorGraph(int n, int[] vEdges, int[] uEdges) {
        int m = vEdges.length;
        if (m == 0) {
            return new int[n];
        }
        int[] q = new int[n];
        int[] color = new int[n];
        Arrays.fill(color, -1);
        int[][] edges = GraphUtils.getEdgesUndirectedUnweighted(n, vEdges, uEdges);
        boolean canMake2 = true;
        all:
        for (int start = 0; start < n; start++) {
            if (color[start] >= 0) continue;
            int head = 0;
            int tail = 0;
            q[tail++] = start;
            color[start] = 0;
            while (head < tail) {
                int v = q[head++];
                for (int to : edges[v]) {
                    if (color[to] < 0) {
                        color[to] = color[v] ^ 1;
                        q[tail++] = to;
                    }
                    if (color[to] != (color[v] ^ 1)) {
                        canMake2 = false;
                        break all;
                    }
                }
            }
        }
        if (canMake2) {
            return color;
        }
        Arrays.fill(color, -1);
        int[] deg = new int[n];
        int head = 0;
        int tail = 0;
        for (int i = 0; i < n; i++) {
            deg[i] = edges[i].length;
            if (deg[i] < 3) {
                q[tail++] = i;
            }
        }
        while (head < tail) {
            int v = q[head++];
            for (int to : edges[v]) {
                deg[to]--;
                if (deg[to] == 2) {
                    q[tail++] = to;
                }
            }
        }
        for (int it = n - 1; it >= 0; it--) {
            int v = q[it];
            int mask = 0;
            for (int to : edges[v]) {
                if (color[to] >= 0) mask |= 1 << color[to];
            }
            int c = 0;
            while (((mask >> c) & 1) == 1) {
                ++c;
            }
            color[v] = c;
        }
        return color;
    }
}
