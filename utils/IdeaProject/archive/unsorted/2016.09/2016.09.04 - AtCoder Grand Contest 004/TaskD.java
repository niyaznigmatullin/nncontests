package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class TaskD {
    static List<Integer>[] edges;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        k = in.nextInt();
        int[] a = in.readIntArray(n);
        for (int i = 0; i < n; i++) --a[i];
        int ans = 0;
        if (a[0] != 0) {
            a[0] = 0;
            ans++;
        }
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            edges[a[i]].add(i);
        }
        kill = new int[n];
        left = new int[n];
        dfs(0);
        out.println(kill[0] + ans);
    }

    static int[] kill, left;
    static int k;

    static void dfs(int v) {
        kill[v] = 0;
        left[v] = 0;
        for (int it = 0; it < edges[v].size(); it++) {
            int to = edges[v].get(it);
            dfs(to);
            kill[v] += kill[to];
            if (left[to] == k - 1 && v != 0) {
                kill[v]++;
            } else {
                left[v] = Math.max(left[v], left[to] + 1);
            }
        }
    }
}
