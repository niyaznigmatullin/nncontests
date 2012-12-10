package mypackage;

import arrayutils.ArrayUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int m = in.nextInt();
        Query[] q = new Query[m];
        int[] x = new int[m];
        int[] y = new int[m];
        for (int i = 0; i < m; i++) {
            String s = in.next();
            int type = s.equals("add") ? 1 : s.equals("remove") ? 2 : 3;
            q[i] = new Query(type, in.nextInt(), in.nextInt());
            x[i] = q[i].x;
            y[i] = q[i].y;
        }
        x = ArrayUtils.sortAndUnique(x);
        y = ArrayUtils.sortAndUnique(y);
        for (int i = 0; i < m; i++) {
            q[i].x = Arrays.binarySearch(x, q[i].x);
            q[i].y = Arrays.binarySearch(y, q[i].y);
        }
        NavigableSet<Integer>[] sets = new NavigableSet[x.length];
        for (int i = 0; i < sets.length; i++) {
            sets[i] = new TreeSet<Integer>();
        }
        count = new int[x.length];
        n = Integer.highestOneBit(x.length) << 1;
        tree = new int[2 * n];
        Arrays.fill(tree, Integer.MIN_VALUE);
        for (Query e : q) {
            if (e.type == 1) {
                sets[e.x].add(e.y);
                add(e.x, sets[e.x].last());
            } else if (e.type == 2) {
                sets[e.x].remove(e.y);
                add(e.x, sets[e.x].isEmpty() ? Integer.MIN_VALUE : sets[e.x].last());
            } else {
                int curX = getMin(e.x + 1, x.length, e.y + 1);
                if (curX < 0) {
                    out.println(-1);
                } else {
                    out.println(x[curX] + " " + y[sets[curX].higher(e.y)]);
                }
            }
        }
    }

    static int[] tree;
    static int[] count;
    static int n;

    static void add(int x, int y) {
        x += n;
        tree[x] = y;
        while (x > 1) {
            x >>= 1;
            tree[x] = Math.max(tree[x * 2], tree[x * 2 + 1]);
        }
    }

    static int getFirst(int v, int l, int r, int left, int right, int x) {
        if (r <= left || right <= l || tree[v] < x) {
            return -1;
        }
        if (l == r - 1) {
            return l;
        }
        int mid = l + r >> 1;
        int ans = getFirst(v * 2, l, mid, left, right, x);
        if (ans >= 0) {
            return ans;
        }
        return getFirst(v * 2 + 1, mid, r, left, right, x);
    }

    static int getMin(int l, int r, int y) {
        return getFirst(1, 0, n, l, r, y);
//        --r;
//        int ret = Integer.MAX_VALUE;
//        l += n;
//        r += n;
//        while (l <= r) {
//            if ((l & 1) == 1) {
//                ret = Math.min(ret, tree[l++]);
//            }
//            if ((r & 1) == 0) {
//                ret = Math.min(ret, tree[r--]);
//            }
//            l >>= 1;
//            r >>= 1;
//        }
//        return ret;
    }

    static class Query {
        int type;
        int x;
        int y;

        Query(int type, int x, int y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }
    }
}
