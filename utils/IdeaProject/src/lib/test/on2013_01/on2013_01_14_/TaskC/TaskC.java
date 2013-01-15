package lib.test.on2013_01.on2013_01_14_.TaskC;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Set<Integer>[] edges = new Set[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new HashSet<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int v = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            if (v == u) {
                continue;
            }
            edges[v].add(u);
            edges[u].add(v);
        }
        Set<Integer>[] edges2 = new Set[n];
        for (int i = 0; i < n; i++) {
            edges2[i] = new HashSet<Integer>(edges[i]);
        }
        int[] stack = new int[n];
        int cur = 0;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt() - 1;
            while (cur > 0) {
                int last = stack[cur - 1];
                if (edges[last].isEmpty()) {
                    --cur;
                    continue;
                }
                if (!edges[last].contains(x)) {
                    out.println("No");
                    return;
                }
                stack[cur++] = x;
                break;
            }
            if (cur == 0) {
                stack[cur++] = x;
            }
            for (int to : edges2[x]) {
                edges[to].remove(x);
            }
        }
        out.println("Yes");
    }
}
