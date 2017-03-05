package coding;

import ru.ifmo.niyaz.DataStructures.*;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class NumberOfSubstrings {

    static final int C = 666;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        String s = in.next();
        int[] a = new int[s.length() + 1];
        for (int i = 0; i < s.length(); i++) a[i] = s.charAt(i);
        int[] sa = SuffixArray.buildSuffixArray(a);
        int[] lcp = SuffixArray.getLCP(sa, a);
        Query[] qs = new Query[q];
        for (int i = 0; i < q; i++) {
            qs[i] = new Query(in.nextInt() - 1, in.nextInt() - 1);
        }
        Query[] sortedQ = qs.clone();
        Arrays.sort(sortedQ, new Comparator<Query>() {
            @Override
            public int compare(Query o1, Query o2) {
                int c = Integer.compare(o1.left / C, o2.left / C);
                if (c != 0) return c;
                return Integer.compare(o1.right, o2.right);
            }
        });
        int l = 0;
        int r = -1;
        Structure solver = new Structure(sa, lcp);
        int[] where = new int[sa.length];
        for (int i = 0; i < sa.length; i++) {
            where[sa[i]] = i;
        }
        for (Query e : sortedQ) {
            while (r < e.right) {
                ++r;
                solver.add(where[r]);
            }
            while (l > e.left) {
                --l;
                solver.add(where[l]);
            }
            while (r > e.right) {
                solver.remove(where[r]);
                --r;
            }
            while (l < e.left) {
                solver.remove(where[l]);
                ++l;
            }
//            for (int i = l; i <= r; i++) {
//                System.out.print(where[i] + " ");
//            }
//            System.out.println(e.left + " " + e.right + " " + solver.getAnswer(r + 1));
            int[] z = new int[e.right - e.left + 1];
            for (int i = e.left; i <= e.right; i++) {
                z[i - e.left] = where[i];
            }
            Arrays.sort(z);
            long ans2 = (long) (e.right - e.left + 1) * (e.right - e.left + 2) / 2;
            for (int i = 0; i + 1 < z.length; i++) {
                int mx = Integer.MAX_VALUE;
                for (int j = z[i]; j < z[i + 1]; j++) mx = Math.min(mx, lcp[j]);
                ans2 -= Math.min(mx, r + 1 - Math.max(sa[z[i]], sa[z[i + 1]]));
                System.out.println(Math.min(mx, r + 1 - Math.max(sa[z[i]], sa[z[i + 1]])));
            }
            e.answer = (long) (e.right - e.left + 1) * (e.right - e.left + 2) / 2 - solver.getAnswer(r + 1);
            System.out.println(ans2);
        }
        for (Query e : qs) {
            out.println(e.answer);
        }
    }



    static class Structure {
        FenwickKth f;
        FenwickLong sf;
        Fenwick cf;
        SparseTableMin stable;
        int n;
        int[] sa;
        long sum;

        Structure(int[] sa, int[] lcp) {
            this.sa = sa;
            stable = new SparseTableMin(lcp);
            n = sa.length;
            sf = new FenwickLong(n);
            cf = new Fenwick(n);
            f = new FenwickKth(n);
            sum = 0;
        }

        void add(int id) {
            int k = f.getSum(id);
            int prev = k == 0 ? -1 : f.getKth(k);
            int next = k == f.getSum(f.getCapacity()) ? n : f.getKth(k + 1);
            if (prev >= 0 && next < n) {
                removePoint(prev, next);
            }
            if (prev >= 0) {
                addPoint(prev, id);
            }
            if (next < n) {
                addPoint(id, next);
            }
            f.add(id, 1);
        }

        void remove(int id) {
            f.add(id, -1);
            int k = f.getSum(id);
            int prev = k == 0 ? -1 : f.getKth(k);
            int next = k == f.getSum(f.getCapacity()) ? n : f.getKth(k + 1);
            if (next < n) {
                removePoint(id, next);
            }
            if (prev >= 0) {
                removePoint(prev, id);
            }
            if (prev >= 0 && next < n) {
                addPoint(prev, next);
            }
        }

        void addPoint(int first, int second) {
            System.out.println("add " + first + " " + second);
            if (first >= second) throw new AssertionError(first + " " + second);
            int lcp = stable.getMin(first, second);
            int sub = Math.max(sa[first], sa[second]);
            System.out.println(lcp + " " + sub);
            int pos = (n - lcp - sub);
            sf.add(pos, -sub - lcp);
            cf.add(pos, 1);
            sum += lcp;
        }

        void removePoint(int first, int second) {
            System.out.println("remove " + first + " " + second);
            if (first >= second) throw new AssertionError(first + " " + second);
            int lcp = stable.getMin(first, second);
            int sub = Math.max(sa[first], sa[second]);
            int pos = (n - lcp - sub);
            sf.add(pos, sub + lcp);
            cf.add(pos, -1);
            sum -= lcp;
        }

        long getAnswer(int right) {
            long sum1 = sf.getSum(n - right);
            int count1 = cf.getSum(n - right);
            System.out.println("sum = " + sum + " count1 = " + count1 + " sum1 = " + sum1);
            return sum1 + (long) right * count1 + sum;
        }
    }

    private static class Query {
        int left;
        int right;
        long answer;

        public Query(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}
