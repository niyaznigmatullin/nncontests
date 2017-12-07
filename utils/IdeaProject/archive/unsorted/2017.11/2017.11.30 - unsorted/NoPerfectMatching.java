package coding;

import ru.ifmo.niyaz.graphalgorithms.DinicGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class NoPerfectMatching {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n1 = in.nextInt();
        if (n1 == 0) {
            throw new UnknownError();
        }
        int n2 = in.nextInt();
        int m = in.nextInt();
        DinicGraph g = new DinicGraph(n1 + n2 + 2);
        int src = n1 + n2;
        int tar = src + 1;
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            g.addEdge(to, from + n2, 1);
        }
        for (int i = 0; i < n2; i++) {
            g.addEdge(src, i, 1);
        }
        for (int i = 0; i < n1; i++) {
            g.addEdge(i + n2, tar, 1);
        }
        if (g.getMaxFlow(src, tar) == n2) {
            out.println(0);
        } else {
            boolean[] cut = g.getCut(src, tar);
            int[] ans = new int[n2];
            int ac = 0;
            for (int i = 0; i < n2; i++) {
                if (cut[i]) {
                    ans[ac++] = i;
                }
            }
            out.println(ac);
            for (int i = 0; i < ac; i++) {
                if (i > 0) out.print(' ');
                out.print(ans[i] + 1);
            }
            out.println();
        }
    }
}
