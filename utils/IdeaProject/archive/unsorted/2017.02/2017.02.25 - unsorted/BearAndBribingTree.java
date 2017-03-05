package coding;

import ru.ifmo.niyaz.DataStructures.MinSegmentTree;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class BearAndBribingTree {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int h = in.nextInt();
        int n = 1 << h;
        int k = in.nextInt();
        int[] a = in.readIntArray(n);
        MinSegmentTree tree = new MinSegmentTree(n);
        for (int i = 0; i < n; i++) {
            tree.set(i, 0);
        }
        int[] who = new int[n];
        for (int i = 0; i < n; i++) who[i] = i;
        int[] dp = new int[n];
        for (int round = 0; round < h; round++) {
            int[] ndp = new int[n];
            for (int i = 0; i < n; i += (1 << round)) {
                int left = i ^ (1 << round);
                int right = left + (1 << round);
                int curLess = left;
                int curKMore = left;
                int dpLess = Integer.MAX_VALUE;
                int dpKMore = Integer.MAX_VALUE;
                for (int j = i; j < i + (1 << round); j++) {
                    if (dp[j] == Integer.MAX_VALUE) {
                        ndp[j] = Integer.MAX_VALUE;
                        continue;
                    }
                    while (curLess < right && a[curLess] < a[j]) {
                        dpLess = Math.min(dpLess, dp[curLess++]);
                    }
                    while (curKMore < right && a[curKMore] <= a[j] + k) {
                        dpKMore = Math.min(dpKMore, dp[curKMore++]);
                    }
                    int val1 = dpLess;
                    int val2 = dpKMore;
                    if (val2 != Integer.MAX_VALUE) val2++;
                    ndp[j] = Math.min(val1, val2);
                    if (ndp[j] != Integer.MAX_VALUE) ndp[j] += dp[j];
                }
            }
            int[] nwho = new int[n];
            int[] na = new int[n];
            for (int i = 0; i < n; i += (1 << (round + 1))) {
                int left = i;
                int right = (i + (1 << round + 1));
                int mid = (i + (1 << round));
                int pos1 = left;
                int pos2 = mid;
                int pos3 = left;
                while (pos1 < mid || pos2 < right) {
                    if (pos2 >= right || (pos1 < mid && a[pos1] < a[pos2])) {
                        nwho[pos3] = who[pos1];
                        dp[pos3] = ndp[pos1];
                        na[pos3++] = a[pos1++];
                    } else {
                        nwho[pos3] = who[pos2];
                        dp[pos3] = ndp[pos2];
                        na[pos3++] = a[pos2++];
                    }
                }
            }
            a = na;
            who = nwho;
            for (int i = 0; i < n; i++) {
                tree.set(i, dp[i]);
            }
        }
        for (int i = 0; i < n; i++) {
            if (who[i] == 0) {
                out.println(dp[i] == Integer.MAX_VALUE ? -1 : dp[i]);
            }
        }
    }
}
