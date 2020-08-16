package ru.ifmo.niyaz.math;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: niyaz.nigmatullin
 * Date: 09.04.13
 * Time: 13:35
 * used the idea of implementation from https://sites.google.com/site/indy256/
 */
public class SimplexSolver {

    // returns max c*x such that A*x <= b, x >= 0
    // -1 if max c * x = +inf
    public static int simplex(int var, int ine, Rational[][] matrix, Rational[] b, Rational[] c, Rational[] x, Rational[] y) {
        int n = var + 1;
        int m = ine;
        int[] id = new int[n + m];
        for (int i = 0; i < n + m; i++) id[i] = i;
        Rational[][] a = new Rational[m + 2][n + 1];
        for (Rational[] e : a) {
            Arrays.fill(e, Rational.ZERO);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n - 1; j++) {
                a[i][j] = matrix[i][j].negate();
            }
            a[i][n - 1] = Rational.ONE;
            a[i][n] = b[i];
        }
        a[m + 1][n - 1] = Rational.ONE.negate();
        int d = m;
        for (int i = 0; i < m; i++) {
            if (a[i][n].compareTo(a[d][n]) < 0) d = i;
        }
        for (int i = 0; i < c.length; i++) {
            a[m][i] = c[i];
        }
        int e = n - 1;
        while (true) {
            if (d < m) {
                int t = id[e];
                id[e] = id[n + d];
                id[n + d] = t;
                a[d][e] = new Rational(BigInteger.ONE).divide(a[d][e]);
                Rational z = a[d][e].negate();
                for (int i = 0; i <= n; i++) {
                    if (i == e) continue;
                    a[d][i] = a[d][i].multiply(z);
                }
                for (int i = 0; i <= m + 1; i++) {
                    if (i == d) continue;
                    for (int j = 0; j <= n; j++) {
                        if (j == e) continue;
                        a[i][j] = a[i][j].add(a[i][e].multiply(a[d][j]));
                    }
                    a[i][e] = a[i][e].multiply(a[d][e]);
                }
            }
            e = -1;
            for (int i = 0; i < n; i++) {
                if (e < 0 || id[e] > id[i]) {
                    if (a[m + 1][i].signum() > 0 || (a[m + 1][i].signum() == 0 && a[m][i].signum() > 0)) {
                        e = i;
                    }
                }
            }
            if (e < 0) break;
            d = -1;
            for (int i = 0; i < m; i++) {
                if (a[i][e].signum() >= 0) continue;
                if (d < 0) {
                    d = i;
                    continue;
                }
                int z = a[d][n].divide(a[d][e]).compareTo(a[i][n].divide(a[i][e]));
                if (z < 0 || (z == 0 && id[d + n] > id[i + n])) d = i;
            }
            if (d < 0) {
                return -1;
            }
        }
        if (a[m + 1][n].signum() < 0) return 0;
        Arrays.fill(x, Rational.ZERO);
        for (int i = 0; i < m; i++) {
            if (id[n + i] < n - 1)
                x[id[n + i]] = a[i][n];
        }
        Arrays.fill(y, Rational.ZERO);
        for (int i = 0; i < n; i++) {
            if (id[i] >= n) {
                y[id[i] - n] = a[m][i];
            }
        }
        return 1;
    }

    // returns max c*x such that A*x <= b, x >= 0
    public static int simplexDoubleNoEps(int var, int ine, double[][] matrix, double[] b, double[] c, double[] x, double[] y) {
        int n = var + 1;
        int m = ine;
        int[] id = new int[n + m];
        for (int i = 0; i < n + m; i++) id[i] = i;
        double[][] a = new double[m + 2][n + 1];
        for (double[] e : a) {
            Arrays.fill(e, 0);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n - 1; j++) {
                a[i][j] = -matrix[i][j];
            }
            a[i][n - 1] = 1;
            a[i][n] = b[i];
        }
        a[m + 1][n - 1] = -1;
        int d = m;
        for (int i = 0; i < m; i++) {
            if (a[i][n] < a[d][n]) d = i;
        }
        for (int i = 0; i < c.length; i++) {
            a[m][i] = c[i];
        }
        int e = n - 1;
        while (true) {
            if (d < m) {
                int t = id[e];
                id[e] = id[n + d];
                id[n + d] = t;
                a[d][e] = 1. / a[d][e];
                double z = -a[d][e];
                for (int i = 0; i <= n; i++) {
                    if (i == e) continue;
                    a[d][i] = a[d][i] * z;
                }
                for (int i = 0; i <= m + 1; i++) {
                    if (i == d) continue;
                    for (int j = 0; j <= n; j++) {
                        if (j == e) continue;
                        a[i][j] += a[i][e] * a[d][j];
                    }
                    a[i][e] *= a[d][e];
                }
            }
            e = -1;
            for (int i = 0; i < n; i++) {
                if (e < 0 || id[e] > id[i]) {
                    if (a[m + 1][i] > 0 || (a[m + 1][i] == 0 && a[m][i] > 0)) {
                        e = i;
                    }
                }
            }
            if (e < 0) break;
            d = -1;
            for (int i = 0; i < m; i++) {
                if (a[i][e] >= 0) continue;
                if (d < 0) {
                    d = i;
                    continue;
                }
                int z = compareEps(a[d][n] / a[d][e], a[i][n] / a[i][e]);
                if (z < 0 || (z == 0 && id[d + n] > id[i + n])) d = i;
            }
            if (d < 0) {
                return -1;
            }
        }
        if (a[m + 1][n] < 0) return 0;
        Arrays.fill(x, 0);
        for (int i = 0; i < m; i++) {
            if (id[n + i] < n - 1)
                x[id[n + i]] = a[i][n];
        }
        Arrays.fill(y, 0);
        for (int i = 0; i < n; i++) {
            if (id[i] >= n) {
                y[id[i] - n] = a[m][i];
            }
        }
        return 1;
    }

    static int compareEps(double a, double b) {
        if (Math.abs(a - b)/* / Math.max(1, Math.max(Math.abs(a), Math.abs(b))) */< 1e-10) return 0;
        return a < b ? -1 : 1;
    }

}
