package coding;

import ru.ifmo.niyaz.geometry.*;
import ru.ifmo.niyaz.graphalgorithms.KuhnMatchingGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.DoubleComparator;

public class Boredom {

    static final DoubleComparator comp = new DoubleComparator() {
        @Override
        public int compare(double a, double b) {
            return Math.abs(a - b) / Math.max(1, Math.max(Math.abs(a), Math.abs(b))) < 1e-11 ? 0 : a < b ? -1 : 1;
        }
    };

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        double[] x = new double[n];
        for (int i = 0; i < n; i++) x[i] = in.nextDouble();
        double[] y = new double[n];
        for (int i = 0; i < n; i++) y[i] = in.nextDouble();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                all: for (int k = 0; k < n; k++) {
                    if (j == k || i == k) continue;
                    Point2DDouble[] f = new Point2DDouble[n];
                    f[0] = new Point2DDouble(x[0], y[i]);
                    f[1] = new Point2DDouble(x[1], y[j]);
                    f[2] = new Point2DDouble(x[2], y[k]);
                    Line2DDouble line1 = Line2DDouble.getPerpendicularBisection(f[0], f[1]);
                    Line2DDouble line2 = Line2DDouble.getPerpendicularBisection(f[0], f[2]);
                    Point2DDouble p = line1.intersect(line2, comp);
                    if (p == null) continue;
                    Circle2DDouble c = new Circle2DDouble(p, p.distance(f[0]));
                    for (int q = 0; q < 3; q++) {
                        for (int r = q + 1; r < 3; r++) {
                            if (comp.compare(f[q].distance(f[r]), 0) == 0) {
                                continue all;
                            }
                        }
                    }
                    boolean[] was = new boolean[n];
                    was[i] = was[j] = was[k] = true;
                    for (int q = 3; q < n; q++) {
                        for (int r = 0; r < n; r++) {
                            if (was[r]) continue;
                            f[q] = new Point2DDouble(x[q], y[r]);
                            if (!c.containsOn(f[q], comp)) {
                                f[q] = null;
                                continue;
                            }
                            for (int e = 0; e < q; e++) {
                                if (comp.compare(f[q].distance(f[e]), 0) == 0) {
                                    f[q] = null;
                                    break;
                                }
                            }
                            if (f[q] != null) {
                                was[r] = true;
                                break;
                            }
                        }
                        if (f[q] == null) {
                            continue all;
                        }
                    }
                    out.println("YES");
                    return;
                }
            }
        }
        out.println("NO");
    }
}
