package coding;

import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;
import ru.ifmo.niyaz.math.MathUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DengklekGaneshAndTree {

    static final int MOD = 1000000007;

    static int add(int a, int b) {
        a += b;
        if (a >= MOD) a -= MOD;
        return a;
    }

    static int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }

    public int getCount(String labels, int[] parents) {
        int n = labels.length();
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        int[] depth = new int[n];
        int maxDepth = 0;
        for (int i = 0; i < parents.length; i++) {
            int from = parents[i];
            int to = i + 1;
            depth[to] = depth[from] + 1;
            if (labels.charAt(from) == labels.charAt(to)) {
                dsu.union(from, to);
            }
            maxDepth = Math.max(maxDepth, depth[to]);
        }
        int[] minLevel = new int[n];
        Arrays.fill(minLevel, Integer.MAX_VALUE);
        int[] maxLevel = new int[n];
        for (int i = 0; i < n; i++) {
            minLevel[dsu.get(i)] = Math.min(minLevel[dsu.get(i)], depth[i]);
            maxLevel[dsu.get(i)] = Math.max(maxLevel[dsu.get(i)], depth[i]);
        }
        List<Integer>[] edges = new List[maxDepth + 1];
        for (int i = 0; i < edges.length; i++) edges[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (dsu.get(i) != i) continue;
            edges[maxLevel[i]].add(minLevel[i]);
        }
        int[] dp = new int[maxDepth + 2];
        dp[0] = 1;
        int[] TWO = new int[n + 1];
        TWO[0] = 1;
        for (int i = 1; i <= n; i++) {
            TWO[i] = mul(TWO[i - 1], 2);
        }
        for (int i = 0; i <= maxDepth; i++) {
            Collections.sort(edges[i]);
            int pos = edges[i].size() - 1;
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                sum = add(sum, dp[j]);
                while (pos >= 0 && edges[i].get(pos) >= j) {
                    dp[i + 1] = add(dp[i + 1], mul(sum, TWO[edges[i].size() - pos - 1]));
                    --pos;
                }
            }
        }
        return dp[maxDepth + 1];
    }
}
