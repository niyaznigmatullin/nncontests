package coding;

import ru.ifmo.niyaz.DataStructures.MaxSegmentTree;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskD {

    static class Edge {
        int from;
        int to;
        int w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int m = in.nextInt();
        int[][] edges = new int[n][n];
        for (int[] e : edges) Arrays.fill(e, Integer.MAX_VALUE);
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int w = in.nextInt();
            edges[from][to] = Math.min(edges[from][to], w);
        }
        int[][][] dp = new int[n][n + 1][n + 1];
        for (int[][] e : dp) {
            for (int[] f : e) {
                Arrays.fill(f, Integer.MAX_VALUE);
            }
        }
        for (int i = 0; i < n; i++) {
            dp[i][n][n] = 0;
        }
        int[][][] ndp = new int[n][n + 1][n + 1];
        int[][] bestLeft = new int[n][n + 1];
        int[][] bestRight = new int[n + 1][n];
        for (int it = 1; it < k; it++) {
            for (int[] e : bestLeft) Arrays.fill(e, Integer.MAX_VALUE);
            for (int[] e : bestRight) Arrays.fill(e, Integer.MAX_VALUE);
            for (int[][] e : ndp) {
                for (int[] f : e) Arrays.fill(f, Integer.MAX_VALUE);
            }
            for (int oldLeft_ = -1; oldLeft_ < n; oldLeft_++) {
                int oldLeft = oldLeft_ == -1 ? n : oldLeft_;
                for (int left = oldLeft_ + 1; left < n; left++) {
                    int[] bLLeft = bestLeft[left];
                    for (int right = left + 1; right <= n; right++) {
                        bLLeft[right] = Math.min(bLLeft[right], dp[left][oldLeft][right]);
                    }
                }
            }
            for (int left_ = -1; left_ < n; left_++) {
                int left = left_ == -1 ? n : left_;
                int[] bRLeft = bestRight[left];
                for (int right = left_ + 1; right < n; right++) {
                    for (int oldRight = right + 1; oldRight <= n; oldRight++) {
                        bRLeft[right] = Math.min(bRLeft[right], dp[right][left][oldRight]);
                    }
                }
            }
            for (int left_ = -1; left_ < n; left_++) {
                int left = left_ == -1 ? n : left_;
                int[] bLeft = left == n ? null : bestLeft[left];
                int[] eLeft = left == n ? null : edges[left];
                for (int v = left_ + 1; v < n; v++) {
                    for (int right = v + 1; right <= n; right++) {
                        int[] bRight = right == n ? null : bestRight[left];
                        int[] eRight = right == n ? null : edges[right];
                        int cur = Integer.MAX_VALUE;
                        if (left != n && eLeft[v] != Integer.MAX_VALUE) {
//                            int best = Integer.MAX_VALUE;
//                            for (int oldLeft_ = -1; oldLeft_ < left; oldLeft_++) {
//                                int oldLeft = oldLeft_ == -1 ? n : oldLeft_;
//                                best = Math.min(best, dp[left][oldLeft][right]);
//                            }
                            int best = bLeft[right];
                            if (best != Integer.MAX_VALUE) {
                                cur = Math.min(cur, best + eLeft[v]);
                            }
                        }
                        if (right != n && eRight[v] != Integer.MAX_VALUE) {
//                            int best = Integer.MAX_VALUE;
//                            for (int oldRight = right + 1; oldRight <= n; oldRight++) {
//                                best = Math.min(best, dp[right][left][oldRight]);
//                            }
                            int best = bRight[right];
                            if (best != Integer.MAX_VALUE) {
                                cur = Math.min(cur, best + eRight[v]);
                            }
                        }
                        ndp[v][left][right] = cur;
                    }
                }
            }
            int[][][] t = dp;
            dp = ndp;
            ndp = t;
        }
        int ans = Integer.MAX_VALUE;
        for (int[][] e : dp)
            for (int[] f : e)
                for (int z : f) ans = Math.min(ans, z);
        out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}
