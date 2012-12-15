package graphalgorithms;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 20.01.12
 * Time: 21:03
 * To change this template use File | Settings | File Templates.
 */
public class HungarianAlgorithm {

    static public int[] getMatching(int[][] a) {
        int n = a.length;
        int[] p1 = new int[n];
        int[] p2 = new int[n];
        int[] row = new int[n];
        int[] col = new int[n];
        int allMin = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                allMin = Math.min(allMin, a[i][j]);
            }
        }
        Arrays.fill(row, allMin);
        Arrays.fill(p1, -1);
        Arrays.fill(p2, -1);
        for (int i = 0; i < n; i++) {
            if (p1[i] >= 0) {
                continue;
            }
            boolean[] was = new boolean[n];
            int[] from = new int[n];
            int[] min = new int[n];
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
        return p1;
    }
}
