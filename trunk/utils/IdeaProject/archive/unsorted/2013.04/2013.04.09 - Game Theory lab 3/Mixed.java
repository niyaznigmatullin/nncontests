package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.Rational;
import ru.ifmo.niyaz.math.SimplexSolver;

import java.util.Arrays;

public class Mixed {

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = in.nextInt() + 1200;
            }
        }
        Rational[] p2 = new Rational[m];
        Rational[] p1 = new Rational[n];
        if (false) {
            Rational[] b = new Rational[m];
            Rational[] c = new Rational[n];
            Arrays.fill(b, Rational.ONE.negate());
            Arrays.fill(c, Rational.ONE.negate());
            Rational[][] a = new Rational[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j] = new Rational(-matrix[j][i]);
                }
            }
            SimplexSolver.simplex(n, m, a, b, c, p1, p2);
        }
        {
            Rational[] b = new Rational[n];
            Rational[] c = new Rational[m];
            Arrays.fill(b, Rational.ONE);
            Arrays.fill(c, Rational.ONE);
            Rational[][] a = new Rational[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    a[i][j] = new Rational(matrix[i][j]);
                }
            }
            SimplexSolver.simplex(m, n, a, b, c, p2, p1);
            Rational sum = Rational.ZERO;
            for (int i = 0; i < m; i++) {
                sum = sum.add(p2[i]);
            }
            for (int i = 0; i < m; i++) {
                p2[i] = p2[i].divide(sum);
            }
        }
        norm(p1);
        norm(p2);
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                out.print(' ');
            }
            out.print(p1[i].decimal(20));
        }
        out.println();
        for (int i = 0; i < m; i++) {
            if (i > 0) {
                out.print(' ');
            }
            out.print(p2[i].decimal(20));
        }
        out.println();
    }

    private void norm(Rational[] p1) {
        int n = p1.length;
        Rational sum = Rational.ZERO;
        for (int i = 0; i < n; i++) {
            sum = sum.add(p1[i]);
        }
        for (int i = 0; i < n; i++) {
            p1[i] = p1[i].divide(sum);
        }
    }

}


