package coding;

import ru.ifmo.niyaz.geometry.Point3DDouble;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.DoubleComparator;

import java.util.Arrays;

public class BuildingACube {
    static
    DoubleComparator dbl = new DoubleComparator() {
        @Override
        public int compare(double a, double b) {
            return Math.abs(a - b) < 1e-7 ? 0 : a < b ? -1 : 1;
        }
    };


    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        double a = in.nextDouble();
        double b = in.nextDouble();
        double c = in.nextDouble();
        double d = in.nextDouble();
        double[] dists = new double[8];
        for (int i = 0; i < 8; i++) {
            dists[i] = in.nextDouble();
        }
        for (int mask = 0; mask < 1 << 8; mask++) {
            double[] cd = dists.clone();
            for (int i = 0; i < 8; i++) {
                if (((mask >> i) & 1) == 1) {
                    cd[i] = -cd[i];
                }
            }
            Arrays.sort(cd);
            double[] h = new double[3];
            h[0] = cd[1] - cd[0];
            h[1] = cd[2] - cd[0];
            if (dbl.compare(cd[3] - cd[0], h[0] + h[1]) == 0) {
                h[2] = cd[4] - cd[0];
            } else {
                h[2] = cd[3] - cd[0];
            }
            double[] gotD = new double[8];
            for (int m = 0; m < 8; m++) {
                double cur = 0;
                for (int i = 0; i < 3; i++) {
                    if (((m >> i) & 1) == 1) {
                        cur += h[i];
                    }
                }
                gotD[m] = cur;
            }
            Arrays.sort(gotD);
            boolean ok = true;
            for (int i = 0; i < 8; i++) {
                if (dbl.compare(gotD[i], cd[i] - cd[0]) != 0) {
                    ok = false;
                    break;
                }
            }
            if (!ok) continue;
            double ssqr = 0;
            for (int i = 0; i < 3; i++) ssqr += h[i] * h[i];
            ssqr = Math.sqrt(ssqr);
            for (int i = 0; i < 3; i++) h[i] /= ssqr;
            Point3DDouble v1 = new Point3DDouble(h[0], h[1], h[2]);
            Point3DDouble v2 = getSecond(v1);
            Point3DDouble v3 = v1.vmul(v2);
            Point3DDouble s1 = getCoordinates(new Point3DDouble(1, 0, 0), v1, v2, v3).multiply(ssqr);
            Point3DDouble s2 = getCoordinates(new Point3DDouble(0, 1, 0), v1, v2, v3).multiply(ssqr);
            Point3DDouble s3 = getCoordinates(new Point3DDouble(0, 0, 1), v1, v2, v3).multiply(ssqr);
            Point3DDouble u1 = new Point3DDouble(a, b, c);
            u1 = u1.multiply(1. / u1.length());
            Point3DDouble u2 = getSecond(u1);
            Point3DDouble u3 = u1.vmul(u2);
            Point3DDouble z1 = u1.multiply(s1.x).add(u2.multiply(s1.y)).add(u3.multiply(s1.z));
            Point3DDouble z2 = u1.multiply(s2.x).add(u2.multiply(s2.y)).add(u3.multiply(s2.z));
            Point3DDouble z3 = u1.multiply(s3.x).add(u2.multiply(s3.y)).add(u3.multiply(s3.z));
            Point3DDouble onPlane = new Point3DDouble(a, b, c).multiply(-d / Math.sqrt(a * a + b * b + c * c));
            onPlane = onPlane.subtract(u1.multiply(cd[0]));
            Point3DDouble[] ans = new Point3DDouble[8];
            Point3DDouble[] z = new Point3DDouble[]{z1, z2, z3};
            for (int m = 0; m < 8; m++) {
                Point3DDouble cur = new Point3DDouble(0, 0, 0);
                for (int i = 0; i < 3; i++) {
                    if (((m >> i) & 1) == 0) {
                        cur = cur.add(z[i]);
                    }
                }
                ans[m] = cur.add(onPlane);
            }
            boolean[] was = new boolean[8];
            for (int i = 0; i < 8; i++) {
                boolean found = false;
                for (int j = 0; j < 8; j++) {
                    if (was[j]) continue;
                    if (dbl.compare(u1.smul(ans[j]), dists[i]) == 0) {
                        was[j] = true;
                        out.println(ans[j].x + " " + ans[j].y + " " + ans[j].z);
                        found = true;
                        break;
                    }
                }
                if (!found) throw new AssertionError();
            }
            return;
        }
    }

    Point3DDouble getCoordinates(Point3DDouble p, Point3DDouble v1, Point3DDouble v2, Point3DDouble v3) {
        return new Point3DDouble(p.smul(v1), p.smul(v2), p.smul(v3));
    }

    private Point3DDouble getSecond(Point3DDouble v1) {
        Point3DDouble v2;
        if (dbl.compare(v1.x, 0) != 0) {
            v2 = new Point3DDouble(0, 1, 0);
        } else if (dbl.compare(v1.y, 0) != 0) {
            v2 = new Point3DDouble(1, 0, 0);
        } else if (dbl.compare(v1.z, 0) != 0) {
            v2 = new Point3DDouble(1, 0, 0);
        } else {
            throw new AssertionError();
        }
        v2 = v2.subtract(v1.multiply(v2.smul(v1)));
        v2 = v2.multiply(1. / v2.length());
        return v2;
    }
}
