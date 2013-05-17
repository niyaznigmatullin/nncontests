package lib.test.on2013_04.on2013_04_13_Google_Code_Jam_2013_Qualification.Treasure;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class Treasure {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        out.print("Case #" + testNumber + ":");
        System.err.println("[" + testNumber + "]");
        int k = in.nextInt();
        int n = in.nextInt();
        int[] haveStart = new int[k];
        for (int i = 0; i < k; i++) {
            haveStart[i] = in.nextInt();
        }
        int[] open = new int[n];
        int[][] takeStart = new int[n][];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            open[i] = in.nextInt();
            if (!map.containsKey(open[i])) {
                map.put(open[i], map.size());
            }
            open[i] = map.get(open[i]);
            takeStart[i] = new int[in.nextInt()];
            for (int j = 0; j < takeStart[i].length; j++) {
                takeStart[i][j] = in.nextInt();
            }
        }
        int[][] have = new int[map.size()][1 << n];
        for (int i : haveStart) {
            if (map.containsKey(i)) {
                have[map.get(i)][0]++;
            }
        }
        int[][] take = new int[n][map.size()];
        for (int i = 0; i < n; i++) {
            for (int j : takeStart[i]) {
                if (map.containsKey(j)) {
                    take[i][map.get(j)]++;
                }
            }
        }
        for (int i = 1; i < 1 << n; i++) {
            int j = Integer.numberOfTrailingZeros(i);
            int mask = i & ~(1 << j);
            for (int d = 0; d < map.size(); d++) {
                have[d][i] = have[d][mask] + take[j][d];
            }
            have[open[j]][i]--;
        }
        boolean[] dp = new boolean[1 << n];
        dp[(1 << n) - 1] = true;
        for (int i = (1 << n) - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1 || !dp[i | (1 << j)] || have[open[j]][i] < 1) continue;
                dp[i] = true;
                break;
            }
        }
        if (!dp[0]) {
            out.println(" IMPOSSIBLE");
            return;
        }
        all: for (int i = 0; i < (1 << n) - 1; ) {
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    continue;
                }
                if (have[open[j]][i] > 0 && dp[i | (1 << j)]) {
                    out.print(" " + (j + 1));
                    i |= (1 << j);
                    continue all;
                }
            }
            throw new AssertionError();
        }
        out.println();
    }
}
