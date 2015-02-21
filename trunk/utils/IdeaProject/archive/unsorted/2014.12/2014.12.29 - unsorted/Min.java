package coding;

import ru.ifmo.niyaz.DataStructures.MinSegmentTree;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Min {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        in.nextInt();
        MinSegmentTree tree = new MinSegmentTree(n);
        for (int i = 0; i < n; i++) {
            tree.set(i, in.nextInt());
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int l = in.nextInt() - 1;
            int r = in.nextInt();
            out.println(tree.getMinID(l, r) + 1);
        }
    }
}
