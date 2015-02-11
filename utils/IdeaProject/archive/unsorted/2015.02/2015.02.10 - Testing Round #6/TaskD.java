package coding;

import ru.ifmo.niyaz.geometry.Point2DDouble;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.awt.geom.Point2D;
import java.util.Random;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        if (n <= 4) {
            out.println("No solution");
            return;
        }
        Point2DDouble[] p = new Point2DDouble[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Point2DDouble(Math.cos(i * 2 * Math.PI / n), Math.sin(i * 2 * Math.PI / n));
        }
        Random rand = new Random(58L);
        for (int i = 0; i < n; i++) {
            p[i] = p[i].multiply(rand.nextDouble() * 100 + 600);
        }
        Point2DDouble vect = new Point2DDouble(0, 0);
        for (Point2DDouble e : p) {
            vect = vect.add(e);
        }
        vect = vect.multiply(-1);
        double angle = Math.atan2(vect.y, vect.x);
        if (angle < 0) angle += 2 * Math.PI;
        for (int i = 0; i < n; i++) {
            if (angle <= (i + 1) * 2 * Math.PI / n) {
                double ang1 = i * 2 * Math.PI / n;
                double ang2 = (i + 1) * 2 * Math.PI / n;
                double alpha = ang2 - angle;
                double beta = angle - ang1;
                double theta = Math.PI - alpha - beta;
                double a = vect.length() / Math.sin(theta) * Math.sin(alpha);
                double b = vect.length() / Math.sin(theta) * Math.sin(beta);
                Point2DDouble add1 = new Point2DDouble(Math.cos(ang1), Math.sin(ang1)).multiply(a);
                Point2DDouble add2 = new Point2DDouble(Math.cos(ang2), Math.sin(ang2)).multiply(b);
//                System.out.println(vect + " " + (add1.add(add2)));
                p[i] = p[i].add(add1);
                p[(i + 1) % n] = p[(i + 1) % n].add(add2);
                break;
            }
        }
        int cur = 0;
        Point2DDouble curPoint = new Point2DDouble(0, 0);
        Point2DDouble[] q = new Point2DDouble[n];
        for (int i = 0; i < n; i++) {
            out.println(-curPoint.x + " " + curPoint.y);
            q[i] = curPoint;
            curPoint = curPoint.add(p[cur]);
            cur = (cur + n - 1) % n;
        }
//        System.out.println(curPoint);
//        for (int i = 0; i < n; i++) {
//            int prev = (i + n - 1) % n;
//            int next = (i + 1) % n;
//            Point2DDouble v = q[prev].subtract(q[i]);
//            Point2DDouble u = q[next].subtract(q[i]);
//            double curAngle = Math.atan2(v.vmul(u), u.smul(v));
//            System.out.println("ang[" + i + "] = " + curAngle);
//        }
//        for (int i = 0; i < n; i++) {
//            int next = (i + 1) % n;
//            Point2DDouble u = q[next].subtract(q[i]);
//            System.out.println("dist[" + i + "] = " + u.length());
//        }
    }
}
