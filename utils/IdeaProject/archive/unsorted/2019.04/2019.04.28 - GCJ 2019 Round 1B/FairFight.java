package coding;

import ru.ifmo.niyaz.DataStructures.MultiSegmentTree;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class FairFight {

    long solve(int[] c, int[] d, int k) {
        int n = c.length;
        d = d.clone();
        for (int i = 0; i < n; i++) d[i] += k;
        int[] sc = new int[n];
        int[] sd = new int[n];
        int nc = 0;
        int nd = 0;
        MultiSegmentTree t = new MultiSegmentTree(n);
        long ans = 0;
        for (int i = 0; i < n; i++) {
            while (nc > 0 && c[sc[nc - 1]] <= c[i]) --nc;
            while (nd > 0 && d[sd[nd - 1]] <= d[i]) --nd;
            sc[nc++] = i;
            sd[nd++] = i;
            int prevC = nc > 1 ? sc[nc - 2] : -1;
            int prevD = nd > 1 ? sd[nd - 2] : -1;
            int from = Math.min(prevC, prevD);
            t.set(from + 1, i + 1, 0);
            if (prevC < prevD) {
                int l = -1;
                int r = nd;
                while (l < r - 1) {
                    int mid = (l + r) >> 1;
                    if (d[sd[mid]] < c[i]) {
                        r = mid;
                    } else {
                        l = mid;
                    }
                }
                int z = l < 0 ? 0 : sd[l] + 1;
                t.set(z, i + 1, 1);
            } else {
                int l = -1;
                int r = nc;
                while (l < r - 1) {
                    int mid = (l + r) >> 1;
                    if (c[sc[mid]] > d[i]) {
                        l = mid;
                    } else {
                        r = mid;
                    }
                }
                int to = l < 0 ? 0 : sc[l] + 1;
                if (prevD + 1 <= to) {
                    t.set(prevD + 1, to, 1);
                }
            }
            ans += t.getSum(0, n);
        }
        return ans;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] c = in.readIntArray(n);
        int[] d = in.readIntArray(n);
        out.println("Case #" + testNumber + ": " + ((long) n * (n + 1) / 2 - solve(c, d, k) - solve(d, c, k)));
    }
}
