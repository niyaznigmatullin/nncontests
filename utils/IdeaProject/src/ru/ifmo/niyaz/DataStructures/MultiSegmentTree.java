package ru.ifmo.niyaz.DataStructures;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 17.01.12
 * Time: 17:33
 * To change this template use File | Settings | File Templates.
 */
public class MultiSegmentTree {
    private long[] min;
    private long[] max;
    private long[] sum;
    private long[] add;
    private long[] set;
    private boolean[] toSet;
    private int[] count;
    final int n;

    public MultiSegmentTree(int n) {
        this.n = Integer.highestOneBit(n) << 1;
        min = new long[this.n << 1];
        max = new long[this.n << 1];
        sum = new long[this.n << 1];
        add = new long[this.n << 1];
        set = new long[this.n << 1];
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

    private void add(int node, long x) {
        if (toSet[node]) {
            set[node] += x;
        } else {
            add[node] += x;
        }
    }

    private void set(int node, long x) {
        add[node] = 0;
        set[node] = x;
        toSet[node] = true;
    }

    private void relax(int node) {
        if (!toSet[node] && add[node] == 0) {
            return;
        }
        min[node] = getMin(node);
        max[node] = getMax(node);
        sum[node] = getSum(node);
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

    private long getSum(int node) {
        return toSet[node] ? set[node] * count(node) : sum[node]
                + add[node] * count(node);
    }

    private long count(int node) {
        return count[node];
    }

    private long getMax(int node) {
        return toSet[node] ? set[node] : max[node] + add[node];
    }

    private long getMin(int node) {
        return toSet[node] ? set[node] : min[node] + add[node];
    }

    private void add(int node, int l, int r, int left, int right, long x) {
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
        max[node] = Math.max(getMax((node << 1) | 1),
                getMax((node << 1) + 2));
        sum[node] = getSum((node << 1) | 1) + getSum((node << 1) + 2);
    }

    private void set(int node, int l, int r, int left, int right, long x) {
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
        max[node] = Math.max(getMax((node << 1) | 1),
                getMax((node << 1) + 2));
        sum[node] = getSum((node << 1) | 1) + getSum((node << 1) + 2);
    }

    private long getMin(int node, int l, int r, int left, int right) {
        if (right <= l || r <= left) {
            return Long.MAX_VALUE;
        }
        if (left <= l && r <= right) {
            return getMin(node);
        }
        relax(node);
        int m = (l + r) >> 1;
        return Math.min(getMin((node << 1) | 1, l, m, left, right),
                getMin((node << 1) + 2, m, r, left, right));
    }

    private long getMax(int node, int l, int r, int left, int right) {
        if (right <= l || r <= left) {
            return Long.MIN_VALUE;
        }
        if (left <= l && r <= right) {
            return getMax(node);
        }
        relax(node);
        int m = (l + r) >> 1;
        return Math.max(getMax((node << 1) | 1, l, m, left, right),
                getMax((node << 1) + 2, m, r, left, right));
    }

    private long getSum(int node, int l, int r, int left, int right) {
        if (right <= l || r <= left) {
            return 0;
        }
        if (left <= l && r <= right) {
            return getSum(node);
        }
        relax(node);
        int m = (l + r) >> 1;
        return getSum((node << 1) | 1, l, m, left, right)
                + getSum((node << 1) + 2, m, r, left, right);
    }

    public void set(int l, int r, long x) {
        if (l >= r) {
            return;
        }
        set(0, 0, n, l, r, x);
    }

    public void add(int l, int r, long x) {
        if (l >= r) {
            return;
        }
        add(0, 0, n, l, r, x);
    }

    public long getSum(int l, int r) {
        if (l >= r) {
            return 0;
        }
        return getSum(0, 0, n, l, r);
    }

    public long getMax(int l, int r) {
        if (l >= r) {
            return Long.MAX_VALUE;
        }
        return getMax(0, 0, n, l, r);
    }

    public long getMin(int l, int r) {
        if (l >= r) {
            return Long.MAX_VALUE;
        }
        return getMin(0, 0, n, l, r);
    }
}
