package coding;

import ru.ifmo.niyaz.DataStructures.Fenwick;
import ru.ifmo.niyaz.DataStructures.FenwickRev;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.Factor;
import ru.ifmo.niyaz.math.MathUtils;

import java.util.Arrays;

public class TaskE {
    int MOD;

    int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }

    int add(int a, int b) {
        a += b;
        if (a >= MOD) a -= MOD;
        return a;
    }

    class SegmentTree {
        int[] sum;
        int[] coef;
        int n;

        SegmentTree(int size) {
            n = 1;
            while (n < size) n *= 2;
            sum = new int[2 * n];
            coef = new int[2 * n];
            Arrays.fill(coef, 1);
        }

        void set(int v, int x) {
            set(1, 0, n, v, x);
        }

        int getSum(int left, int right) {
            return getSum(1, 0, n, left, right);
        }

        void relax(int v) {
            if (coef[v] == 1) return;
            sum[v] = getSumVertex(v);
            coef[2 * v] = mul(coef[2 * v], coef[v]);
            coef[2 * v + 1] = mul(coef[2 * v + 1], coef[v]);
            coef[v] = 1;
        }

        int getSum(int v, int left, int right, int L, int R) {
            if (right <= L || R <= left) return 0;
            if (L <= left && right <= R) {
                return getSumVertex(v);
            }
            relax(v);
            int mid = (left + right) >> 1;
            return add(getSum(v * 2, left, mid, L, R), getSum(v * 2 + 1, mid, right, L, R));
        }

        void set(int v, int left, int right, int pos, int val) {
            if (left == right - 1) {
                sum[v] = val;
                coef[v] = 1;
                return;
            }
            relax(v);
            int mid = (left + right) >> 1;
            if (pos < mid) {
                set(v * 2, left, mid, pos, val);
            } else {
                set(v * 2 + 1, mid, right, pos, val);
            }
            sum[v] = add(getSumVertex(2 * v), getSumVertex(2 * v + 1));
        }

        void makeMul(int left, int right, int val) {
            makeMul(1, 0, n, left, right, val);
        }

        void makeMul(int v, int left, int right, int L, int R, int val) {
            if (right <= L || R <= left) return;
            if (L <= left && right <= R) {
                coef[v] = mul(coef[v], val);
                return;
            }
            relax(v);
            int mid = (left + right) >> 1;
            makeMul(v * 2, left, mid, L, R, val);
            makeMul(v * 2 + 1, mid, right, L, R, val);
            sum[v] = add(getSumVertex(2 * v), getSumVertex(2 * v + 1));
        }

        int getSumVertex(int v) {
            return mul(sum[v], coef[v]);
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        MOD = in.nextInt();
        int phi = MOD;
        Factor[] f = MathUtils.factorize(MOD);
        for (Factor e : f) phi -= phi / e.prime;
        FenwickRev[] fs = new FenwickRev[f.length];
        for (int i = 0; i < fs.length; i++) {
            fs[i] = new FenwickRev(n);
        }
        SegmentTree tree = new SegmentTree(n);
        SegmentTree coprime = new SegmentTree(n);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            tree.set(i, a[i] % MOD);
            for (int j = 0; j < f.length; j++) {
                int prime = (int) f[j].prime;
                while (a[i] % prime == 0) {
                    fs[j].add(i, i + 1, 1);
                    a[i] /= prime;
                }
            }
            coprime.set(i, a[i]);
        }
        int q = in.nextInt();
        for (int cq = 0; cq < q; cq++) {
            int op = in.nextInt();
            if (op == 1) {
                int left = in.nextInt() - 1;
                int right = in.nextInt();
                int val = in.nextInt();
                tree.makeMul(left, right, val % MOD);
                for (int j = 0; j < f.length; j++) {
                    int prime = (int) f[j].prime;
                    while (val % prime == 0) {
                        fs[j].add(left, right, 1);
                        val /= prime;
                    }
                }
                coprime.makeMul(left, right, val % MOD);
            } else if (op == 2) {
                int pos = in.nextInt() - 1;
                int val = in.nextInt();
                for (int j = 0; j < f.length; j++) {
                    int prime = (int) f[j].prime;
                    while (val % prime == 0) {
                        fs[j].add(pos, pos + 1, -1);
                        val /= prime;
                    }
                }
                int newVal = coprime.getSum(pos, pos + 1);
                newVal = mul(newVal, MathUtils.modPow(val, phi - 1, MOD));
                coprime.set(pos, newVal);
                for (int j = 0; j < f.length; j++) {
                    newVal = mul(newVal, MathUtils.modPow((int) f[j].prime, fs[j].getElement(pos), MOD));
                }
                tree.set(pos, newVal);
            } else {
                int left = in.nextInt() - 1;
                int right = in.nextInt();
                out.println(tree.getSum(left, right));
            }
        }
    }
}
