package coding;

import ru.ifmo.niyaz.DataStructures.FenwickMin;
import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskD {
    static class Query {
        int left;
        int right;
        int answer;

        public Query(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = in.readIntArray(n);
        Query[] q = new Query[m];
        for (int i = 0; i < m; i++) {
            q[i] = new Query(in.nextInt() - 1, in.nextInt());
        }
        Query[] sortedQ = q.clone();
        Arrays.sort(sortedQ, new Comparator<Query>() {
            @Override
            public int compare(Query o1, Query o2) {
                return Integer.compare(o1.left, o2.left);
            }
        });
        int[] as = ArrayUtils.sortAndUnique(a);
        for (int i = 0; i < a.length; i++) {
            a[i] = Arrays.binarySearch(as, a[i]);
        }
        int[] last = new int[as.length];
        Arrays.fill(last, -1);
        FenwickMin f = new FenwickMin(n);
        int currentQuery = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            int x = a[i];
            if (last[x] >= 0) {
                f.setAndMin(last[x], last[x] - i);
            }
            last[x] = i;
            while (currentQuery >= 0 && sortedQ[currentQuery].left == i) {
                sortedQ[currentQuery].answer = f.getMin(sortedQ[currentQuery].right - 1);
                --currentQuery;
            }
        }
        for (Query e : q) {
            if (e.answer == Integer.MAX_VALUE) {
                out.println(-1);
            } else {
                out.println(e.answer);
            }
        }
    }
}
