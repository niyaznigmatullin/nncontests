package coding;

import ru.ifmo.niyaz.DataStructures.MaxSegmentTree;
import ru.ifmo.niyaz.DataStructures.SparseTableMin;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        long[] w = new long[n + 1];
        for (int i = 1; i < n; i++) {
            w[i] = w[i - 1] + in.nextInt();
        }
        long[] g = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            g[i] = g[i - 1] + in.nextInt();
        }
        int[] stack = new int[n + 1];
        long[] val = new long[n + 1];
        long[] maxVal = new long[n + 1];
//        long[] maxAns = new long[n + 1];
        int cn = 0;
//        stack[cn] = n;
//        val[cn] = g[n] - w[n - 1];
//        maxVal[cn] = Long.MIN_VALUE;
//        maxAns[cn] = Long.MIN_VALUE;
//        ++cn;
        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = g[i] - w[i];
        long[] b = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            b[i] = g[i] - w[i - 1];
        }
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (cn > 1 && a[stack[cn - 1]] >= a[i]) {
                --cn;
            }
            int left = k - cn;
            System.out.println("left = " + left);
            val[cn] = getMax(b, i, (cn == 0 ? (n + 1) : stack[cn - 1])) - cn;
            maxVal[cn] = cn == 0 ? val[cn] : Math.max(val[cn], maxVal[cn - 1]);
//            maxAns[cn] = cn == 0 ? Long.MAX_VALUE : Math.min(maxVal[cn] - val[cn], maxAns[cn - 1]);
            stack[cn++] = i;
            System.out.println(Arrays.toString(val));
            System.out.println(Arrays.toString(maxVal));
//            System.out.println(Arrays.toString(maxAns));
            System.out.println("cn = " + cn);
            int l = -1;
            int r = cn - 1;
            while (l < r - 1) {
                int mid = (l + r) >> 1;
                if (maxVal[mid] == maxVal[cn - 1]) {
                    r = mid;
                } else {
                    l = mid;
                }
            }
            System.out.println("r = " + r);
            long minValue = (val[r]) - left;
            int pos = findLast(b, stack[r], n + 1, minValue, stack, cn);
            System.out.println("pos = " + pos);
            ans = Math.max(ans, pos - i);
        }
        out.println(ans);
    }

    static long getMax(long[] b, int left, int right) {
        long ret = Long.MIN_VALUE;
        for (int i = left; i < right; i++) {
            ret = Math.max(ret, b[i]);
        }
        return ret;
    }

    static int findLast(long[] b, int left, int right, long minValue, int[] stack, int cn) {
        b = b.clone();
        int last = b.length;
        for (int i = 0; i < cn; i++) {
            for (int j = last - 1; j >= stack[i]; j--) {
                b[j] -= i;
            }
            last = stack[i];
        }
        for (int i = right - 1; i >= left; i--) {
            if (b[i] >= minValue) {
                return i;
            }
        }
        throw new AssertionError();
    }
}
