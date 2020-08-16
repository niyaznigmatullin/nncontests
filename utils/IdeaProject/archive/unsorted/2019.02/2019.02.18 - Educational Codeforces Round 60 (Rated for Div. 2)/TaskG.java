package coding;

import ru.ifmo.niyaz.DataStructures.Fenwick;
import ru.ifmo.niyaz.DataStructures.FenwickLong;
import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class TaskG {
    static class Query {
        int left;
        int right;
        long ans;

        public Query(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = in.nextInt();
        }
        Query[] qs = new Query[q];
        int[] leftQ = in.readIntArray(q);
        int[] rightQ = in.readIntArray(q);
        for (int i = 0; i < q; i++) {
            qs[i] = new Query(leftQ[i] - 1, rightQ[i] - 1);
        }
        solve(p, qs);
        ArrayUtils.reverse(p);
        for (int i = 0; i < q; i++) {
            int v = qs[i].left;
            qs[i].left = n - qs[i].right - 1;
            qs[i].right = n - v - 1;
        }
        solve(p, qs);
        long[] ans = new long[qs.length];
        for (int i = 0; i < qs.length; i++) {
            Query e = qs[i];
            ans[i] = e.ans + e.right - e.left + 1;
        }
        out.printArray(ans);
    }

    static void solve(int[] p, Query[] qs) {
        int n = p.length;
        int[] stack = new int[n];
        int sn = 0;
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            while (sn > 0 && p[stack[sn - 1]] < p[i]) --sn;
            left[i] = sn > 0 ? stack[sn - 1] : -1;
            stack[sn++] = i;
        }
        List<Query>[] queries = new List[n];
        List<Integer>[] opens = new List[n];
        for (int i = 0; i < n; i++) opens[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (left[i] >= 0) opens[left[i]].add(i);
        }
        for (int i = 0; i < n; i++) queries[i] = new ArrayList<>();
        for (Query e : qs) queries[e.left].add(e);
        FenwickLong f1 = new FenwickLong(n);
        Fenwick f1Count = new Fenwick(n);
        for (int i = n - 1; i >= 0; i--) {
            f1.add(i, i);
            f1Count.add(i, 1);
            for (int j : opens[i]) {
                f1.add(j, -j);
                f1Count.add(j, -1);
                f1.add(j, j - left[j] - 1);
            }
            for (Query e : queries[i]) {
                e.ans += f1.getSum(e.left, e.right + 1) - (long) e.left * f1Count.getSum(e.left, e.right + 1);
            }
        }
    }
}
