package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class FuzzyAdd {

    /**
     * Created by IntelliJ IDEA.
     * User: niyaznigmatul
     * Date: 17.01.12
     * Time: 17:33
     * To change this template use File | Settings | File Templates.
     */
    public class MultiSegmentTree {
        private double[] sum;
        private double[] add;
        private double[] set;
        private double[] exp;
        private boolean[] toSet;
        private int[] count;
        final int n;

        public MultiSegmentTree(int n) {
            this.n = Integer.highestOneBit(n) << 1;
            sum = new double[this.n << 1];
            add = new double[this.n << 1];
            set = new double[this.n << 1];
            exp = new double[this.n << 1];
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

        private void add(int node, double x) {
            if (toSet[node]) {
                set[node] += x;
            } else {
                add[node] += x;
            }
        }

        private void set(int node, double x) {
            add[node] = 0;
            set[node] = x;
            toSet[node] = true;
        }

        private void relax(int node) {
            if (!toSet[node] && add[node] == 0) {
                return;
            }
            sum[node] = getSum(node);
            exp[node] = getExp(node);
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

        private double getSum(int node) {
            return toSet[node] ? set[node] * count(node) : sum[node]
                    + add[node] * count(node);
        }

        private double getExp(int node) {
            return toSet[node] ? set[node] * count2(node) : exp[node]
                    + add[node] * count2(node);
        }

        private int count(int node) {
            return count[node];
        }

        private double count2(int node) {
            return .5 * count[node] * (count[node] - 1);
        }

        private void add(int node, int l, int r, int left, int right, double x) {
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
            gather(node);
        }

        private void set(int node, int l, int r, int left, int right, double x) {
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
            gather(node);
        }

        private void gather(int node) {
            sum[node] = getSum((node << 1) | 1) + getSum((node << 1) + 2);
            exp[node] = getExp((node << 1) | 1) + getExp((node << 1) + 2) + getSum((node << 1) + 2) * count((node << 1) | 1);
        }


        private double getSum(int node, int l, int r, int left, int right) {
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

        double gs;

        private double getExp(int node, int l, int r, int left, int right) {
            if (right <= l || r <= left) {
                gs = 0;
                return 0;
            }
            if (left <= l && r <= right) {
                gs = getSum(node);
                return getExp(node);
            }
            relax(node);
            int m = (l + r) >> 1;
            int intersection = Math.min(right, m) - Math.max(l, left);
            if (intersection < 0) intersection = 0;
            double exp1 =  getExp((node << 1) | 1, l, m, left, right);
            double s1 = gs;
            double exp2 = getExp((node << 1) + 2, m, r, left, right);
            double s2 = gs;
            gs = s1 + s2;
            return exp1 + exp2 + s2 * intersection;
        }

        public void set(int l, int r, double x) {
            if (l >= r) {
                return;
            }
            set(0, 0, n, l, r, x);
        }

        public void add(int l, int r, double x) {
            if (l >= r) {
                return;
            }
            add(0, 0, n, l, r, x);
        }

        public double getSum(int l, int r) {
            if (l >= r) {
                return 0;
            }
            return getSum(0, 0, n, l, r);
        }

        public double getExp(int l, int r) {
            if (l >= r) {
                return 0;
            }
            return getExp(0, 0, n, l, r);
        }

    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        final int M = 1000000;
        MultiSegmentTree tree = new MultiSegmentTree(M);
        tree.set(0, 1, 1);
        int n = in.nextInt();
        int curStart = 0;
        for (int i = 0; i < n; i++) {
            int v = in.nextInt();
            int add = (M - v);
            if (curStart + add < M) {
                double s1 = tree.getSum(curStart + add, M);
                double s2 = tree.getSum(0, curStart);
                tree.set(curStart + add, M, 0);
                tree.set(0, curStart, 0);
                s1 += s2;
                tree.add(0, M, s1 / M);
            } else {
                int start = (curStart + add - M);
                double s = tree.getSum(start, curStart);
                tree.set(start, curStart, 0);
                tree.add(0, M, s / M);
            }
            curStart = (curStart + M - v) % M;
            double exp1 = tree.getExp(curStart, M);
            double exp2 = tree.getExp(0, curStart);
            double exp = exp1 + exp2 + (M - curStart) * tree.getSum(0, curStart);
            out.println(exp);
        }

    }
}
