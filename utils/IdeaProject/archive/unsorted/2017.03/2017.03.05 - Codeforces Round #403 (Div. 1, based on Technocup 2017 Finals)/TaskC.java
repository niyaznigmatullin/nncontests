package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class TaskC {
    static List<Integer>[] edges;
    static boolean[] was;
    static int[] path;
    static int cn;

    static void dfs(int v) {
        was[v] = true;
        path[cn++] = v;
        for (int i = 0; i < edges[v].size(); i++) {
            int to = edges[v].get(i);
            if (was[to]) continue;
            dfs(to);
            path[cn++] = v;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            edges[a].add(b);
            edges[b].add(a);
        }
        was = new boolean[n];
        cn = 0;
        path = new int[n * 2];
        dfs(0);
        int one = (2 * n + k - 1) / k;
        int printed = 0;
        for (int i = 0; i < cn; i += one) {
            int from = i;
            int to = Math.min(cn, i + one);
            out.print(to - from);
            for (int j = from; j < to; j++) {
                out.print(" " + (path[j] + 1));
            }
            out.println();
            printed++;
        }
        if (printed > k) throw new AssertionError();
        while (printed < k) {
            out.println("1 1");
            ++printed;
        }
    }
}
