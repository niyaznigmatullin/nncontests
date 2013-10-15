package lib.test.on2013_09.on2013_09_14_Codeforces_Round__200__Div__1_.D___Water_Tree;



import ru.ifmo.niyaz.DataStructures.MaxSegmentTree;
import ru.ifmo.niyaz.graphalgorithms.GraphUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {

    static int[][] edges;
    static int[] enter;
    static int[] exit;
    static int time;

    static void dfs(int v, int p) {
        enter[v] = time++;
        for (int i : edges[v]) {
            if (i == p) continue;
            dfs(i, v);
        }
        exit[v] = time;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] from = new int[n - 1];
        int[] to = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
        }
        edges = GraphUtils.getEdgesUndirectedUnweighted(n, from, to);
        time = 0;
        enter = new int[n];
        exit = new int[n];
        dfs(0, -1);
        MaxSegmentTree tree = new MaxSegmentTree(n);
        MyMultiSegmentTree tree2 = new MyMultiSegmentTree(n);
        for (int i = 0; i < n; i++) {
            tree2.set(0, n, -2);
        }
        for (int i = 0; i < n; i++) {
            tree.set(i, -1);
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int type = in.nextInt();
            int v = in.nextInt() - 1;
            if (type == 1) {
                tree2.set(enter[v], exit[v], i);
            } else if (type == 2) {
                tree.set(enter[v], i);
            } else {
                int max1 = tree.getMax(enter[v], exit[v]);
                int max2 = tree2.getMax(enter[v], enter[v] + 1);
                if (max1 > max2) {
                    out.println(0);
                } else {
                    out.println(1);
                }
            }
        }
    }

    static class MyMultiSegmentTree {
        private int[] max;
        private int[] set;
        private boolean[] toSet;
        final int n;

        public MyMultiSegmentTree(int n) {
            this.n = Integer.highestOneBit(n) << 1;
            max = new int[this.n << 1];
            set = new int[this.n << 1];
            toSet = new boolean[this.n << 1];
        }

        private void set(int node, int x) {
            set[node] = x;
            toSet[node] = true;
        }

        private void relax(int node) {
            if (!toSet[node]) {
                return;
            }
            max[node] = getMax(node);
                set((node << 1) | 1, set[node]);
                set((node << 1) + 2, set[node]);
                toSet[node] = false;
        }

        private int getMax(int node) {
            return toSet[node] ? set[node] : max[node];
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
            max[node] = Math.max(getMax((node << 1) | 1),
                    getMax((node << 1) + 2));
        }

        private int getMax(int node, int l, int r, int left, int right) {
            if (right <= l || r <= left) {
                return Integer.MIN_VALUE;
            }
            if (left <= l && r <= right) {
                return getMax(node);
            }
            relax(node);
            int m = (l + r) >> 1;
            return Math.max(getMax((node << 1) | 1, l, m, left, right),
                    getMax((node << 1) + 2, m, r, left, right));
        }

        public void set(int l, int r, int x) {
            if (l >= r) {
                return;
            }
            set(0, 0, n, l, r, x);
        }

        public int getMax(int l, int r) {
            if (l >= r) {
                return Integer.MAX_VALUE;
            }
            return getMax(0, 0, n, l, r);
        }

    }

}
