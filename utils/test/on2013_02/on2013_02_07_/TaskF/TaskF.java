package lib.test.on2013_02.on2013_02_07_.TaskF;



import ru.ifmo.niyaz.graphalgorithms.KuhnMatchingGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.StringTokenizer;

public class TaskF {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = Integer.parseInt(in.nextLine().trim());
        String[] name = new String[n];
        KuhnMatchingGraph g = new KuhnMatchingGraph(n, n);
        for (int i = 0; i < n; i++) {
            name[i] = in.nextLine().trim();
            String line = in.nextLine();
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                int x = Integer.parseInt(st.nextToken());
                g.addEdge(i, x - 1);
            }
        }
        int z = g.getMaximalMatching();
        if (z != n) {
            while (true);
        }
        String[] ans = new String[n];
        int[] p = g.getPaired1();
        for (int i = 0; i < n; i++) {
            ans[p[i]] = name[i];
        }
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                out.print(' ');
            }
            out.print(ans[i]);
        }
        out.println();
    }
}
