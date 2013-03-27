package coding;

import ru.ifmo.niyaz.graphalgorithms.GraphUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] from = new int[m];
        int[] to = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
        }
        int[][] edges = GraphUtils.getEdgesUndirectedUnweighted(n, from, to);
        Queue<Integer> q = new ArrayDeque<Integer>();
        int[] color = new int[n];
        for (int i = 0; i < n; i++) {
            q.add(i);
        }
        boolean[] inq = new boolean[n];
        Arrays.fill(inq, true);
        while (!q.isEmpty()) {
            int v = q.poll();
            inq[v] = false;
            int cnt = 0;
            for (int j : edges[v]) {
                if (color[v] == color[j]) {
                    ++cnt;
                }
            }
            if (cnt < 2) {
                continue;
            }
            color[v] ^= 1;
            for (int j : edges[v]) {
                if (inq[j]) {
                    continue;
                }
                cnt = 0;
                for (int k : edges[j]) {
                    if (color[k] == color[j]) {
                        ++cnt;
                    }
                }
                if (cnt > 1) {
                    inq[j] = true;
                    q.add(j);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            out.print(color[i]);
        }
        out.println();
    }
}
