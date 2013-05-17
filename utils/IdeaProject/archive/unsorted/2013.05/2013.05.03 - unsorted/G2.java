package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class G2 {
    static class SegmentTree {
        boolean[] needSet;
        double[] addA;
        double[] addB;
        double[] sum;
        int[] count;
        int n;

        SegmentTree(int[] count) {
            this.n = Integer.highestOneBit(count.length) << 1;
            int all = this.n * 2 + 1;
            needSet = new boolean[all];
            addA = new double[all];
            addB = new double[all];
            sum = new double[all];
            this.count = new int[all];
            for (int i = 0; i < count.length; i++) {
                this.count[i + this.n] = count[i];
            }
            for (int i = this.n - 1; i >= 1; i--) {
                this.count[i] = this.count[i * 2] + this.count[i * 2 + 1];
            }
        }

        double getSum(int l, int r) {
            return getSum(1, 0, n, l, r);
        }

        static double sum(double x) {
            return x * (x - 1) * .5;
        }

        double getSum(int v) {
            if (needSet[v]) return 0;
            return sum[v] + addA[v] * count[v] + addB[v] * sum(count[v]);
        }

        void relax(int v) {
            if (needSet[v]) {
                sum[v] = getSum(v);
                setZero(v * 2);
                setZero(v * 2 + 1);
                needSet[v] = false;
                return;
            }
            if (addA[v] == 0 && addB[v] == 0) {
                return;
            }
            sum[v] = getSum(v);
            addValue(v * 2, addA[v], addB[v]);
            addValue(v * 2 + 1, addA[v] + addB[v] * count[v * 2], addB[v]);
            addA[v] = 0;
            addB[v] = 0;
        }

        double getSum(int v, int left, int right, int l, int r) {
            if (right <= l || r <= left) return 0;
            if (l <= left && right <= r) {
                return getSum(v);
            }
            relax(v);
            int mid = (left + right) >> 1;
            return getSum(v * 2, left, mid, l, r) + getSum(v * 2 + 1, mid, right, l, r);
        }

        void setZero(int left, int right) {
            setZero(1, 0, n, left, right);
        }

        void setZero(int v) {
            addA[v] = addB[v] = 0;
            needSet[v] = true;
        }

        void addValue(int v, double dA, double dB) {
            if (needSet[v]) {
                sum[v] = 0;
                needSet[v] = false;
                if (v < n) {
                    setZero(v * 2);
                    setZero(v * 2 + 1);
                }
            }
            addA[v] += dA;
            addB[v] += dB;
        }

        void setZero(int v, int left, int right, int l, int r) {
            if (right <= l || r <= left) {
                return;
            }
            if (l <= left && right <= r) {
                setZero(v);
                return;
            }
            relax(v);
            int mid = (left + right) >> 1;
            setZero(v * 2, left, mid, l, r);
            setZero(v * 2 + 1, mid, right, l, r);
            sum[v] = getSum(v * 2) + getSum(v * 2 + 1);
        }

        void addValue(int l, int r, double dA, double dB) {
            addValue(1, 0, n, l, r, dA, dB);
        }

        int addValue(int v, int left, int right, int l, int r, double dA, double dB) {
            if (right <= l || r <= left) {
                return 0;
            }
            if (l <= left && right <= r) {
                addValue(v, dA, dB);
                return count[v];
            }
            relax(v);
            int mid = (left + right) >> 1;
            int added = addValue(v * 2, left, mid, l, r, dA, dB);
            int added2 = addValue(v * 2 + 1, mid, right, l, r, dA + dB * added, dB);
            sum[v] = getSum(v * 2) + getSum(v * 2 + 1);
            return added + added2;
        }

    }

    static class Query {
        int time;
        int type;
        int a;
        int b;

        Query(int time, int type, int a, int b) {
            this.time = time;
            this.type = type;
            this.a = a;
            this.b = b;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int p = in.nextInt();
        int m = in.nextInt();
        Query[] qs = new Query[m];
        int[] z = new int[4 * m + 10];
        int cn = 0;
        for (int i = 0; i < m; i++) {
            int time = in.nextInt();
            int type = in.next().equals("save") ? 0 : 1;
            int a = in.nextInt();
            int b = in.nextInt();
            qs[i] = new Query(time, type, a, b);
            if (type == 0) {
                z[cn++] = a - 1;
                z[cn++] = b;
            } else {
                --a;
                z[cn++] = a - b + 1;
                z[cn++] = a;
                z[cn++] = a + b;
            }
        }
        z[cn++] = 0;
        z[cn++] = n;
        z = Arrays.copyOf(z, cn);
        z = ArrayUtils.sortAndUnique(z);
        int[] cnt = new int[z.length - 1];
        {
            for (int i = 0; i < z.length - 1; i++) {
                cnt[i] = z[i + 1] - z[i];
            }
        }
        SegmentTree tree = new SegmentTree(cnt);
        int last = 0;
        double all = 0;
        for (int i = 0; i < m; i++) {
            int time = qs[i].time;
            tree.addValue(0, cnt.length, 1. * p * (time - last), 0);
            last = time;
            if (qs[i].type == 0) {
                int l = qs[i].a - 1;
                int r = qs[i].b;
                l = Arrays.binarySearch(z, l);
                r = Arrays.binarySearch(z, r);
                double sum = tree.getSum(l, r);
                all += sum;
                out.println(all);
                tree.setZero(l, r);
            } else {
                int index = qs[i].a - 1;
                int d = qs[i].b;
                double x = all / (2 * SegmentTree.sum(d) + d);
                int id = Arrays.binarySearch(z, index);
                tree.addValue(Arrays.binarySearch(z, index - d + 1), id, x, x);
                tree.addValue(id, Arrays.binarySearch(z, index + d), d * x, -x);
                all = 0;
            }
        }
    }
}
