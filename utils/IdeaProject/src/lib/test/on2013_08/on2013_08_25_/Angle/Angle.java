package lib.test.on2013_08.on2013_08_25_.Angle;



import ru.ifmo.niyaz.geometry.Point2DDouble;
import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Angle {

    static final double EPS = 1e-8;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        while (true) {
            int n = in.nextInt();
            if (n == 0) break;
            Point2DInteger[] p = new Point2DInteger[n];
            for (int i = 0; i < n; i++) {
                p[i] = new Point2DInteger(in.nextInt(), in.nextInt());
            }
            double ans = Double.POSITIVE_INFINITY;
            double angle0 = 0;
            for (int i = 0; i + 1 < n; i++) {
                double cur = 0;
                for (int j = 0; j + 1 < n; j++) {
                    int x1 = p[i + 1].x - p[i].x;
                    int y1 = p[i + 1].y - p[i].y;
                    int x2 = p[j + 1].x - p[j].x;
                    int y2 = p[j + 1].y - p[j].y;
                    int vmul = x1 * y2 - x2 * y1;
                    int smul = x1 * x2 + y1 * y2;
                    if (vmul == 0 && smul > 0) {
                        continue;
                    }
                    double angle = Math.atan2(vmul, smul);
                    if (angle < 0) {
                        angle += 2 * Math.PI;
                    }
                    cur += angle;
                    while (cur >= 2 * Math.PI - EPS) cur -= 2 * Math.PI;
                }
                if (ans > cur) {
                    ans = cur;
                    angle0 = -Math.atan2(p[i + 1].y - p[i].y, p[i + 1].x - p[i].x);
                }
            }
            if (n == 1) ans = 0;
            while (angle0 < -EPS) angle0 += 2 * Math.PI;
            if (angle0 < 0) angle0 = 0;
            double sin = Math.sin(angle0);
            double cos = Math.cos(angle0);
            out.println(ans);
            out.println(angle0);
            for (int i = 0; i < n; i++) {
                double x = p[i].x * cos - p[i].y * sin;
                double y = p[i].x * sin + p[i].y * cos;
                out.println(x + " " + y);
            }
            out.println();
        }
    }
}
