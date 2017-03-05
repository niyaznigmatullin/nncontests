package coding;

import ru.ifmo.niyaz.DataStructures.*;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class NumberOfSubstrings2 {
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
        List<Query>[] queries = new List[n];
        for (int i = 0; i < n; i++) queries[i] = new ArrayList<>();
        for (Query e : qs) queries[e.left].add(e);
        MinSegmentTree minTree = new MinSegmentTree(n + 1);
        int[] where = new int[sa.length];
        for (int i = 0; i < sa.length; i++) where[sa[i]] = i;
        SparseTableMin stable = new SparseTableMin(lcp);
        FenwickMultiSum fSum = new FenwickMultiSum(n);
        final int K = 18;
        int[][] st = new int[K][lcp.length];
        st[0] = lcp.clone();
        for (int i = 1; i < K; i++) {
            int[] sti = st[i];
            int[] pst = st[i - 1];
            for (int v = 0; v < lcp.length; v++) {
                if (v + (1 << (i - 1)) < lcp.length) {
                    sti[v] = Math.min(pst[v], pst[v + (1 << i - 1)]);
                } else {
                    sti[v] = pst[v];
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            int suffix = where[i];
            int curLen = 0;
            while (true) {
                int cur = suffix;
                for (int e = K - 1; e >= 0; e--) {
                    if (cur - (1 << e) < 0) continue;
                    if (st[e][cur - (1 << e)] > curLen) cur -= 1 << e;
                }
                int first = cur;
                cur = suffix;
                for (int e = K - 1; e >= 0; e--) {
                    if (cur + (1 << e) <= n + 1) {
                        if (st[e][cur] > curLen) cur += 1 << e;
                    }
                }
                int last = cur;
                int j = minTree.getMin(first, last + 1);
                if (j == Integer.MAX_VALUE) break;
                int nextSuffix = where[j];
                int curLCP = nextSuffix < suffix ? stable.getMin(nextSuffix, suffix) : stable.getMin(suffix, nextSuffix);
                fSum.add(j + curLen, j + curLCP, -1);
                curLen = curLCP;
            }
            minTree.set(suffix, i);
            fSum.add(i, n, 1);
            for (Query e : queries[i]) {
                e.answer = fSum.getSum(e.left, e.right + 1);
            }
        }
        for (Query e : qs) out.println(e.answer);
    }


    static class Query {
        int left;
        int right;
        long answer;

        public Query(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

}
