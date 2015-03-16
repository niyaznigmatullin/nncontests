package coding;

import ru.ifmo.niyaz.graphalgorithms.KuhnMatchingGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] from = new int[m];
        int[] to = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            KuhnMatchingGraph g = new KuhnMatchingGraph(n, n);
            int count = 0;
            for (int j = 0; j < m; j++) {
                if (from[j] != i && to[j] != i) {
                    g.addEdge(from[j], to[j]);
                    ++count;
                }
            }
            int cur = (n - 1 + count) - 2 * g.getMaximalMatching();
            boolean[] a = new boolean[n];
            boolean[] b = new boolean[n];
            for (int j = 0; j < m; j++) {
                if (from[j] == i) {
                    a[to[j]] = true;
                }
                if (to[j] == i) {
                    b[from[j]] = true;
                }
            }
            for (int j = 0; j < n; j++) {
                if (j == i) {
                    if (!a[j]) ++cur;
                } else {
                    if (!a[j]) ++cur;
                    if (!b[j]) ++cur;
                }
            }
            ans = Math.min(ans, cur);
        }
        out.println(ans);
    }
}
