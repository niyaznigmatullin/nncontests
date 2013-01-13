package lib.test.on2012_12.on2012_12_29_Codeforces_Beta_Round__69__Div__1_Only_.C___Beavermuncher_0xFF;



import ru.ifmo.niyaz.graphalgorithms.GraphUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskC {

    static int[] k;
    static int[][] edges;
    static long[] removed;
    static int[] left;

    static Comparator<Integer> comp = new Comparator<Integer>() {
        public int compare(Integer o1, Integer o2) {
            return Long.signum(removed[o2] - removed[o1]);
        }
    };

    static void dfs(int v, int p) {
        Integer[] children = new Integer[edges[v].length - (p < 0 ? 0 : 1)];
        int j = 0;
        for (int i : edges[v]) {
            if (i == p) {
                continue;
            }
            dfs(i, v);
            children[j++] = i;
        }
        Arrays.sort(children, comp);
        int have = k[v] - (p < 0 ? 0 : 1);
        long current = (p < 0 ? 0 : 1);
        for (int i : children) {
            if (have == 0) {
                break;
            }
            current += removed[i];
            --have;
        }
        for (int i : children) {
            if (have == 0) {
                break;
            }
            int f = Math.min(left[i], have);
            current += f;
            have -= f;
        }
        removed[v] = current;
        left[v] = have;
//        System.out.println(v + " " + removed[v] + " " + left[v]);
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        k = in.readIntArray(n);
        int[] from = new int[n - 1];
        int[] to = new int[n - 1];
        for (int i = 0; i + 1 < n; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
        }
        edges = GraphUtils.getEdgesUndirectedUnweighted(n, from, to);
        int root = in.nextInt() - 1;
        left = new int[n];
        removed = new long[n];
        dfs(root, -1);
        out.println(removed[root] * 2);
    }
}
