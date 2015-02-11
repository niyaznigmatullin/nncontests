package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class CorporateGifting {
    static List<Integer>[] edges;
    static final int K = 100;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println("[" + testNumber + "]");
        int n = in.nextInt();
        edges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        int root = -1;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt() - 1;
            if (x >= 0) {
                edges[x].add(i);
            } else {
                root = i;
            }
        }
        pref = new int[n][K];
        suf = new int[n][K];
        dfs(root);
        out.println("Case #" + testNumber + ": " + (suf[root][0] + n));
        pref = null;
        suf = null;
    }

    static int[][] pref;
    static int[][] suf;

    static void dfs(int v) {
        int[] dp = new int[K];
        for (int i = 0; i < K; i++) dp[i] = i;
        for (int e = 0; e < edges[v].size(); e++) {
            int to = edges[v].get(e);
            dfs(to);
            for (int i = 0; i < K; i++) {
                int left = i > 0 ? pref[to][i - 1]: Integer.MAX_VALUE;
                int right = i + 1 < K ? suf[to][i + 1] : Integer.MAX_VALUE;
                dp[i] += Math.min(left, right);
            }
        }
        int[] curPref = pref[v];
        for (int i = 0; i < K; i++) {
            curPref[i] = dp[i];
            if (i > 0 && curPref[i] > curPref[i - 1]) {
                curPref[i] = curPref[i - 1];
            }
        }
        int[] curSuf = suf[v];
        for (int i = K - 1; i >= 0; i--) {
            curSuf[i] = dp[i];
            if (i + 1 < K && curSuf[i] > curSuf[i + 1]) {
                curSuf[i] = curSuf[i + 1];
            }
        }
    }
}
