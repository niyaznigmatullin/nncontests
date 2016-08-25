package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int s = in.nextInt();
        int n = in.nextInt();
        int cn = 0;
        int[] a_ = new int[n];
        int[] b_ = new int[n];
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            if (x + y > s) {
                continue;
            }
            a_[cn] = x;
            b_[cn] = y;
            ++cn;
        }
        final int[] a = Arrays.copyOf(a_, cn);
        final int[] b = Arrays.copyOf(b_, cn);
        n = cn;
        Integer[] orderA = new Integer[n];
        Integer[] orderB = new Integer[n];
        for (int i = 0; i < n; i++) {
            orderA[i] = orderB[i] = i;
        }
        Arrays.sort(orderA, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(a[o1], a[o2]);
            }
        });
        Arrays.sort(orderB, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(b[o1], b[o2]);
            }
        });
        int have = 0;
        int kt = n;
        boolean[] used = new boolean[n];
        int ansA = 1;
        int ansB = s - 1;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] <= ansA && b[i] <= ansB) {
                ++ans;
            }
        }
        for (int it = 0; it < n; ) {
            int jt = it;
            int curA = a[orderA[it]];
            if (curA >= s) {
                break;
            }
            while (jt < n && curA == a[orderA[jt]]) {
                used[orderA[jt]] = true;
                ++have;
                ++jt;
            }
            int maxB = s - curA;
            while (kt > 0 && b[orderB[kt - 1]] > maxB) {
                --kt;
                if (!used[orderB[kt]]) {
                    throw new AssertionError();
                }
                used[orderB[kt]] = false;
                --have;
            }
            if (have > ans) {
                ans = have;
                ansA = curA;
                ansB = maxB;
            }
            it = jt;
        }
        out.println(ansA + " " + ansB);
    }
}
