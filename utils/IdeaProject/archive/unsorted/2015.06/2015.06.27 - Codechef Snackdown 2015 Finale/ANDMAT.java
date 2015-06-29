package coding;

import ru.ifmo.niyaz.graphalgorithms.KuhnMatchingGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class ANDMAT {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        long[][] a = in.readLong2DArray(n, n);
        KuhnMatchingGraph g = new KuhnMatchingGraph(n, n);
        long prefix = 0;
        for (int bit = 59; bit >= 0; bit--) {
            g.clearEdges();
            long nextPrefix = prefix | (1L << bit);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if ((a[i][j] & nextPrefix) == nextPrefix) {
                        g.addEdge(i, j);
                    }
                }
            }
            if (g.getMaximalMatching() == n) {
                prefix = nextPrefix;
            }
        }
        out.println(prefix);
    }
}
