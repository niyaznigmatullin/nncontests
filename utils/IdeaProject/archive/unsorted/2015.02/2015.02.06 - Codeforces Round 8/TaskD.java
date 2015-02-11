package coding;

import ru.ifmo.niyaz.geometry.Circle2DDouble;
import ru.ifmo.niyaz.geometry.Point2DDouble;
import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.DoubleComparator;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int t1 = in.nextInt();
        int t2 = in.nextInt();
        Point2DDouble cinema = new Point2DDouble(in.nextInt(), in.nextInt());
        Point2DDouble home = new Point2DDouble(in.nextInt(), in.nextInt());
        Point2DDouble store = new Point2DDouble(in.nextInt(), in.nextInt());
        double shortestHome = cinema.distance(home);
        double distToStore = cinema.distance(store);
        double storeToHome = store.distance(home);
        double shortestStore = distToStore + storeToHome;
        DoubleComparator cmp = new DoubleComparator() {
            @Override
            public int compare(double a, double b) {
                return Math.abs(a - b) < 1e-11 ? 0 : a < b ? -1 : 1;
            }
        };
        if (cmp.compare(shortestHome + t2, shortestStore) >= 0) {
            out.println(Math.min(shortestHome + t2, shortestStore + t1));
        } else if (cmp.compare(shortestHome + t2 + storeToHome * 2, shortestStore + t1) <= 0) {
            out.println(shortestHome + t2);
        } else {
            double l = 0;
            double r = Math.min(distToStore + t1, shortestHome + t2);
            bsearch:
            for (int it = 0; it < 100; it++) {
                double mid = (l + r) * .5;
                Circle2DDouble f = new Circle2DDouble(new Point2DDouble(store.x, store.y), distToStore + t1 - mid);
                Circle2DDouble g = new Circle2DDouble(new Point2DDouble(home.x, home.y), shortestHome + t2 - mid);
                Circle2DDouble h = new Circle2DDouble(new Point2DDouble(cinema.x, cinema.y), mid);
                Circle2DDouble[] a = new Circle2DDouble[]{f, g, h};
//                System.out.println(mid);
//                System.out.println(f.p.distance(home) + " " + f.radius);
//                System.out.println(g.p.distance(home) + " " + g.radius);
//                System.out.println(h.p.distance(home) + " " + h.radius);
                for (int i = 0; i < 3; i++) {
                    for (int j = i + 1; j < 3; j++) {
                        for (Point2DDouble e : a[i].intersect(a[j], cmp)) {
                            if (    a[0].p.distance(e) <= a[0].radius &&
                                    a[1].p.distance(e) <= a[1].radius &&
                                    a[2].p.distance(e) <= a[2].radius) {
//                                if (    cmp.compare(a[0].p.distance(e), a[0].radius) <= 0 &&
//                                        cmp.compare(a[1].p.distance(e), a[1].radius) <= 0 &&
//                                        cmp.compare(a[2].p.distance(e), a[2].radius) <= 0) {
                                l = mid;
                                continue bsearch;
                            }
                        }
                    }
                }
                r = mid;
            }
            out.println((l + r) * .5);
        }
    }
}
