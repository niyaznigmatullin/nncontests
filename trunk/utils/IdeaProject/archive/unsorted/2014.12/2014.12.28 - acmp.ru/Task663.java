package coding;

import ru.ifmo.niyaz.graphalgorithms.MinCostMaxFlowGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Task663 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        String[] name = new String[n];
        for (int i = 0; i < n; i++) {
            name[i] = in.next();
        }
        String[] pName = new String[m];
        for (int i = 0; i < m; i++) {
            pName[i] = in.next();
        }
        int q = in.nextInt();
        MinCostMaxFlowGraph g = new MinCostMaxFlowGraph(q + n * m + 2);
        int src = q + n * m;
        int tar = src + 1;
        String[] statuses = new String[q];
        for (int i = 0; i < q; i++) {
            statuses[i] = in.next();
        }
        int k = in.nextInt();
        boolean[] was = new boolean[n];
        for (int i = 0; i < k; i++) {
            was[in.nextInt() - 1] = true;
        }
        for (int i = 0; i < q; i++) {
            String[] r = statuses[i].split("-");
            for (int a = 0; a < n; a++) {
                for (int b = 0; b < m; b++) {
                    if (matches(r[0], name[a]) && matches(r[1], pName[b])) {
                        g.addEdge(i, a * m + b + q, 1, was[a] ? -Integer.parseInt(r[2]) : 0);
                    }
                }
            }
            g.addEdge(src, i, 1, 0);
        }
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < m; b++) {
                g.addEdge(a * m + b + q, tar, 1, 0);
            }
        }
        long[] f = g.getMinCostMaxFlow(src, tar);
        out.println(-f[1]);
    }

    static boolean matches(String s, String t) {
        if (s.length() != t.length()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i) && s.charAt(i) != '?') {
                return false;
            }
        }
        return true;
    }
}
