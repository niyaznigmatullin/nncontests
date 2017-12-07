package coding;

import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permatch {
    static List<Integer>[] edges;
    static boolean[] was;
    static int cur;

    static boolean dfs(int v, int pv) {
        was[v] = true;
        int have = 0;
        for (int to : edges[v]) {
            if (pv == to) continue;
            if (dfs(to, v)) {
                ++have;
            }
        }
        if (have > 2) {
            cur += have - 2;
            have = 2;
        }
        if (pv < 0) {
            if (have == 1) {
                ++cur;
            }
        }
        return have != 1;
    }

    public int complete(String[] board) {
        int n = board.length;
        int m = board[0].length();
        edges = new List[n + m];
        for (int i = 0; i < n + m; i++) {
            edges[i] = new ArrayList<>();
        }
        int countEdges = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i].charAt(j) == '#') {
                    ++countEdges;
                    edges[i].add(j + n);
                    edges[j + n].add(i);
                }
            }
        }
        DisjointSetUnion dsu = new DisjointSetUnion(n + m);
        for (int i = 0; i < n + m; i++) {
            for (int to : edges[i]) {
                if (i < to) {
                    if (!dsu.union(i, to)) {
                        return -1;
                    }
                }
            }
        }
        was = new boolean[n + m];
        int[] k = new int[n + m];
        int cn = 0;
        int ans = 0;
        for (int i = 0; i < n + m; i++) {
            cur = 0;
            if (!was[i]) {
                dfs(i, -1);
                k[cn++] = cur;
                ans += cur;
            }
        }
        if (n + m - 1 - countEdges < ans) return -1;
        return ans;
//        Arrays.sort(k, 0, cn);
//        for (int i = cn - 1; i >= 0; i--) {
//            int have = k[i];
//            for (int j = i - 1; have > 0 && j >= 0; j--) {
//                if (k[j] > 0) {
//                    --have;
//                    --k[j];
//                }
//            }
//            if (have != 0) return -1;
//            Arrays.sort(k, 0, i);
//        }
//        if (ans % 2 != 0) throw new AssertionError();
//        return ans / 2;
    }
}
