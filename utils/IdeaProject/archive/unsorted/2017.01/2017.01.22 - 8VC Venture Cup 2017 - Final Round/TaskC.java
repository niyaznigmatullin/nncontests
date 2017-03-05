package coding;

import ru.ifmo.niyaz.DataStructures.MultiSegmentTree;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int m = in.nextInt();
        MultiSegmentTree t = new MultiSegmentTree(m);
        int[] value = new int[m];
        for (int i = 0; i < m; i++) {
            int p = m - in.nextInt();
            int type = in.nextInt();
            if (type == 0) {
                t.add(p, m, -1);
            } else {
                t.add(p, m, 1);
                value[p] = in.nextInt();
            }
            int l = -1;
            int r = m;
            while (l < r - 1) {
                int mid = (l + r) >> 1;
                if (t.getMax(0, mid + 1) > 0) {
                    r = mid;
                } else {
                    l = mid;
                }
            }
            if (r == m) {
                out.println(-1);
            } else {
                out.println(value[r]);
            }
        }
    }
}
