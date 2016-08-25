package coding;

import ru.ifmo.niyaz.DataStructures.Fenwick;
import ru.ifmo.niyaz.DataStructures.FenwickLong;
import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = in.readIntArray(n);
        int[] f = ArrayUtils.sortAndUnique(a);
        Query[] qs = new Query[m];
        for (int i = 0; i < m; i++) {
            qs[i] = new Query(in.nextInt() - 1, in.nextInt());
        }
        Query[] sorted = qs.clone();
        Arrays.sort(sorted, new Comparator<Query>() {
            @Override
            public int compare(Query o1, Query o2) {
                final int C = 1000;
                int c = Integer.compare(o1.l / C, o2.l / C);
                if (c != 0) return c;
                return Integer.compare(o1.r, o2.r);
            }
        });
        int[] id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = Arrays.binarySearch(f, a[i]);
        }
        int l = 0;
        int r = 0;
        FenwickLong fs = new FenwickLong(f.length);
        for (Query e : sorted) {
            while (e.r > r) {
                fs.add(id[r], a[r]);
                ++r;
            }
            while (e.l < l) {
                --l;
                fs.add(id[l], a[l]);
            }
            while (e.r < r) {
                --r;
                fs.add(id[r], -a[r]);
            }
            while (e.l > l) {
                fs.add(id[l], -a[l]);
                ++l;
            }
            long s = 0;
            while (true) {
                int left = -1;
                int right = f.length;
                while (left < right - 1) {
                    int mid = (left + right) >> 1;
                    if (f[mid] > s + 1) right = mid; else left = mid;
                }
                long sum = fs.getSum(left);
                if (sum == s) break;
                s = sum;
            }
            e.ans = s + 1;
        }
        for (Query e : qs) {
            out.println(e.ans);
        }
    }

    static class Query {
        int l;
        int r;
        long ans;

        public Query(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }
}
