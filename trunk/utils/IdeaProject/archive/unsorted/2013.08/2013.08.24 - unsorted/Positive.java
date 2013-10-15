package coding;

import ru.ifmo.niyaz.DataStructures.MultiSegmentTree;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Positive {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[2 * n];
        for (int i = 0; i < n; i++) {
            a[i] = a[i + n] = in.nextInt();
        }
        for (int i = 1; i < 2 * n; i++) {
            a[i] += a[i - 1];
        }
        MultiSegmentTree tree = new MultiSegmentTree(2 * n);
        for (int i = 0; i < 2 * n; i++) {
            tree.set(i, i + 1, a[i]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (tree.getMin(i, i + n) > 0) {
                ++ans;
            }
            int cur = tree.getMin(i, i + 1);
            tree.add(i + 1, 2 * n, -cur);
        }
        out.println(ans);
    }

    public class MultiSegmentTree {
        private int[] min;
        private int[] add;
        private int[] set;
        private boolean[] toSet;
        private int[] count;
        final int n;

        public MultiSegmentTree(int n) {
            this.n = Integer.highestOneBit(n) << 1;
            min = new int[this.n << 1];
            add = new int[this.n << 1];
            set = new int[this.n << 1];
            count = new int[this.n << 1];
            toSet = new boolean[this.n << 1];
            count(0, 0, this.n);
        }

        private int count(int node, int l, int r) {
            if (l == r - 1) {
                count[node] = 1;
                return 1;
            }
            int m = (l + r) >> 1;
            return count[node] = count((node << 1) | 1, l, m)
                    + count((node << 1) + 2, m, r);
        }

        private void add(int node, int x) {
            if (toSet[node]) {
                set[node] += x;
            } else {
                add[node] += x;
            }
        }

        private void set(int node, int x) {
            add[node] = 0;
            set[node] = x;
            toSet[node] = true;
        }

        private void relax(int node) {
            if (!toSet[node] && add[node] == 0) {
                return;
            }
            min[node] = getMin(node);
            if (toSet[node]) {
                set((node << 1) | 1, set[node]);
                set((node << 1) + 2, set[node]);
                toSet[node] = false;
            } else {
                add((node << 1) | 1, add[node]);
                add((node << 1) + 2, add[node]);
                add[node] = 0;
            }
        }

        private long count(int node) {
            return count[node];
        }

        private int getMin(int node) {
            return toSet[node] ? set[node] : min[node] + add[node];
        }

        private void add(int node, int l, int r, int left, int right, int x) {
            if (right <= l || r <= left) {
                return;
            }
            if (left <= l && r <= right) {
                add(node, x);
                return;
            }
            relax(node);
            int m = (l + r) >> 1;
            add((node << 1) | 1, l, m, left, right, x);
            add((node << 1) + 2, m, r, left, right, x);
            min[node] = Math.min(getMin((node << 1) | 1),
                    getMin((node << 1) + 2));
        }

        private void set(int node, int l, int r, int left, int right, int x) {
            if (right <= l || r <= left) {
                return;
            }
            if (left <= l && r <= right) {
                set(node, x);
                return;
            }
            relax(node);
            int m = (l + r) >> 1;
            set((node << 1) | 1, l, m, left, right, x);
            set((node << 1) + 2, m, r, left, right, x);
            min[node] = Math.min(getMin((node << 1) | 1),
                    getMin((node << 1) + 2));
        }

        private int getMin(int node, int l, int r, int left, int right) {
            if (right <= l || r <= left) {
                return Integer.MAX_VALUE;
            }
            if (left <= l && r <= right) {
                return getMin(node);
            }
            relax(node);
            int m = (l + r) >> 1;
            return Math.min(getMin((node << 1) | 1, l, m, left, right),
                    getMin((node << 1) + 2, m, r, left, right));
        }

        public void set(int l, int r, int x) {
            if (l >= r) {
                return;
            }
            set(0, 0, n, l, r, x);
        }

        public void add(int l, int r, int x) {
            if (l >= r) {
                return;
            }
            add(0, 0, n, l, r, x);
        }

        public int getMin(int l, int r) {
            if (l >= r) {
                return Integer.MAX_VALUE;
            }
            return getMin(0, 0, n, l, r);
        }
    }

}
