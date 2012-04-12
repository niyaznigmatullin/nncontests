package mypackage;

import arrayutils.ArrayUtils;
import com.sun.deploy.util.ArrayUtil;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class TaskA {

    static List<Integer>[] edges;
    static boolean[] was;

    static List<Integer> dfs(int v, int start, int p, List<Integer> cycle) {
        was[v] = true;
        cycle.add(v);
        for (int i : edges[v]) {
            if (i == p) {
                continue;
            }
            if (i == start) {
                return cycle;
            }
            if (was[i]) {
                continue;
            }
            List<Integer> got = dfs(i, start, v, cycle);
            if (got != null) {
                return got;
            }
        }
        cycle.remove(cycle.size() - 1);
        return null;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            edges[from].add(to);
            edges[to].add(from);
        }
        int start = in.nextInt() - 1;
        was = new boolean[n];
        List<Integer> cycle = new ArrayList<Integer>();
        cycle = dfs(start, start, -1, cycle);
        if (cycle == null) {
            out.println(-1);
        } else {
            out.println(cycle.size());
            int[] ans = ArrayUtils.toPrimitiveArray(cycle);
            for (int i = 0; i < ans.length; i++) {
                ++ans[i];
            }
            out.printArray(ans);
        }
    }
}
