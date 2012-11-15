package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.BitSet;

public class TaskH {

    static BitSet[] tr;
    static int shift;

    static void init(int n) {
        shift = Integer.highestOneBit(n) << 1;
        tr = new BitSet[shift * 2];
        for (int i = 0; i < shift * 2; i++) {
            tr[i] = new BitSet();
        }
    }

    static void add(int x, int y) {
        x += shift;
        while (x > 0) {
            tr[x].set(y);
            x >>= 1;
        }
    }

    static BitSet get(int l, int r) {
        r--;
        l += shift;
        r += shift;
        BitSet ret = new BitSet();
        while (l <= r) {
            if ((l & 1) == 1) {
                ret.or(tr[l++]);
            }
            if ((r & 1) == 0) {
                ret.or(tr[r--]);
            }
            l >>= 1;
            r >>= 1;
        }
        return ret;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        init(n);
        for (int i = 0; i < n; i++) {
            add(i, in.nextInt() - 1);
        }
        for (int i = 0; i < k; i++) {
            int left = in.nextInt();
            int right = in.nextInt();
            out.println(get(left - 1, right).cardinality());
        }
    }
}
