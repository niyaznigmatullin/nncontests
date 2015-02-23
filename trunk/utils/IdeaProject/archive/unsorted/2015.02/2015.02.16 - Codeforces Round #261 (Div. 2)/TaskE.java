package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] from = new int[m];
        int[] to = new int[m];
        final int[] w = new int[m];
        Integer[] id = new Integer[m];
        for (int i = 0; i < m; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
            w[i] = in.nextInt();
            id[i] = i;
        }
        Arrays.sort(id, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(w[o1], w[o2]);
            }
        });
        int[] dp = new int[n];
        int[] cur = new int[m];
        for (int it = 0; it < m; ) {
            int jt = it;
            while (jt < m && w[id[it]] == w[id[jt]]) {
                ++jt;
            }
            for (int k = it; k < jt; k++) {
                int i = id[k];
                cur[k] = dp[from[i]] + 1;
            }
            while (it < jt) {
                int i = id[it];
                dp[to[i]] = Math.max(dp[to[i]], cur[it]);
                it++;
            }
        }
        int ans = 0;
        for (int i : dp) ans = Math.max(ans, i);
        out.println(ans);
    }
}
