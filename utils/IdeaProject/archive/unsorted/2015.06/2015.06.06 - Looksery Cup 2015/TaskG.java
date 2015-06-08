package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskG {

    static class Element {
        int val;
        int toLeft;
        int toRight;

        public Element(int val) {
            this.val = val;
        }
    }

    static long inversions(Element[] a, Element[] b, int left, int right) {
        if (b == null) {
            b = new Element[a.length];
        }
        if (left + 1 >= right) {
            return 0;
        }
        int middle = left + right >> 1;
        long ret = inversions(a, b, left, middle) + inversions(a, b, middle, right);
        for (int i = left, j = middle, k = 0; i < middle || j < right; k++) {
            if (j >= right || i < middle && a[i].val <= a[j].val) {
                ret += j - middle;
                a[i].toRight += j - middle;
                b[k] = a[i++];
            } else {
                a[j].toLeft += middle - i;
                b[k] = a[j++];
            }
        }
        for (int i = left; i < right; i++) {
            a[i] = b[i - left];
        }
        return ret;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        Element[] f = new Element[n];
        for (int i = 0; i < n; i++) {
            f[i] = new Element(i + a[i]);
        }
        Element[] g = f.clone();
        long z = inversions(f, null, 0, f.length);
        int[] q = new int[n];
//        for (int i = 0; i < n; i++) {
//            System.err.println(a[i] + " " + g[i].toLeft + " " + g[i].toRight);
//        }
        for (int i = 0; i < n; i++) {
            q[i + g[i].toRight - g[i].toLeft] = a[i] - g[i].toRight + g[i].toLeft;
        }
        for (int i = 0; i + 1 < n; i++) if (q[i] > q[i + 1]) {
            out.println(":(");
            return;
        }
        out.printArray(q);
    }
}
