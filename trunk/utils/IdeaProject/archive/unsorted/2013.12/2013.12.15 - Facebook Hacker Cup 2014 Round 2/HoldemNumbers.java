package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class HoldemNumbers {

    static class Pair {
        int a;
        int b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        out.print("Case #" + testNumber + ": ");
        System.err.println(testNumber);
        int n = in.nextInt();
        int m = in.nextInt();
        Pair[] ps = new Pair[n * (n - 1) / 2];
        for (int i = 1, k = 0; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                ps[k++] = new Pair(i, j);
            }
        }
        Comparator<Pair> comp = new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                int c = Integer.compare(o1.a + o1.b, o2.a + o2.b);
                if (c != 0) return c;
                return Integer.compare(o1.b, o2.b);
            }
        };
        Arrays.sort(ps, comp);
        int l = -1;
        int r = ps.length;
        long all = c(n - 2, 6) * 5 * 3;
        while (l < r - 1) {
            int mid = (l + r) >> 1;
            int a = ps[mid].a;
            int b = ps[mid].b;
            long cur = countWinnings(n, a, b);
//            System.out.println(cur * 5 + " " + all + " " + mid);
            if (cur * 4 > all) {
                r = mid;
            } else {
                l = mid;
            }
        }
//        System.out.println(r + " " + all + " " + ps.length);
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            if (a > b) {
                int t = a;
                a = b;
                b = t;
            }
            Pair z = new Pair(a, b);
            int id = Arrays.binarySearch(ps, z, comp);
//            System.out.println(id);
            if (id >= r) {
                out.print('B');
            } else {
                out.print('F');
            }
        }
        out.println();
//        for (int n = 8; n <= 20; n++) {
//            long last = -1;
//            for (int sum = 3; sum <= n + n - 1; sum++) {
//                for (int i = 1; i <= n; i++) {
//                    int j = sum - i;
//                    if (j < 1 || j >= i) continue;
//                    long cur = 0;
//                    for (int k1 = 1; k1 <= n; k1++) {
//                        if (k1 == i || k1 == j) continue;
//                        for (int k2 = 1; k2 < k1; k2++) {
//                            if (k1 + k2 > sum || k1 + k2 == sum && k1 > i || k2 == i || k2 == j) continue;
//                            for (int l1 = 1; l1 < k1; l1++) {
//                                if (l1 == i || l1 == j || l1 == k2) continue;
//                                for (int l2 = 1; l2 < l1; l2++) {
//                                    if (l1 + l2 > sum || l1 + l2 == sum && l1 > i || l2 == i || l2 == j || l2 == k2) continue;
//                                    for (int m1 = 1; m1 < l1; m1++) {
//                                        if (m1 == i || m1 == j || m1 == k2 || m1 == l2) continue;
//                                        for (int m2 = 1; m2 < m1; m2++) {
//                                            if (m1 + m2 > sum || m1 + m2 == sum && m1 > i || m2 == i || m2 == j || m2 == k2 || m2 == l2) continue;
//                                            ++cur;
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    if (last >= 0 && cur < last) throw new AssertionError(cur + " " + last + " " + n + " " + i + " " + j);
//                    last = cur;
//                    System.out.println(n + " " + i + " " + j + " " + last);
//                }
//            }
//        }
    }

    private long countWinnings(int n, int a, int b) {
        int sum = a + b;
        long cur = 0;
        for (int first = n; first >= 1; first--) {
            if (first == a || first == b) continue;
            for (int second = first - 1; second >= 1; second--) {
                if (second == a || second == b) continue;
                for (int third = second - 1; third >= 1; third--) {
                    if (third == a || third == b) continue;
                    int hFirst = getIt(b, sum, first);
                    int c1 = hFirst;
                    int hSecond = getIt(b, sum, second);
                    int c2 = hSecond;
                    int hThird = getIt(b, sum, third);
                    int c3 = hThird;
                    if (hFirst >= first) --c1;
                    if (hFirst >= second) --c1;
                    if (hFirst >= third) --c1;
                    if (hFirst >= a) --c1;
                    if (hFirst >= b) --c1;
                    if (hSecond >= first) --c2;
                    if (hSecond >= second) --c2;
                    if (hSecond >= third) --c2;
                    if (hSecond >= a) --c2;
                    if (hSecond >= b) --c2;
                    if (hThird >= first) --c3;
                    if (hThird >= second) --c3;
                    if (hThird >= third) --c3;
                    if (hThird >= a) --c3;
                    if (hThird >= b) --c3;
                    if (c1 <= 0 || c2 <= 0 || c3 <= 0) continue;
                    cur += c1 * c2 * c3 - Math.min(c1, c2) * c3 - Math.min(c1, c3) * c2
                            - Math.min(c2, c3) * c1 + 2 * Math.min(Math.min(c2, c3), c1);
                }
            }
        }
        return cur;
    }

    private int getIt(int b, int sum, int first) {
        int hFirst = sum - first;
        if (first > b) --hFirst;
        if (hFirst >= first) hFirst = first - 1;
        return hFirst;
    }

    static long c(int n, int k) {
        long ret = 1;
        for (int i = 0; i < k; i++) {
            ret = ret * (n - i);
            ret = ret / (i + 1);
        }
        return ret;
    }
}
