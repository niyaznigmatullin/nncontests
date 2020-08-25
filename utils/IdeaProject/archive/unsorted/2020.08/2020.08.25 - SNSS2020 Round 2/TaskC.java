package coding;

import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.graphalgorithms.TwoSATSolver;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Point2DInteger[] p1 = new Point2DInteger[n];
        Point2DInteger[] p2 = new Point2DInteger[n];
        for (int i = 0; i < n; i++) {
            p1[i] = new Point2DInteger(in.nextInt(), in.nextInt());
            p2[i] = new Point2DInteger(in.nextInt(), in.nextInt());
        }
        Point2DInteger[] q = new Point2DInteger[m];
        for (int i = 0; i < m; i++) {
            q[i] = new Point2DInteger(in.nextInt(), in.nextInt());
        }
        double[][] distances = calcDists(q);
        double[][] d = new double[n + n][n + n];
        for (double[] e : d) {
            Arrays.fill(e, Double.POSITIVE_INFINITY);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                for (int pi = 0; pi < m; pi++) {
                    for (int pj = 0; pj < m; pj++) {
                        d[i][j] = Math.min(d[i][j], p1[i].distance(q[pi]) + distances[pi][pj] + q[pj].distance(p1[j]));
                        d[i][j + n] = Math.min(d[i][j + n], p1[i].distance(q[pi]) + distances[pi][pj] + q[pj].distance(p2[j]));
                        d[i + n][j] = Math.min(d[i + n][j], p2[i].distance(q[pi]) + distances[pi][pj] + q[pj].distance(p1[j]));
                        d[i + n][j + n] = Math.min(d[i + n][j + n], p2[i].distance(q[pi]) + distances[pi][pj] + q[pj].distance(p2[j]));
                    }
                }
            }
        }
        double left = 0;
        double right = 1e6;
        for (int it = 0; it < 100; it++) {
            double mid = (left + right) * .5;
            TwoSATSolver solver = new TwoSATSolver(n, n * n);
            for (int i = 0; i < n + n; i++) {
                for (int j = 0; j < n + n; j++) {
                    int v = i < n ? i : ~(i - n);
                    int u = j < n ? j : ~(j - n);
                    if (v == u || ~v == u) continue;
                    if (d[i][j] > mid) {
                        solver.addClause(~v, ~u);
                    }
                }
            }
            boolean[] ans = solver.solve();
            if (ans == null) {
                left = mid;
            } else {
                right = mid;
            }
        }
        out.println((left + right) * .5);
    }

    static double[][] calcDists(Point2DInteger[] q) {
        int n = q.length;
        double[][] ret = new double[n][n];
        double[][] d = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                d[i][j] = q[i].distance(q[j]);
            }
        }
        for (int start = 0; start < n; start++) {
            double[][] dp = new double[n][1 << n];
            for (double[] e : dp) Arrays.fill(e, Double.POSITIVE_INFINITY);
            for (int mask = 1; mask < 1 << n; mask++) {
                if ((mask & (mask - 1)) == 0) {
                    if (mask != (1 << start)) {
                        continue;
                    }
                    dp[start][mask] = 0;
                    continue;
                }
                for (int last = 0; last < n; last++) {
                    if (((mask >> last) & 1) == 0) continue;
                    double best = Double.POSITIVE_INFINITY;
                    for (int prelast = 0; prelast < n; prelast++) {
                        if (last == prelast || ((mask >> prelast) & 1) == 0) continue;
                        best = Math.min(best, dp[prelast][mask & ~(1 << last)] + d[prelast][last]);
                    }
                    dp[last][mask] = best;
                }
            }
            for (int i = 0; i < n; i++) {
                ret[start][i] = dp[i][(1 << n) - 1];
            }
        }
        return ret;
    }

}
