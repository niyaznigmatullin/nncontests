package coding;

import ru.ifmo.niyaz.DataStructures.MinSegmentTree;
import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class Task661 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int start = in.nextInt();
        int end = in.nextInt();
        int[] from = new int[n];
        final int[] to = new int[n];
        int[] q = new int[n + n + 2];
        int[] cost = new int[n];
        for (int i = 0; i < n; i++) {
            from[i] = in.nextInt();
            to[i] = in.nextInt();
            cost[i] = in.nextInt();
            q[2 * i] = from[i];
            q[2 * i + 1] = to[i];
        }
        q[2 * n] = start;
        q[2 * n + 1] = end;
        q = ArrayUtils.sortAndUnique(q);
        start = Arrays.binarySearch(q, start);
        end = Arrays.binarySearch(q, end);
        for (int i = 0; i < n; i++) {
            from[i] = Arrays.binarySearch(q, from[i]);
            to[i] = Arrays.binarySearch(q, to[i]);
        }
        Integer[] id = new Integer[n];
        for (int i = 0; i < n; i++) id[i] = i;
        Arrays.sort(id, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(to[o1], to[o2]);
            }
        });
        MinSegmentTree tree = new MinSegmentTree(q.length);
        int[] dp = new int[q.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        tree.set(start, 0);
        dp[start] = 0;
        for (int it = 0; it < n; it++) {
            int i = id[it];
            int val = tree.getMin(from[i], q.length) + cost[i];
            if (dp[to[i]] > val) {
                dp[to[i]] = val;
                tree.set(to[i], val);
            }
        }
        out.println(tree.getMin(end, q.length));
    }
}
