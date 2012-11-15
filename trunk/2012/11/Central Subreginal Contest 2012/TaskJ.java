package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskJ {

    static List<Integer>[] edges;
    static int[] depth;
    static int[] parent;
    static int[] up;
    static int[] upBridge;
    static int[] down1;
    static int[] w1;
    static int[] w2;
    static int[] down2;
    static boolean[] was;

    static void dfs(int v, int p, int d) {
        parent[v] = p;
        depth[v] = d;
        up[v] = d;
        boolean wasParent = false;
        for (int i : edges[v]) {
            if (i == p && !wasParent) {
                wasParent = true;
                continue;
            }
            if (depth[i] < 0) {
                dfs(i, v, d + 1);
                up[v] = Math.min(up[v], up[i]);
                if (up[i] > depth[v]) {
                    upBridge[i] = 1;
                }
            } else {
                up[v] = Math.min(up[v], depth[i]);
            }
        }
    }

    static void dfs(int v) {
        was[v] = true;
        int bestFirst = -1;
        int bestSecond = -1;
        int go1 = -1;
        int go2 = -1;
        for (int i : edges[v]) {
            if (was[i]) {
                continue;
            }
            dfs(i);
            int cur = down1[i] + upBridge[i];
            if (cur > bestFirst) {
                bestSecond = bestFirst;
                go2 = go1;
                bestFirst = cur;
                go1 = i;
            } else if (cur > bestSecond) {
                bestSecond = cur;
                go2 = i;
            }
        }
        down1[v] = Math.max(0, bestFirst);
        down2[v] = Math.max(0, bestSecond);
        w1[v] = go1;
        w2[v] = go2;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            edges[from].add(to);
            edges[to].add(from);
        }
        up = new int[n];
        upBridge = new int[n];
        depth = new int[n];
        parent = new int[n];
        down1 = new int[n];
        down2 = new int[n];
        w1 = new int[n];
        w2 = new int[n];
        Arrays.fill(depth, -1);
        dfs(0, -1, 0);
        was = new boolean[n];
        dfs(0);
        int bestV = -1;
        for (int i = 0; i < n; i++) {
            if (bestV < 0 || down1[bestV] + down2[bestV] < down1[i] + down2[i]) {
                bestV = i;
            }
        }
        int first = bestV;
        while (w1[first] >= 0) {
            first = w1[first];
        }
        int second = bestV;
        if (w2[second] >= 0) {
            second = w2[second];
            while (w1[second] >= 0) {
                second = w1[second];
            }
        }
        out.println((first + 1) + " " + (second + 1));
    }
}
