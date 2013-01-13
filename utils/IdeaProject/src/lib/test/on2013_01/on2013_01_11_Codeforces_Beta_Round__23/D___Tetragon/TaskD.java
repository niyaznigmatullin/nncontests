package lib.test.on2013_01.on2013_01_11_Codeforces_Beta_Round__23.D___Tetragon;



import ru.ifmo.niyaz.geometry.Line2DDouble;
import ru.ifmo.niyaz.geometry.Point2DDouble;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.DoubleComparator;

public class TaskD {

    static final double EPS = 1e-8;

    DoubleComparator dblcmp = new DoubleComparator() {
        public int compare(double a, double b) {
            return Math.abs(a - b) < EPS ? 0 : a < b ? -1 : 1;
        }
    };

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        Point2DDouble[] three = new Point2DDouble[3];
        for (int i = 0; i < 3; i++) {
            three[i] = new Point2DDouble(in.nextInt(), in.nextInt());
        }
        if (dblcmp.compare(three[0].subtract(three[1]).vmul(three[0].subtract(three[2])), 0) == 0) {
            out.println("NO");
            out.println();
            return;
        }
        all:
        for (int c = 0; c < 3; c++) {
            Point2DDouble[] z = null;
            for (int i = 0; i < 3; i++) {
                if (c == i) {
                    continue;
                }
                int j = 3 - i - c;
                Point2DDouble first = three[i];
                Point2DDouble second = three[c];
                Point2DDouble third = three[j];
                if (dblcmp.compare(0, second.subtract(first).vmul(third.subtract(second))) < 0) {
                    z = new Point2DDouble[]{first, second, third};
                    break;
                }
            }
            if (z == null) {
                continue;
            }
            Line2DDouble line1 = Line2DDouble.getPerpendicularBisection(z[1].add(z[1]).subtract(z[0]), z[1]);
            Line2DDouble line2 = Line2DDouble.getPerpendicularBisection(z[1], z[2]);
            Point2DDouble p1 = line1.intersect(line2, dblcmp);
            if (p1 == null) {
                continue;
            }
            Point2DDouble p2 = z[2].add(z[2]).subtract(p1);
            Point2DDouble p4 = z[1].add(z[1]).subtract(p1);
            Point2DDouble p3 = z[0].add(z[0]).subtract(p4);
            Point2DDouble[] p = new Point2DDouble[]{p1, p2, p3, p4};
            for (int i = 0; i < 4; i++) {
                double w = p[i + 1 & 3].subtract(p[i]).vmul(p[i].subtract(p[i - 1 & 3]));
                if (dblcmp.compare(w, 0) >= 0) {
                    continue all;
                }
            }
            out.println("YES");
            for (Point2DDouble e : p) {
                out.print(e.x + " " + e.y + " ");
            }
            out.println();
            return;
        }
        out.println("NO\n");
    }
}
