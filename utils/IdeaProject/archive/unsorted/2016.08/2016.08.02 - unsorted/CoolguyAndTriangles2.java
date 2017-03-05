package coding;

import ru.ifmo.niyaz.geometry.Point2DDouble;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.List;

public class CoolguyAndTriangles2 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Area area = new Area();
        for (int i = 0; i < n; i++) {
            int[] x = new int[3];
            int[] y = new int[3];
            for (int j = 0; j < 3; j++) {
                x[j] = in.nextInt();
                y[j] = in.nextInt();
            }
            Polygon p = new Polygon(x, y, 3);
            area.add(new Area(p));
        }
        PathIterator it = area.getPathIterator(null);
        double[] z = new double[6];
        List<List<Point2DDouble>> all = new ArrayList<>();
        List<Point2DDouble> current = new ArrayList<>();
        while (!it.isDone()) {
            int f = it.currentSegment(z);
            if (f == PathIterator.SEG_MOVETO) {
                current.add(new Point2DDouble(z[0], z[1]));
            } else if (f == PathIterator.SEG_LINETO) {
                current.add(new Point2DDouble(z[0], z[1]));
            } else if (f == PathIterator.SEG_CLOSE) {
                all.add(current);
                current = new ArrayList<>();
            } else throw new AssertionError();
            it.next();
        }
        double ans = 0;
        for (List<Point2DDouble> f : all) {
            double cur = 0;
            for (int i = 0; i < f.size(); i++) {
                int j = (i + 1) % f.size();
                cur += f.get(i).x * f.get(j).y - f.get(j).x * f.get(i).y;
            }
            cur = Math.abs(cur);
            ans += cur;
        }
        out.println(ans * .5);
    }
}
