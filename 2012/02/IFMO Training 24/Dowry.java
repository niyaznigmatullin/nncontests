package mypackage;

import arrayutils.ArrayUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Dowry {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        long l = in.nextLong();
        long r = in.nextLong();
        long[] w = new long[n];
        long[] v = new long[n];
        for (int i = 0; i < n; i++) {
            w[i] = in.nextLong();
            v[i] = in.nextLong();
        }
        int m = n / 2;
        Element[] a = new Element[1 << m];
        for (int i = 0; i < 1 << m; i++) {
            long weight = 0;
            long value = 0;
            for (int j = 0; j < m; j++) {
                if (((i >> j) & 1) == 1) {
                    weight += w[j];
                    value += v[j];
                }
            }
            a[i] = new Element(weight, value, i);
        }
        Arrays.sort(a, new Comparator<Element>() {
            public int compare(Element o1, Element o2) {
                return Long.signum(o1.w - o2.w);
            }
        });
        int mask1 = 0;
        int mask2 = 0;
        long answer = Long.MIN_VALUE;
        SegmentTree tree = new SegmentTree(a);
        for (int i = 0; i < 1 << (n - m); i++) {
            long weight = 0;
            long value = 0;
            for (int j = m; j < n; j++) {
                if (((i >> (j - m)) & 1) == 1) {
                    weight += w[j];
                    value += v[j];
                }
            }
            int left = -1;
            int right = a.length;
            while (left < right - 1) {
                int mid = left + right >> 1;
                if (a[mid].w + weight < l) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            int leftID = right;
            left = -1;
            right = a.length;
            while (left < right - 1) {
                int mid = left + right >> 1;
                if (a[mid].w + weight > r) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
            int rightID = left;
            if (leftID > rightID) {
                continue;
            }
            Element e = tree.getMax(leftID, rightID + 1);
            if (e.v + value > answer) {
                answer = value + e.v;
                mask1 = e.mask;
                mask2 = i;
            }
        }
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < m; i++) {
            if (((mask1 >> i) & 1) == 1) {
                ans.add(i + 1);
            }
        }
        for (int i = m; i < n; i++) {
            if (((mask2 >> i - m) & 1) == 1) {
                ans.add(i + 1);
            }
        }
        if (answer == Long.MIN_VALUE) {
            out.println(0);
        } else {
            out.println(ans.size());
            out.printArray(ArrayUtils.toPrimitiveArray(ans));
        }
    }

    static class SegmentTree {
        Element[] t;
        int n;

        SegmentTree(Element[] a) {
            n = Integer.highestOneBit(a.length) << 1;
            t = new Element[n << 1];
            for (int i = 0; i < a.length; i++) {
                t[i + n] = a[i];
            }
            for (int i = n - 1; i > 0; i--) {
                t[i] = max(t[2 * i], t[2 * i + 1]);
            }
        }

        Element getMax(int l, int r) {
            --r;
            l += n;
            r += n;
            Element ret = null;
            while (l <= r) {
                if ((l & 1) == 1) {
                    ret = max(ret, t[l++]);
                }
                if ((r & 1) == 0) {
                    ret = max(ret, t[r--]);
                }
                l >>= 1;
                r >>= 1;
            }
            return ret;
        }

        static Element max(Element a, Element b) {
            if (a == null) {
                return b;
            }
            if (b == null) {
                return a;
            }
            return a.v < b.v ? b : a;
        }
    }

    static class Element {
        long w;
        long v;
        int mask;

        Element(long w, long v, int mask) {
            this.w = w;
            this.v = v;
            this.mask = mask;
        }
    }
}
