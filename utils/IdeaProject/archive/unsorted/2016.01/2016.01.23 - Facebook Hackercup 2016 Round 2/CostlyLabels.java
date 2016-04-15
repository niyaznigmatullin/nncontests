package coding;

import ru.ifmo.niyaz.graphalgorithms.HungarianAlgorithm;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CostlyLabels {

    static int P;
    static int[][] C;
    static int[][][] dp;
    static List<Integer>[] edges;

    static void dfs(int v, int pv) {
        for (int i : edges[v]) {
            if (i == pv) {
                continue;
            }
            dfs(i, v);
        }
        int colors = C[v].length;
        dp[v] = new int[colors + 1][colors];
        for (int[] e : dp[v]) {
            Arrays.fill(e, Integer.MAX_VALUE);
        }
        for (int ourColor = 0; ourColor < colors; ourColor++) {
            p1 = null;
            int haveColors = colors;
            int childrenCount = edges[v].size();
            if (pv >= 0) {
                haveColors--;
                --childrenCount;
            }
            for (int parentColor = 0; parentColor <= colors; parentColor++) {
                if ((parentColor == colors) != (pv < 0)) {
                    continue;
                }
                int any = 0;
                for (int i : edges[v]) {
                    if (i == pv) continue;
                    int best = Integer.MAX_VALUE;
                    for (int j = 0; j < colors; j++) {
                        best = Math.min(best, dp[i][ourColor][j]);
                    }
                    any += best;
                }
                dp[v][parentColor][ourColor] = Math.min(dp[v][parentColor][ourColor], any + P + C[v][ourColor]);
                if (childrenCount > haveColors) {
                    continue;
                }
                int[][] matrix = new int[haveColors][haveColors];
                int curChild = 0;
                for (int i : edges[v]) {
                    if (i == pv) continue;
                    int curColor = 0;
                    for (int j = 0; j < colors; j++) {
                        if (j == parentColor) continue;
                        matrix[curColor][curChild] = dp[i][ourColor][j];
                        curColor++;
                    }
                    ++curChild;
                }
                if (parentColor != colors && p1 != null) {
                    p1[parentColor - 1] = -1;
                    for (int i = 0; i < haveColors; i++) {
                        if (p2[i] == parentColor - 1) {
                            p2[i] = -1;
                        }
                    }
                }
                p1 = null;
                getMatching(matrix);
                int sum = 0;
                for (int i = 0; i < haveColors; i++) {
                    sum += matrix[i][p1[i]];
                }
                dp[v][parentColor][ourColor] = Math.min(dp[v][parentColor][ourColor], sum + C[v][ourColor]);
            }
        }
    }

    static int[] p1, p2, row, col, from, min;
    static boolean[] was;

    static void getMatching(int[][] a) {
        int n = a.length;
        if (p1 == null || p1.length != n) {
            p1 = new int[n];
            p2 = new int[n];
            row = new int[n];
            col = new int[n];
            was = new boolean[n];
            from = new int[n];
            min = new int[n];
            int allMin = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    allMin = Math.min(allMin, a[i][j]);
                }
            }
            Arrays.fill(row, allMin);
            Arrays.fill(p1, -1);
            Arrays.fill(p2, -1);
        }
        for (int i = 0; i < n; i++) {
            if (p1[i] >= 0) {
                continue;
            }
            Arrays.fill(was, false);
            Arrays.fill(from, 0);
            Arrays.fill(min, Integer.MAX_VALUE);
            int cur = i;
            int curPair = -1;
            do {
                if (curPair >= 0) {
                    was[curPair] = true;
                    cur = p2[curPair];
                }
                int d = Integer.MAX_VALUE;
                int minPair = -1;
                for (int j = 0; j < n; j++) {
                    if (was[j]) {
                        continue;
                    }
                    int val = a[cur][j] - row[cur] - col[j];
                    if (val < min[j]) {
                        min[j] = val;
                        from[j] = curPair;
                    }
                    if (min[j] < d) {
                        d = min[j];
                        minPair = j;
                    }
                }
                row[i] += d;
                for (int j = 0; j < n; j++) {
                    if (was[j]) {
                        col[j] -= d;
                        row[p2[j]] += d;
                    } else {
                        min[j] -= d;
                    }
                }
                curPair = minPair;
            } while (p2[curPair] >= 0);
            while (from[curPair] >= 0) {
                int prev = from[curPair];
                p2[curPair] = p2[prev];
                p1[p2[prev]] = curPair;
                curPair = prev;
            }
            p2[curPair] = i;
            p1[i] = curPair;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println(testNumber);
        int n = in.nextInt();
        int k = in.nextInt();
        P = in.nextInt();
        C = in.readInt2DArray(n, k);
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i + 1 < n; i++) {
            int v = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            edges[v].add(u);
            edges[u].add(v);
        }
        dp = new int[n][][];
        dfs(0, -1);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            ans = Math.min(ans, dp[0][k][i]);
        }
        out.println("Case #" + testNumber + ": " + ans);
    }
}
