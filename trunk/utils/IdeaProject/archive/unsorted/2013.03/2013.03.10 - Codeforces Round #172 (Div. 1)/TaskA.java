package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        double w = in.nextInt() * .5;
        double h = in.nextInt() * .5;
        double angle = in.nextInt() * Math.PI / 180;
        double[] x = new double[]{-w, w, w, -w};
        double[] y = new double[]{-h, -h, h, h};
        Path2D.Double path = new Path2D.Double();
        for (int i = 0; i < 4; i++) {
            path.append(new Line2D.Double(x[i], y[i], x[i + 1 & 3], y[i + 1 & 3]), true);
        }
        path.closePath();
        Area a = new Area(path);
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        Path2D.Double path2 = new Path2D.Double();
        for (int i = 0; i < 4; i++) {
            double xx = x[i] * cos - y[i] * sin;
            double yy = x[i] * sin + y[i] * cos;
            x[i] = xx;
            y[i] = yy;
        }
        for (int i = 0; i < 4; i++) {
            path2.append(new Line2D.Double(x[i], y[i], x[i + 1 & 3], y[i + 1 & 3]), true);
        }
        path2.closePath();
        Area b = new Area(path2);
        a.intersect(b);
        double ans = 0;
        PathIterator it = a.getPathIterator(null);
        List<double[]> p = new ArrayList<>();
        for (; !it.isDone();) {
            double[] cur = new double[2];
            it.currentSegment(cur);
            p.add(cur);
            it.next();
        }
        p.remove(p.size() - 1);
        for (int i = 0; i < p.size(); i++) {
            double[] c = p.get(i);
            double[] d = p.get((i + 1) % p.size());
            ans += c[0] * d[1] - d[0] * c[1];
        }
        out.println(Math.abs(ans) * .5);
    }
}
