package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class TaskA {
    static List<Integer>[] edges;
    static int[] ans;

    static void dfs(int v, int pv, int c, int cp) {
        ans[v] = c;
        int cur = 0;
        for (int i = 0; i < edges[v].size(); i++) {
            while (cur == c || cur == cp) ++cur;
            int to = edges[v].get(i);
            if (to == pv) continue;
            dfs(to, v, cur, c);
            ++cur;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        int[] deg = new int[n];
        for (int i = 0; i + 1 < n; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            edges[a].add(b);
            edges[b].add(a);
            deg[a]++;
            deg[b]++;
        }
        ans = new int[n];
        int mx = 0;
        for (int i = 0; i < n; i++) mx = Math.max(mx, deg[i] + 1);
        dfs(0, -1, 0, -1);
        out.println(mx);
        for (int i = 0; i < n; i++) {
            if (i > 0) out.print(' ');
            out.print(ans[i] + 1);
        }
        out.println();
    }
}
