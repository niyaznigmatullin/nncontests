package coding;

import ru.ifmo.niyaz.geometry.Circle2DDouble;
import ru.ifmo.niyaz.geometry.Point2DDouble;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.DoubleComparator;
import sun.print.PSPrinterJob;

import java.util.Arrays;
import java.util.Comparator;

public class CircleIntersect {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int k = in.nextInt();
        final DoubleComparator dblComp = new DoubleComparator() {
            @Override
            public int compare(double a, double b) {
                return Math.abs(a - b) < 1e-8 ? 0 : a < b ? -1 : 1;
            }
        };
        for (int i = 0; i < k; i++) {
            Circle2DDouble c1 = new Circle2DDouble(new Point2DDouble(in.nextDouble(), in.nextDouble()), in.nextDouble());
            Circle2DDouble c2 = new Circle2DDouble(new Point2DDouble(in.nextDouble(), in.nextDouble()), in.nextDouble());
            Point2DDouble[] p = c1.intersect(c2, dblComp);
            if (p == null) {
                out.println("I can't count them - too many points :(");
            } else if (p.length == 0) {
                out.println("There are no points!!!");
            } else {
                out.println("There are only " + p.length + " of them....");
                Arrays.sort(p, new Comparator<Point2DDouble>() {
                    @Override
                    public int compare(Point2DDouble o1, Point2DDouble o2) {
                        int c = dblComp.compare(o1.x, o2.x);
                        if (c != 0) {
                            return c;
                        }
                        return dblComp.compare(o1.y, o2.y);
                    }
                });
                for (Point2DDouble e : p) {
                    out.println(e.x + " " + e.y);
                }
            }
            out.println();
        }
    }
}
