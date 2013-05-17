package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class G {
    static final int MAXN = 1 << 30;
    static class Node {
        Node left;
        Node right;
        double sum;
        boolean needSet;
        double addA;
        double addB;
        int count;

        Node(int count) {
            this.count = count;
        }
    }

    static double getSum(Node v, int l, int r) {
        return getSum(v, 0, MAXN, l, r);
    }

    static double sum(double x) {
        return x * (x - 1) * .5;
    }

    static double getSum(Node v) {
        if (v == null) return 0;
        if (v.needSet) return 0;
        return v.sum + v.addA * v.count + v.addB * sum(v.count);
    }

    static void relax(Node v) {
        if (v == null) return;
        if (v.needSet) {
            v.sum = getSum(v);
            setZero(v.left);
            setZero(v.right);
            v.needSet = false;
            return;
        }
        if (v.addA == 0 && v.addB == 0) {
            return;
        }
        v.sum = getSum(v);
        if (v.left == null) {
            v.left = new Node(v.count >> 1);
        }
        if (v.right == null) {
            v.right = new Node(v.count >> 1);
        }
        addValue(v.left, v.addA, v.addB);
        addValue(v.right, v.addA + v.addB * v.left.count, v.addB);
        v.addA = 0;
        v.addB = 0;
    }

    static double getSum(Node v, int left, int right, int l, int r) {
        if (v == null) return 0;
        if (right <= l || r <= left) return 0;
        if (l <= left && right <= r) {
            return getSum(v);
        }
        relax(v);
        int mid = (left + right) >> 1;
        return getSum(v.left, left, mid, l, r) + getSum(v.right, mid, right, l, r);
    }

    static void setZero(Node v, int left, int right) {
        setZero(v, 0, MAXN, left, right);
    }

    static void setZero(Node v) {
        if (v == null) return;
        v.addA = v.addB = 0;
        v.needSet = true;
    }

    static void addValue(Node v, double dA, double dB) {
        if (v == null) throw new AssertionError();
        if (v.needSet) {
            v.sum = 0;
            v.needSet = false;
            setZero(v.left);
            setZero(v.right);
        }
        v.addA += dA;
        v.addB += dB;
    }

    static void setZero(Node v, int left, int right, int l, int r) {
        if (v == null) return;
        if (right <= l || r <= left) {
            return;
        }
        if (l <= left && right <= r) {
            setZero(v);
            return;
        }
        relax(v);
        int mid = (left + right) >> 1;
        setZero(v.left, left, mid, l, r);
        setZero(v.right, mid, right, l, r);
        v.sum = getSum(v.left) + getSum(v.right);
    }

    static void addValue(Node v, int l, int r, double dA, double dB) {
        addValue(v, 0, MAXN, l, r, dA, dB);
    }

    static int addValue(Node v, int left, int right, int l, int r, double dA, double dB) {
        if (right <= l || r <= left) {
            return 0;
        }
        if (l <= left && right <= r) {
            addValue(v, dA, dB);
            return v.count;
        }
        relax(v);
        if (v.left == null) {
            v.left = new Node(v.count >> 1);
        }
        if (v.right == null) {
            v.right = new Node(v.count >> 1);
        }
        int mid = (left + right) >> 1;
        int added = addValue(v.left, left, mid, l, r, dA, dB);
        int added2 = addValue(v.right, mid, right, l, r, dA + dB * added, dB);
        v.sum = getSum(v.left) + getSum(v.right);
        return added + added2;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int p = in.nextInt();
        int m = in.nextInt();
        int last = 0;
        Node root = new Node(MAXN);
        double all = 0;
        for (int i = 0; i < m; i++) {
            int time = in.nextInt();
            addValue(root, 0, n, 1. * p * (time - last), 0);
            last = time;
            String s = in.next();
            if (s.equals("save")) {
                int l = in.nextInt() - 1;
                int r = in.nextInt();
                double sum = getSum(root, l, r);
                all += sum;
                out.println(all);
                setZero(root, l, r);
            } else {
                int index = in.nextInt() - 1;
                int d = in.nextInt();
                double x = all / (2 * sum(d) + d);
                addValue(root, index - d + 1, index, x, x);
                addValue(root, index, index + d, d * x, -x);
                all = 0;
            }
        }
    }
}
