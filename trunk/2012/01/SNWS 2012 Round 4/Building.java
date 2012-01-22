package mypackage;

import graphalgorithms.KuhnMatchingGraph;
import niyazio.FastScanner;
import niyazio.FastPrinter;

public class Building {

    static class Point {
        double x;
        double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        double dist(Point p) {
            double dx = x - p.x;
            double dy = y - p.y;
            return Math.sqrt(dx * dx + dy * dy);
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int studentsNumber = in.nextInt();
        Point prof = readPoint(in);
        double vProf = in.nextDouble();
        Point[] s = new Point[studentsNumber];
        double[] velS = new double[studentsNumber];
        for (int i = 0; i < studentsNumber; i++) {
            s[i] = readPoint(in);
            velS[i] = in.nextDouble();
        }
        double ans = prof.dist(readPoint(in)) / vProf;
        Point[] z = new Point[n];
        for (int i = 0; i < n; i++) {
            z[i] = readPoint(in);
        }
        double left = 0;
        double right = 1e6;
        for (int it = 0; it < 70; it++) {
            double mid = (left + right) * .5;
            KuhnMatchingGraph g = new KuhnMatchingGraph(studentsNumber, n);
            for (int i = 0; i < studentsNumber; i++) {
                for (int j = 0; j < n; j++) {
                    double time = s[i].dist(z[j]) / velS[i];
                    if (time < mid) {
                        g.addEdge(i, j);
                    }
                }
            }
            if (g.getMaximalMatching() == n) {
                right = mid;
            } else {
                left = mid;
            }
        }
        out.println(ans + left);
    }

    static Point readPoint(FastScanner in) {
        return new Point(in.nextDouble(), in.nextDouble());
    }

}
