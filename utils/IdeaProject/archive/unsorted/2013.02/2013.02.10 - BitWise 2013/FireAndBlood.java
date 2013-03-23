package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.graphalgorithms.KuhnMatchingGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class FireAndBlood {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        KuhnMatchingGraph g = new KuhnMatchingGraph(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = in.nextInt();
                if (x == 1)
                    g.addEdge(i, j);
            }
        }
        out.println(n - g.getMaximalMatching());
    }
}
