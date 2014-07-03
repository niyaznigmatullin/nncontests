package coding;

import ru.ifmo.niyaz.DataStructures.Fenwick;
import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class E {


    int[] min;
    int n;
    Fenwick fPos;
    Fenwick f;
    boolean[] removed;
    long current;

    void init(int n) {
        this.n = Integer.highestOneBit(n) << 1;
        min = new int[this.n * 2];
        Arrays.fill(min, Integer.MAX_VALUE);
    }

    void set(int x, int y) {
        x += n;
        min[x] = y;
        while (x > 1) {
            x >>= 1;
            min[x] = Math.min(min[x * 2], min[x * 2 + 1]);
        }
    }

    void removeAll(int v, int l, int r, int left, int maximal) {
        if (min[v] > maximal)
            return;
        if (r <= left)
            return;
        if (l == r - 1) {
            {
                int count1 = fPos.getSum(l);
                int count2 = f.getSum(min[v] - 1);
                if (count2 > count1) {
                    current -= count2 - count1;
                }
            }
            {
                int count1 = fPos.getSum(l);
                int count2 = f.getSum(min[v]);
                if (count1 > count2) {
                    current -= count1 - count2;
                }
            }
            removed[l] = true;
            fPos.add(l, 1);
            f.add(min[v], 1);
            min[v] = Integer.MAX_VALUE;
            return;
        }
        int mid = l + r >> 1;
        removeAll(v * 2, l, mid, left, maximal);
        removeAll(v * 2 + 1, mid, r, left, maximal);
        min[v] = Math.min(min[v * 2], min[v * 2 + 1]);
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = in.readIntArray(n);
        init(n);
        int maxIQ = 0;
        for (int i = 0; i < n; i++) {
            set(i, a[i]);
            maxIQ = Math.max(maxIQ, a[i]);
        }
        removed = new boolean[n];
        ++maxIQ;
        fPos = new Fenwick(n);
        f = new Fenwick(maxIQ);
        current = ArrayUtils.countNumberOfInversions(a.clone());
        out.println(current);
        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            if (removed[x]) {
                out.println(current);
                continue;
            }
            removeAll(1, 0, this.n, x, a[x]);
            out.println(current);
        }
    }
}
