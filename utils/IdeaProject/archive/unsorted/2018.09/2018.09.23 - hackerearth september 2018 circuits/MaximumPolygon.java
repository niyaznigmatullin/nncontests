package coding;

import ru.ifmo.niyaz.geometry.GeometryAlgorithms;
import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Locale;

public class MaximumPolygon {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Point2DInteger[] p = new Point2DInteger[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Point2DInteger(in.nextInt(), in.nextInt());
        }
        Point2DInteger[] hull = GeometryAlgorithms.convexHull(p);
        if (hull.length <= m) {
            double area = GeometryAlgorithms.doubledArea(hull) * .5;
            double perimeter = 0;
            for (int i = 0; i < hull.length; i++) {
                perimeter += hull[i].distance(hull[(i + 1) % hull.length]);
            }
            out.printf(Locale.US, "%.4f", area / perimeter);
            return;
        }
        n = hull.length;
        p = hull;
        double left = 0;
        double right = 1e10;
        double[][] dp = new double[m][n];
        double[][] distance = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                distance[i][j] = p[i].distance(p[j]);
            }
        }
        double[][][] area = new double[n][n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    area[i][j][k] = Math.abs(GeometryAlgorithms.vmulFromPoint(p[i], p[j], p[k])) * .5;
                }
            }
        }
        for (int it = 0; it < 70; it++) {
            double mid = (left + right) * .5;
            boolean found = false;
            for (int start = 0; start < n; start++) {
                dp[0][start] = 0;
                for (int i = start + 1; i < n; i++) dp[0][i] = Double.NEGATIVE_INFINITY;
                double[][] areaStart = area[start];
                for (int k = 0; k + 1 < m; k++) {
                    double[] curDP = dp[k];
                    double[] nextDP = dp[k + 1];
                    nextDP[start] = Double.NEGATIVE_INFINITY;
                    for (int last = start + 1; last < n && last < start + k + 1; last++) nextDP[last] = Double.NEGATIVE_INFINITY;
                    for (int last = start + k + 1; last < n; last++) {
                        double current = Double.NEGATIVE_INFINITY;
                        for (int prev = start; prev < last; prev++) {
                            current = Math.max(current, curDP[prev] + areaStart[prev][last] - distance[prev][last] * mid);
                        }
                        nextDP[last] = current;
                    }
                }
                double[] lastDP = dp[m - 1];
                for (int i = start; i < n; i++) {
                    double value = lastDP[i] - distance[start][i] * mid;
                    if (value >= 0) {
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }
            if (found) {
                left = mid;
            } else {
                right = mid;
            }
        }
        out.printf(Locale.US, "%.4f", (left + right) * .5);

    }
}
