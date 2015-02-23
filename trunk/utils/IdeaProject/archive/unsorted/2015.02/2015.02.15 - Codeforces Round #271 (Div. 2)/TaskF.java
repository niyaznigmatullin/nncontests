package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

public class TaskF {

    static class SegmentTree {
        int n;
        int[] g;
        int[] count;

        SegmentTree(int m) {
            n = Integer.highestOneBit(m) << 1;
            g = new int[2 * n];
            count = new int[2 * n];
            for (int i = 0; i < n; i++) {
                count[i + n] = 1;
                set(i, 0);
            }
        }

        void set(int x, int y) {
            x += n;
            g[x] = y;
            while (x > 1) {
                x >>= 1;
                int gg = MathUtils.gcd(g[x * 2], g[x * 2 + 1]);
                count[x] = 0;
                if (gg == g[x * 2]) count[x] += count[x * 2];
                if (gg == g[x * 2 + 1]) count[x] += count[x * 2 + 1];
                g[x] = gg;
            }
        }

        int getGcdAndCount(int l, int r) {
            --r;
            l += n;
            r += n;
            int retG = 0;
            int retC = 0;
            while (l <= r) {
                if ((l & 1) == 1) {
                    int ng = MathUtils.gcd(retG, g[l]);
                    int nc = 0;
                    if (ng == retG) nc += retC;
                    if (ng == g[l]) nc += count[l];
                    ++l;
                    retG = ng;
                    retC = nc;
                }
                if ((r & 1) == 0) {
                    int ng = MathUtils.gcd(retG, g[r]);
                    int nc = 0;
                    if (ng == retG) nc += retC;
                    if (ng == g[r]) nc += count[r];
                    --r;
                    retG = ng;
                    retC = nc;
                }
                l >>= 1;
                r >>= 1;
            }
            return retC;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        SegmentTree tree = new SegmentTree(n);
        for (int i = 0; i < n; i++) {
            tree.set(i, in.nextInt());
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int l = in.nextInt() - 1;
            int r = in.nextInt();
            out.println(r - l - tree.getGcdAndCount(l, r));
        }
    }
}
