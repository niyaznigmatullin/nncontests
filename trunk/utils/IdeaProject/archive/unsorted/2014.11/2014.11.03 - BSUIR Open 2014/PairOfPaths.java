package coding;

import ru.ifmo.niyaz.graphalgorithms.GraphUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class PairOfPaths {

    static int[][] edges;

    static long[] allPaths;
    static long[] pathsRoot;
    static long[] pathsDown;

    static void dfs(int v, int pr) {
        pathsRoot[v] = 0;
        pathsDown[v] = 0;
        allPaths[v] = 0;
        for (int to : edges[v]) {
            if (to == pr) continue;
            dfs(to, v);
            allPaths[v] += allPaths[to];
            pathsRoot[v] += pathsDown[v] * pathsDown[to];
            pathsDown[v] += pathsDown[to];
        }
        pathsRoot[v] += pathsDown[v];
        allPaths[v] += pathsRoot[v];
        ++pathsDown[v];
    }

    static long dfs2(int v, int pr, long upDown, long upAll) {
        long ans = 0;
//        System.out.println(pr + " " + v + " " + upDown + " " + upAll);
        ans += pathsRoot[v] * upAll;
        for (int to : edges[v]) {
            if (to == pr) continue;
            ans += dfs2(to, v,
                    upDown + (pathsDown[v] - pathsDown[to]),
                    upAll + allPaths[v] - allPaths[to] + (pathsDown[v] - pathsDown[to]) * (upDown - pathsDown[to]));
            long z = pathsRoot[v] + (pathsDown[v] - pathsDown[to]) * (upDown - pathsDown[to]);
            ans += z * allPaths[to];
        }
        return ans;
    }


    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] from = new int[n - 1];
        int[] to = new int[n - 1];
        for (int i = 0; i + 1 < n; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
        }
        edges = GraphUtils.getEdgesUndirectedUnweighted(n, from, to);
        pathsRoot = new long[n];
        pathsDown = new long[n];
        allPaths = new long[n];
        dfs(0, -1);
//        System.out.println(Arrays.toString(pathsRoot));
//        System.out.println(Arrays.toString(pathsDown));
//        System.out.println(Arrays.toString(allPaths));
        long ans = dfs2(0, -1, 0, 0);
        out.println(ans / 2);
    }
}
