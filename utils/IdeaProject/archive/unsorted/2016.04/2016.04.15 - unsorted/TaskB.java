package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskB {

    static class Edge {
        int from;
        int to;
        int c;

        public Edge(int from, int to, int c) {
            this.from = from;
            this.to = to;
            this.c = c;
        }
    }

    static List<Edge>[] edges;
    static int[] cv;
    static List<Integer>[] tocolor;

    static boolean dfs(int v, int c) {
        cv[v] = c;
        tocolor[c].add(v);
        for (int i = 0; i < edges[v].size(); i++) {
            Edge e = edges[v].get(i);
            if (cv[e.to] < 0) {
                dfs(e.to, c ^ e.c);
            }
            if (cv[e.to] != (c ^ e.c)) {
                return false;
            }
        }
        return true;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] from = new int[m];
        int[] to = new int[m];
        int[] color = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
            color[i] = in.next().charAt(0) == 'B' ? 0 : 1;
        }
        List<Integer> answer = null;
        tocolor = new List[2];
        tocolor[0] = new ArrayList<>();
        tocolor[1] = new ArrayList<>();
        for (int c = 0; c < 2; c++) {
            edges = new List[n];
            for (int i = 0; i < n; i++) {
                edges[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; i++) {
                edges[from[i]].add(new Edge(from[i], to[i], color[i] == c ? 1 : 0));
                edges[to[i]].add(new Edge(to[i], from[i], color[i] == c ? 1 : 0));
            }
            cv = new int[n];
            Arrays.fill(cv, -1);
            List<Integer> cur = new ArrayList<>();
            boolean ok = true;
            for (int i = 0; i < n; i++) {
                if (cv[i] >= 0) continue;
                tocolor[0].clear();
                tocolor[1].clear();
                if (!dfs(i, 0)) {
                    ok = false;
                    break;
                }
                if (tocolor[0].size() > tocolor[1].size()) {
                    cur.addAll(tocolor[1]);
                } else {
                    cur.addAll(tocolor[0]);
                }
            }
            if (!ok) continue;
            if (answer == null || cur.size() < answer.size()) {
                answer = cur;
            }
        }
        if (answer == null) {
            out.println(-1);
            return;
        }
        out.println(answer.size());
        Collections.sort(answer);
        int[] z = ArrayUtils.toPrimitiveArrayInteger(answer);
        for (int i = 0; i < z.length; i++) {
            z[i]++;
        }
        out.printArray(z);
    }
}
