package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskCStupid {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println("[" + testNumber + "]");
        int n = in.nextInt();
        boolean[] type = new boolean[n];
        int[] id = new int[n];
        int[] all = new int[n];
        int cn = 0;
        for (int i = 0; i < n; i++) {
            type[i] = in.next().equals("E");
            id[i] = in.nextInt() - 1;
            if (id[i] >= 0) {
                all[cn++] = id[i];
            }
        }
        all = Arrays.copyOf(all, cn);
        all = ArrayUtils.sortAndUnique(all);
        for (int i = 0; i < n; i++) {
            if (id[i] >= 0) {
                id[i] = Arrays.binarySearch(all, id[i]);
            }
        }
        cn = all.length;
        boolean[][] dp = new boolean[n + 1][1 << cn];
        for (boolean[] d : dp) {
            Arrays.fill(d, true);
        }
        for (int i = 0; i < n; i++) {
            boolean[][] next = new boolean[n + 1][1 << cn];
            for (int count = 0; count <= n; count++) {
                for (int mask = 0; mask < 1 << cn; mask++) {
                    if (!dp[count][mask]) continue;
                    if (type[i]) {
                        if (id[i] >= 0) {
                            if (((mask >> id[i]) & 1) == 0)
                                next[count][mask | (1 << id[i])] = true;
                        } else {
                            if (count + 1 <= n)
                                next[count + 1][mask] = true;
                            for (int t = 0; t < cn; t++) {
                                if (((mask >> t) & 1) == 0) {
                                    next[count][mask | (1 << t)] = true;
                                }
                            }
                        }
                    } else {
                        if (id[i] >= 0) {
                            if (((mask >> id[i]) & 1) == 1) {
                                next[count][mask & (~(1 << id[i]))] = true;
                            }
                        } else {
                            if (count > 0) {
                                next[count - 1][mask] = true;
                            }
                            for (int t = 0; t < cn; t++) {
                                if (((mask >> t) & 1) == 1) {
                                    next[count][mask & (~(1 << t))] = true;
                                }
                            }
                        }
                    }
                }
            }
            dp = next;
        }
        int ans = Integer.MAX_VALUE;
        for (int count = 0; count <= n; count++)
            for (int mask = 0; mask < 1 << cn; mask++) {
                if (!dp[count][mask]) continue;
                ans = Math.min(ans, count + Integer.bitCount(mask));
            }
        out.println("Case #" + testNumber + ": " + (ans == Integer.MAX_VALUE ? "CRIME TIME" : ans));
    }
}
