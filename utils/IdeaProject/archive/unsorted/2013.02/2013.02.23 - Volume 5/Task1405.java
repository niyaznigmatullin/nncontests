package coding;

import ru.ifmo.niyaz.geometry.Point2DDouble;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Task1405 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Point2DDouble[] p = new Point2DDouble[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Point2DDouble(in.nextDouble(), in.nextDouble());
        }
        double[] maxDist = new double[n];
        Arrays.fill(maxDist, 50 * 50);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double dist = p[i].distanceSquared(p[j]);
                maxDist[i] = Math.min(maxDist[i], dist);
                maxDist[j] = Math.min(maxDist[j], dist);
            }
        }
        for (int i = 0; i < n; i++) {
            maxDist[i] = Math.sqrt(maxDist[i]);
        }
        double maximal = 0;
        for (int i = 0; i < n; i++) {
            if (maxDist[i] < 1 - 1e-9) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                if (maxDist[j] < 1 - 1e-9) {
                    continue;
                }
                if (i == j) {
                    continue;
                }
                double dist = p[i].distance(p[j]);
                double d1 = Math.min(dist - 1, maxDist[i]);
                double d2 = Math.min(dist - d1, maxDist[j]);
                maximal = Math.max(maximal, Math.PI * (d1 * d1 + d2 * d2));
            }
        }
        out.println(maximal);
    }
}
