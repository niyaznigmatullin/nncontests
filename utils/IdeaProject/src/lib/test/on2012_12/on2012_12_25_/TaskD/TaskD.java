package lib.test.on2012_12.on2012_12_25_.TaskD;



import ru.ifmo.niyaz.graphalgorithms.GraphUtils;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.io.FastScanner;

import java.util.Arrays;

public class TaskD {

    static int[][] edges;
    static int curAnswer;
    static boolean[] bad;

    static int dfs(int v, int p, int t) {
        if (v == t) {
            bad[v] = true;
            return 1;
        }
        for (int i : edges[v]) {
            if (i == p) {
                continue;
            }
            int got = dfs(i, v, t);
            if (got > 0) {
                bad[v] = true;
                return got + 1;
            }
        }
        return 0;
    }

    static int dfs2(int v, int p) {
        bad[v] = true;
        int max1 = 0;
        int max2 = 0;
        for (int i : edges[v]) {
            if (i == p || bad[i]) {
                continue;
            }
            int got = dfs2(i, v);
            if (max1 < got) {
                max2 = max1;
                max1 = got;
            } else if (max2 < got) {
                max2 = got;
            }
        }
        curAnswer = Math.max(curAnswer, max1 + max2);
        return max1 + 1;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] from = new int[n - 1];
        int[] to = new int[n - 1];
        for (int i = 0; i + 1 < n; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
        }
        edges = GraphUtils.getEdgesUndirectedUnweighted(n, from, to);
        bad = new boolean[n];
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                Arrays.fill(bad, false);
                int len = dfs(i, -1, j) - 1;
                curAnswer = 0;
                for (int v = 0; v < n; v++) {
                    if (bad[v]) {
                        continue;
                    }
                    dfs2(v, -1);
                }
                answer = Math.max(answer, curAnswer * len);
            }
        }
        out.println(answer);
    }
}
