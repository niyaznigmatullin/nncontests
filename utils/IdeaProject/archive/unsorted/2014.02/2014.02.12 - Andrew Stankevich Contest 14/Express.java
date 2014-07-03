package coding;

import ru.ifmo.niyaz.graphalgorithms.DinicGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class Express {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        DinicGraph g = new DinicGraph(2 * n + 2);
        int start1 = in.nextInt() - 1;
        int finish1 = in.nextInt() - 1;
        int start2 = in.nextInt() - 1;
        int finish2 = in.nextInt() - 1;
        if (start1 > finish1) {
            int t = start1;
            start1 = finish1;
            finish1 = t;
        }
        if (start2 > finish2) {
            int t = start2;
            start2 = finish2;
            finish2 = t;
        }
        int src = 2 * n;
        int tar = 2 * n + 1;
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            g.addEdge(from + n, to, 1);
        }
        for (int i = 0; i < n; i++) {
            g.addEdge(i, i + n, 1);
        }
        g.addEdge(src, start1, 1);
        g.addEdge(src, start2, 1);
        g.addEdge(finish1 + n, tar, 1);
        g.addEdge(finish2 + n, tar, 1);
        if (g.getMaxFlow(src, tar) != 2) {
            out.println("NO");
        } else {
            out.println("YES");
            List<List<DinicGraph.Edge>> f = g.decompose(src, tar);
            if (f.get(0).get(0).to != start1) {
                List<DinicGraph.Edge> t = f.get(0);
                f.set(0, f.get(1));
                f.set(1, t);
            }
            for (List<DinicGraph.Edge> e : f) {
                List<Integer> vs = new ArrayList<>();
                for (int i = 0; i + 1 < e.size(); i += 2) {
                    vs.add(e.get(i).to);
                }
                for (int i = 0; i < vs.size(); i++) {
                    if (i > 0) out.print(' ');
                    out.print(vs.get(i) + 1);
                }
                out.println();
            }
        }
    }
}
