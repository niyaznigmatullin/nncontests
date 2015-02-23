package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskC {

    static List<Integer>[] edges;
    static List<Integer>[] revEdges;
    static List<Integer> topSort;
    static boolean[] was;
    static int[] color;

    static void dfs(int v) {
        was[v] = true;
        for (int i = 0; i < edges[v].size(); i++) {
            int to = edges[v].get(i);
            if (!was[to]) dfs(to);
        }
        topSort.add(v);
    }

    static void dfs2(int v, int c) {
        color[v] = c;
        for (int i = 0; i < revEdges[v].size(); i++) {
            int to = revEdges[v].get(i);
            if (color[to] < 0) dfs2(to, c);
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] v = in.readIntArray(n);
        edges = new List[n];
        revEdges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
            revEdges[i] = new ArrayList<>();
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            edges[from].add(to);
            revEdges[to].add(from);
        }
        was = new boolean[n];
        topSort = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!was[i]) dfs(i);
        }
        Collections.reverse(topSort);
        color = new int[n];
        int c = 0;
        Arrays.fill(color, -1);
        for (int i : topSort) {
            if (color[i] < 0) dfs2(i, c++);
        }
        int[] minimum = new int[c];
        Arrays.fill(minimum, Integer.MAX_VALUE);
        int[] count = new int[c];
        for (int i = 0; i < n; i++) {
            int where = color[i];
            if (minimum[where] > v[i]) {
                minimum[where] = v[i];
                count[where] = 1;
            } else if (minimum[where] == v[i]) {
                ++count[where];
            }
        }
        int ans = 1;
        long sum = 0;
        for (int i = 0; i < c; i++) {
            sum += minimum[i];
            ans = (int) ((long) ans * count[i] % 1000000007);
        }
        out.println(sum + " " + ans);
    }
}
