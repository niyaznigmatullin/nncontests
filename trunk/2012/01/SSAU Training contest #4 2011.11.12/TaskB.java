package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskB {
    static double[][] answers = {
            {0.000000000, 0.000000000, 0.000000000},
            {1.000000000, 0.000000000, 0.000000000},
            {1.000000000, 0.577350269, 1.511522628},
            {0.000000000, 0.577350269, 1.511522628},
            {0.500000000, 0.866025404, 0.000000000},
            {0.500000000, 1.222847494, 0.934172359},
            {0.500000000, -0.288675135, 1.511522628},
            {0.500000000, -0.645497224, 0.577350269},
            {-0.309016994, 0.755761314, 0.577350269},
            {1.309016994, 0.755761314, 0.577350269},
            {1.309016994, -0.178411045, 0.934172359},
            {-0.309016994, -0.178411045, 0.934172359}};

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        double[] x = new double[3];
        double[] y = new double[3];
        for (int i = 0; i < 3; i++) {
            x[i] = in.nextDouble();
            y[i] = in.nextDouble();
        }
        double area = (x[1] - x[0]) * (y[2] - y[0]) - (x[2] - x[0]) * (y[1] - y[0]);
        if (area < 0) {
            {
                double tmp = x[1];
                x[1] = x[2];
                x[2] = tmp;
            }
            {
                double tmp = y[1];
                y[1] = y[2];
                y[2] = tmp;
            }
        }
        double angle = Math.atan2(y[1] - y[0], x[1] - x[0]);
        for (double[] e : answers) {
            double curX = e[0] * Math.cos(angle) - Math.sin(angle) * e[1] + x[0];
            double curY = e[0] * Math.sin(angle) + Math.cos(angle) * e[1] + y[0];
            double curZ = e[2];
            out.println(curX + " " + curY + " " + curZ);
        }
    }
}
