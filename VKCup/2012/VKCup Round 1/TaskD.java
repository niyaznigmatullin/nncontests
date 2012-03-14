package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;
import sun.jvm.hotspot.runtime.linux.LinuxSignals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskD {

    static List<Integer>[] edges;
    static int[][] countDown;
    static long[] dp;
    static int k;

    static void dfs(int v, int parent) {
        List<Integer> edge = edges[v];
        for (int i1 = 0, edgeSize = edge.size(); i1 < edgeSize; i1++) {
            int i = edge.get(i1);
            if (i == parent) {
                continue;
            }
            dfs(i, v);
        }
        countDown[v] = new int[k + 1];
        List<Integer> edge1 = edges[v];
        for (int i1 = 0, edge1Size = edge1.size(); i1 < edge1Size; i1++) {
            int i = edge1.get(i1);
            if (i == parent) {
                continue;
            }
            int[] nextCountDown = countDown[v].clone();
            for (int j = 0; j + 1 <= k; j++) {
                int val = countDown[i][j];
                dp[v] += (long) val * countDown[v][k - j - 1];
                nextCountDown[j + 1] += val;
            }
            countDown[v] = nextCountDown;
        }
        dp[v] += countDown[v][k];
        countDown[v][0] = 1;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        k = in.nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n - 1; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            edges[from].add(to);
            edges[to].add(from);
        }
        countDown = new int[n][];
        dp = new long[n];
        dfs(0, -1);
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += dp[i];
        }
        out.println(ans);
    }
}
