package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class Bomb {

    static double dist(long x1, long y1, long x2, long y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long x1 = in.nextInt();
        long y1 = in.nextInt();
        long x2 = in.nextInt();
        long y2 = in.nextInt();
        long x3 = in.nextInt();
        long y3 = in.nextInt();
        long d = (x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3);
        double d1 = dist(x1, y1, x2, y2);
        double d2 = dist(x1, y1, x3, y3);
        double d3 = dist(x2, y2, x3, y3);
        if (d == 0) {
            if (d3 > d2 && d3 > d1) {
                out.println(0);
                out.println(d1);
                out.println(d2);
            } else if (d2 > d3 && d2 > d1) {
                out.println(d1);
                out.println(0);
                out.println(d3);
            } else {
                out.println(d2);
                out.println(d3);
                out.println(0);
            }
            return;
        }
        double d4 = (d3 - d1 + d2) / 2;
        out.println(d2 - d4);
        out.println(d1 - d2 + d4);
        out.println(d4);
    }
}

//1 0 0 b - (c - a + b) / 2
//0 1 0 a - b + (c - a + b) / 2
//0 0 1 (c - a + b) / 2