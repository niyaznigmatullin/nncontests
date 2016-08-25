package coding;

import ru.ifmo.niyaz.DataStructures.SparseTableMin;
import ru.ifmo.niyaz.DataStructures.SuffixArray;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskE {
    static int[] read(FastScanner in) {
        String s = in.next();
        int[] ret = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            ret[i] = s.charAt(i) - 'a';
        }
        return ret;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int[] s = read(in);
        int n = in.nextInt();
        int[][] t = new int[n][];
        for (int i = 0; i < n; i++) {
            t[i] = read(in);
        }
        int sum = 0;
        for (int[] e : t) {
            sum += e.length;
        }
        int[] all = new int[s.length + n + 1 + sum];
        System.arraycopy(s, 0, all, 0, s.length);
        int[] where = new int[all.length];
        Arrays.fill(where, 0, s.length, -1);
        int cur = 26;
        all[s.length] = cur++;
        for (int i = 0, start = s.length + 1; i < n; i++) {
            System.arraycopy(t[i], 0, all, start, t[i].length);
            Arrays.fill(where, start, start + t[i].length, i);
            start += t[i].length;
            all[start] = cur++;
        }
        int[] sa = SuffixArray.buildSuffixArray(all);
        int[] lcp = SuffixArray.getLCP(sa, all);
        SparseTableMin sparse = new SparseTableMin(lcp);
        int[] rev = new int[sa.length];
        for (int i = 0; i < sa.length; i++) {
            rev[sa[i]] = i;
        }
        int[] onlyT = new int[sum];
        int[] whereDoesTStart = new int[n + 1];
        for (int i = 0, start = 0; i < n; i++) {
            whereDoesTStart[i] = start;
            System.arraycopy(t[i], 0, onlyT, start, t[i].length);
            start += t[i].length;
        }
        whereDoesTStart[n] = sum;
        int q = in.nextInt();
        Query[] qs = new Query[q];
        for (int i = 0; i < q; i++) {
            qs[i] = new Query(whereDoesTStart[in.nextInt() - 1], whereDoesTStart[in.nextInt()], in.nextInt() - 1, in.nextInt());
        }
        Query[] sortQ = qs.clone();
        Arrays.sort(sortQ, new Comparator<Query>() {
            @Override
            public int compare(Query o1, Query o2) {
                int c = Integer.compare(o1.l / 333, o2.l / 333);
                if (c != 0) return c;
                return Integer.compare(o1.r, o2.r);
            }
        });
        for (Query e : sortQ) {
            int inSuffixArray = where[e.pl];
            int l = -1;
            int r = inSuffixArray;
            while (l < r - 1) {
                int mid = (l + r) >> 1;
                if (sparse.getMin(mid, inSuffixArray) >= e.pr - e.pl) {
                    r = mid;
                } else {
                    l = mid;
                }
            }
            int leftOne = r;
            l = inSuffixArray;
            r = all.length;
            while (l < r - 1) {
                int mid = (l + r) >> 1;
                if (sparse.getMin(inSuffixArray, mid) >= e.pr - e.pl) {
                    l = mid;
                } else {
                    r = mid;
                }
            }
            int rightOne = l;

        }
    }

    static class SegmentTree {
        int[] a;
        int[] popularity;
        int n;

        public SegmentTree(int n) {
            this.n = Integer.highestOneBit(n) << 1;
            a = new int[this.n * 2];
            popularity = new int[this.n * 2];
            Arrays.fill(a, -1);
        }

        void set(int x, int y) {
            x += n;
            a[x] = y;
            if (y < 0) {
                popularity[x] = 0;
            } else {
                popularity[x] = 1;
            }
            while (x > 1) {
                x >>= 1;
                if (a[x * 2] == a[x * 2 + 1]) {
                    a[x] = a[x * 2];
                    popularity[x] = popularity[x * 2] + popularity[x * 2 + 1];
                } else if (popularity[x * 2] > popularity[x * 2 + 1]) {
                    a[x] = a[x * 2];
                    popularity[x] = popularity[x * 2] - popularity[x * 2 + 1];
                } else {
                    a[x] = a[x * 2 + 1];
                    popularity[x] = popularity[x * 2 + 1] - popularity[x * 2];
                }
            }
        }
    }

    static class Query {
        int l;
        int r;
        int pl;
        int pr;
        int ansId;
        int ansCount;

        public Query(int l, int r, int pl, int pr) {
            this.l = l;
            this.r = r;
            this.pl = pl;
            this.pr = pr;
        }
    }
}
