package lib.test.on2013_08.on2013_08_26_.Lab;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Lab {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int d = in.nextInt();
//        boolean f1 = false;
//        boolean f2 = false;
//        int max = Math.max(Math.max(a, b), Math.max(c, d));
//        if (a == max) {
//
//        } else if (b == max) {
//            f1 = true;
//            int t = a;
//            a = b;
//            b = t;
//            t = c;
//            c = d;
//            d = t;
//        } else if (c == max) {
//            f2 = true;
//            int t = a;
//            a = c;
//            c = t;
//            t = b;
//            b = d;
//            d = t;
//        } else {
//            f1 = true;
//            f2 = true;
//            int na = d;
//            int nb = c;
//            int nc = b;
//            int nd = a;
//            a = na;
//            b = nb;
//            c = nc;
//            d = nd;
//        }
//        double p1p2 = 1. * (a + b) / (c + d);
//        double q1q2 = 1. * (a + c) / (b + d);
        double l = 1;
        double r = 2;
        for (int it = 0; it < 100; it++) {
            double m1 = (l + l + r) / 3.;
            double m2 = (l + r + r) / 3.;
            if (f(m1, a, b, c, d) > f(m2, a, b, c, d)) {
                l = m1;
            } else {
                r = m2;
            }
        }
        double p1 = (l + r) * .5;
        out.println(f(p1, a, b, c, d));
        double q1 = Math.max(a / p1, c / p2);
        double q2 = Math.max(b / p1, d / p2);
        out.println(p1 + " " + p2 + " " + q1 + " " + q2);
    }

    static double f(double p1, int a, int b, int c, int d) {
        double l = 0;
        double r = 100000;
        for (int it = 0; it < 100; it++) {
            double m1 = (l + l + r) / 3.;
            double m2 = (l + r + r) / 3.;
            if (f(p1, m1, a, b, c, d) > f(p1, m2, a, b, c, d)) {
                l = m1;
            } else {
                r = m2;
            }
        }
        p2 = (l + r) * .5;
        return f(p1, p2, a, b, c, d);
    }

    static double p2;

    static double f(double p1, double p2, int a, int b, int c, int d) {
        double q1 = Math.max(a / p1, c / p2);
        double q2 = Math.max(b / p1, d / p2);
        return (p1 + p2) * (q1 + q2);
    }
}
