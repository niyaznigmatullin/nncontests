package coding;

import ru.ifmo.niyaz.graphalgorithms.KuhnMatchingGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int m = in.nextInt();
        int[] b = in.readIntArray(m);
        KuhnMatchingGraph g = new KuhnMatchingGraph(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (Math.abs(a[i] - b[j]) < 2) {
                    g.addEdge(i, j);
                }
            }
        }
        out.println(g.getMaximalMatching());
    }
}
