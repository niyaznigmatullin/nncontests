package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProblemB {

    static List<Integer>[] edges;
    static boolean[] was;
    static int[] up;
    static int[] depth;
    static int[] size;
    static int allV;
    static int allE;

    static int dfs(int v) {
        was[v] = true;
        int ret = 1;
        for (int i : edges[v]) {
            if (was[i]) {
                continue;
            }
            ret += dfs(i);
        }
        return ret;
    }

    static long dfs2(int v, int parent, int d) {
        depth[v] = d;
        up[v] = d;
        size[v] = 1;
        long ret = 0;
        for (int i : edges[v]) {
            if (i == parent) {
                continue;
            }
            if (depth[i] < 0) {
                ret += dfs2(i, v, d + 1);
                size[v] += size[i];
                if (up[i] >= depth[i]) {
                    ret += (long) size[i] * (allV - size[i]) - 1;
                } else {
                    ret += (long) allV * (allV - 1) / 2 - allE;
                }
                up[v] = Math.min(up[v], up[i]);
            } else {
                if (depth[i] < d) {
                    ret += (long) allV * (allV - 1) / 2 - allE;
                }
                up[v] = Math.min(up[v], depth[i]);
            }
        }
        return ret;
    }

    static long dfs3(int v, int parent, int d, long add) {
        depth[v] = d;
        up[v] = d;
        size[v] = 1;
        long ret = 0;
        for (int i : edges[v]) {
            if (i == parent) {
                continue;
            }
            if (depth[i] < 0) {
                ret += dfs3(i, v, d + 1, add);
                size[v] += size[i];
                if (up[i] >= depth[i]) {
                } else {
                    ret += add;
                }
                up[v] = Math.min(up[v], up[i]);
            } else {
                if (depth[i] < d) {
                    ret += add;
                }
                up[v] = Math.min(up[v], depth[i]);
            }
        }
        return ret;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        allV = n;
        allE = m;
        if (m < n - 1) {
            out.println(0);
            return;
        }
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
        was = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (was[i]) {
                continue;
            }
            dfs(i);
            ++count;
        }
        if (count > 2) {
            out.println(0);
            return;
        }
        up = new int[n];
        size = new int[n];
        depth = new int[n];
        Arrays.fill(depth, -1);
        if (count == 1) {
            out.println(dfs2(0, -1, 0));
        } else {
            Arrays.fill(was, false);
            long mul = 1;
            for (int i = 0; i < n; i++) {
                if (was[i]) {
                    continue;
                }
                mul = mul * dfs(i);
            }
            long answer = 0;
            for (int i = 0; i < n; i++) {
                if (depth[i] < 0) {
                    answer += dfs3(i, -1, 0, mul);
                }
            }
            out.println(answer);
        }
    }
}
