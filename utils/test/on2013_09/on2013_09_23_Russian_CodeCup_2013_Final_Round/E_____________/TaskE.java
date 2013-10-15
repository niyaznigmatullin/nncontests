package lib.test.on2013_09.on2013_09_23_Russian_CodeCup_2013_Final_Round.E_____________;



import ru.ifmo.niyaz.DataStructures.MultiSegmentTree;
import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        long k = in.nextInt();
        int[] years = new int[m];
        long[] incs = new long[m];
        for (int i = 0; i < m; i++) {
            years[i] = in.nextInt();
            incs[i] = in.nextInt();
        }
        int[] year = new int[2 * m];
        for (int i = 0; i < m; i++) {
            year[2 * i] = Math.max(years[i] - 1, 0);
            year[2 * i + 1] = years[i];
        }
        year = ArrayUtils.sortAndUnique(year);
        long[] inc = new long[year.length];
        m = year.length;
        for (int i = 0; i < m; i++) {
            int id = Arrays.binarySearch(years, year[i]);
            if (id >= 0) {
                inc[i] = incs[id];
            }
        }
        long[] dd = new long[m];
        for (int i = 0; i < m; i++) {
            dd[i] = inc[i] - k * (year[i] - (i > 0 ? year[i - 1] : 0));
        }
        tree = new MultiSegmentTree(m);
        for (int i = 0; i < m; i++) {
            tree.add(i, m, dd[i]);
        }
        init(dd);
        for (int i = 0; i < n; i++) {
            int y1 = in.nextInt();
            int y2 = in.nextInt();
            long start = in.nextInt();
            int first = search(m, year, y1);
            int last = search(m, year, y2);
            long ans;
            if (first == last) {
                ans = Math.max(0, start - k * (y2 - y1));
            } else {
                start += k * (y1 - (first > 0 ? year[first - 1] : 0) - 1);
                long rightMin = getMinPrefix(first, last);
                if (start + rightMin >= 0) {
                    ans = start + getSum(first, last);
                } else {
//                    int l = first;
//                    int r = last;
//                    while (l < r - 1) {
//                        int mid = (l + r) >> 1;
//                        if (start + getMinPrefix(first, mid) < 0) {
//                            r = mid;
//                        } else {
//                            l = mid;
//                        }
//                    }
                    ans = getIt(1, 0, this.n, first, last);
//                    System.out.println(ans + " " + start + " " + rightMin + " " + r + " " + last + " " + year[r]);
                }
                ans -= k * (y2 - year[last - 1] - 1);
                ans = Math.max(ans, 0);
            }
            int id = Arrays.binarySearch(year, y2);
            if (id >= 0) {
                ans += inc[id];
            }
            out.println(ans);
        }
    }

    int search(int m, int[] year, int y1) {
        int first;
        int l = -1;
        int r = m;
        while (l < r - 1) {
            int mid = (l + r) >> 1;
            if (year[mid] >= y1) r = mid;
            else l = mid;
        }
        first = r;
        return first;
    }

    long getSum(int l, int r) {
        if (true) {
            long ret = tree.getMin(r - 1, r);
            if (l > 0) {
                ret -= tree.getMin(l - 1, l);
            }
            if (ret != getSum2(l, r)) throw new AssertionError();
            return ret;
        }
//        r = Math.min(r, sum.length);
//        if (l >= r) return 0;
        return getSum2(l, r);
    }

    private long getSum2(int l, int r) {
        if (l >= r) throw new AssertionError();
        long ret = sum[r - 1];
        if (l > 0) ret -= sum[l - 1];
        return ret;
    }


    long getMinPrefix(int l, int r) {
        if (true) {
            long ret = tree.getMin(l, r);
            if (l > 0) {
                ret -= tree.getMin(l - 1, l);
            }
            return ret;
        }
//        r = Math.min(r, sum.length);
//        if (l >= r) return 0;
        if (l >= r) throw new AssertionError();
        int k = size[r - l];
        return Math.min(min[k][l], min[k][r - (1 << k)]);
    }

    MultiSegmentTree tree;
    long[][] min;
    int[] size;
    long[] sum;
    long[] zt;
    int n;

    void init(long[] a) {
        long[] sd = a.clone();
        for (int i = 1; i < sd.length; i++) sd[i] += sd[i - 1];
        sd = Arrays.copyOf(sd, Integer.highestOneBit(a.length) << 1);
        sum = sd.clone();
        for (int i = 1; i < sum.length; i++) sum[i] += sum[i - 1];
        min = new long[21][sd.length];
        size = new int[sd.length + 1];
        size[1] = 0;
        for (int i = 2; i < size.length; i++) {
            size[i] = size[i / 2] + 1;
        }
        for (int i = 0; i < sd.length; i++) min[0][i] = sd[i];
        for (int i = 1; i < min.length; i++) {
            long[] mini = min[i];
            long[] mini2 = min[i - 1];
            for (int j = 0; j < sd.length; j++) {
                mini[j] = mini2[j];
                int next = j + (1 << (i - 1));
                if (next < sd.length) {
                    mini[j] = Math.min(mini[j], mini2[next]);
                }
            }
        }
        int m = a.length;
        n = Integer.highestOneBit(m) << 1;
        zt = new long[2 * n];
        for (int i = 0; i < a.length; i++) {
            zt[i + n] = Math.max(0, a[i]);
        }
//        System.out.println("a = " + Arrays.toString(a));
        build(1, 0, n);
    }

    void build(int v, int l, int r) {
        if (l >= r - 1) return;
        int mid = (l + r) >> 1;
        build(v * 2, l, mid);
        build(v * 2 + 1, mid, r);
        long left = zt[v * 2];
        long rightMin = getMinPrefix(mid, r);
        if (left + rightMin < 0) zt[v] = zt[v * 2 + 1];
        else zt[v] = left + getSum(mid, r);
//        System.out.println("zt[" + v + "] = " + zt[v]);
    }

    long getIt(int v, int l, int r, int left, int right) {
        if (r <= left || right <= l) {
            return 0;
        }
        if (left <= l && r <= right) {
            return zt[v];
        }
        int m = (l + r) >> 1;
        long gotLeft = getIt(v * 2, l, m, left, right);
        int ll = Math.max(m, left);
        int rr = Math.min(r, right);
        if (ll >= rr) {
//            System.out.println("t1 " + v + " " + l + " " + r + " " + gotLeft);
            return gotLeft;
        }
        long minRight = getMinPrefix(ll, rr);
        if (gotLeft + minRight < 0) {
            long ret = getIt(v * 2 + 1, m, r, left, right);
//            System.out.println("t2 " + v + " " + l + " " + r + " " + ret + " " + gotLeft + " " + minRight);
            return ret;
        } else {
            long ret = gotLeft + getSum(ll, rr);
//            System.out.println("t3 " + v + " " + l + " " + r + " " + ret);
            return ret;
        }
    }

}
