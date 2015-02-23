package coding;

import ru.ifmo.niyaz.DataStructures.MaxSegmentTree;
import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int d = in.nextInt();
        long[] h = new long[n];
        for (int i = 0; i < n; i++) {
            h[i] = in.nextLong();
        }
        long[] xs = h.clone();
        xs = ArrayUtils.sortAndUnique(xs);
        MaxSegmentTree tree = new MaxSegmentTree(xs.length);
        int[] who = new int[xs.length];
        Arrays.fill(who, -1);
        int[] from = new int[n];
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            {
                int left = -1;
                int right = xs.length;
                while (left < right - 1) {
                    int mid = left + right >> 1;
                    if (xs[mid] <= h[i] - d) {
                        left = mid;
                    } else {
                        right = mid;
                    }
                }
                int got = tree.getMax(0, right);
                if (got != Integer.MIN_VALUE && got + 1 > dp[i]) {
                    dp[i] = got + 1;
                    from[i] = who[tree.getMaxId(0, right)];
                }
            }
            {
                int left = -1;
                int right = xs.length;
                while (left < right - 1) {
                    int mid = left + right >> 1;
                    if (xs[mid] >= h[i] + d) {
                        right = mid;
                    } else {
                        left = mid;
                    }
                }
                int got = tree.getMax(right, xs.length);
                if (got != Integer.MIN_VALUE && got + 1 > dp[i]) {
                    dp[i] = got + 1;
                    int maxId = tree.getMaxId(right, xs.length);
                    from[i] = who[maxId];
                }
            }
            int id = Arrays.binarySearch(xs, h[i]);
            if (who[id] < 0 || dp[who[id]] < dp[i]) {
                who[id] = i;
                tree.set(id, dp[i]);
            }
        }
//        System.out.println(Arrays.toString(who));
//        System.out.println(Arrays.toString(from));
        int ansI = -1;
        for (int i = 0; i < n; i++) {
            if (ansI < 0 || dp[i] > dp[ansI]) ansI = i;
        }
        int[] ans = new int[n];
        int ac = 0;
        for (int i = ansI; ;) {
            ans[ac++] = i;
            if (dp[i] == 1) {
                break;
            }
            i = from[i];
        }
        out.println(ac);
        for (int i = ac - 1; i >= 0; i--) {
            if (i + 1 < ac) out.print(' ');
            out.print(ans[i] + 1);
        }
        out.println();
    }
}
