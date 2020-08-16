package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.Rational;
import ru.ifmo.niyaz.math.SimplexSolver;

import java.util.Arrays;
import java.util.Random;

public class PointSet {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        while (true) {
            int n = in.nextInt();
            if (n == 0) break;
            int k = in.nextInt();
//            out.println(n + " " + k);
            long[] best = new long[1 << k];
            Arrays.fill(best, Long.MIN_VALUE);
            long[] values = new long[k];
            long[][] p = new long[n][k];
            final long MAX = 20000000000000000L;
            long bestBest = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < k; j++) p[i][j] = values[j] = in.nextLong() + MAX;
//                for (int j = 0; j < k; j++) {
//                    if (j > 0) out.print(' ');
//                    out.print(values[j] - MAX);
//                }
//                out.println();
                for (int mask = 0; mask < 1 << k; mask++) {
                    long sum = 0;
                    for (int j = 0; j < k; j++) {
                        if (((mask >> j) & 1) == 1) {
                            sum -= values[j];
                        } else {
                            sum += values[j];
                        }
                    }
                    best[mask] = Math.max(best[mask], sum);
                }
            }
            for (int i = 0; i < 1 << (k - 1); i++) {
                bestBest = Math.max(bestBest, best[i] + best[i ^ ((1 << k) - 1)]);
            }
            Rational left = Rational.valueOf(0);
//        Rational right = Rational.valueOf(k * MAX);
            Rational right = Rational.valueOf(Long.MAX_VALUE);
            double[] answer = new double[k];
            Rational[][] a = new Rational[1 << k][k];
//        double[][] A = new double[1 << k][k];
            for (int mask = 0; mask < (1 << k); mask++) {
                for (int i = 0; i < k; i++) {
                    a[mask][i] = Rational.valueOf(((mask >> i) & 1) == 0 ? -1 : 1);
//                A[mask][i] = ((mask >> i) & 1) == 0 ? -1 : 1;
                }
            }
            Rational[] b = new Rational[1 << k];
//        double[] B = new double[1 << k];
            Rational[] c = new Rational[k];
//        double[] C = new double[k];
            Arrays.fill(c, Rational.ZERO);
            Rational[] x = new Rational[k];
//        double[] X = new double[k];
            Rational[] y = new Rational[1 << k];
//        double[] Y = new double[1 << k];
            for (int it = 0; it < 100; it++) {
//            if (right.subtract(left).compareTo(Rational.valueOf(1, 10000000000L)) < 0) break;
                Rational middle = left.add(right).divide(Rational.valueOf(2));
                for (int mask = 0; mask < (1 << k); mask++) {
                    b[mask] = middle.subtract(Rational.valueOf(best[mask]));
//                B[mask] = middle.doubleValue() - best[mask];
                }
                if (SimplexSolver.simplex(k, 1 << k, a, b, c, x, y) != 0) {
                    right = middle;
                    for (int i = 0; i < k; i++) answer[i] = x[i].subtract(Rational.valueOf(MAX)).doubleValue();
                } else {
                    left = middle;
                }
            }
//            LinearSolver solver = new LinearSolver(k, 1 << k);
//            for (int mask = 0; mask < (1 << k); mask++) {
//                for (int i = 0; i < k; i++) {
//                    solver.A[mask + 1][i + 1] = ((mask >> i) & 1) == 1 ? -1 : 1;
//                }
//                solver.A[mask + 1][0] = middle - best[mask];
//            }
//            solver.solve(k, (1 << k));
//            boolean bad = false;
//            for (int mask = 0; mask < (1 << k); mask++) {
//                double sum = 0;
//                for (int i = 0; i < k; i++) {
//                    sum += (((mask >> i) & 1) == 1 ? -1 : 1) * solver.x[i];
//                }
//                if (sum < best[mask] - middle) {
//                    bad = true;
//                    break;
//                }
//            }
//            if (!bad) {
//                right = middle;
//            } else {
//                left = middle;
//            }
            for (int i = 0; i < k; i++) {
                if (i > 0) out.print(' ');
                out.print(answer[i]);
            }
            out.println();
//        System.out.println(right.decimal(10));
//        double[] correct = new double[k];
//        for (int i = 0; i < k; i++) correct[i] = in.nextDouble();
//        double max = 0;
//        double maxCorrect = 0;
//        for (int i = 0; i < n; i++) {
//            double dist = 0;
//            double distCorrect = 0;
//            for (int j = 0; j < k; j++) {
//                dist += Math.abs(p[i][j] - MAX - answer[j]);
//                distCorrect += Math.abs(p[i][j] - MAX - correct[j]);
//            }
//            max = Math.max(max, dist);
//            maxCorrect = Math.max(maxCorrect, distCorrect);
//        }
//        out.println("max = " + max);
//        out.println("maxCorrect = " + maxCorrect);


//        for (int mask = 0; mask < 1 << (k - 1); mask++) {
//            best[mask] -= best[mask ^ ((1 << k) - 1)];
//        }
//        long[] answer = new long[k];
//        for (int i = 0; i + 1 < k; i++) {
//            answer[i] = best[0] - best[1 << i];
//        }
//        answer[k - 1] = best[0] * 2;
//        for (int i = 0; i + 1 < k; i++) answer[k - 1] -= answer[i];
//        System.out.println(Arrays.toString(answer));
//        for (int mask = 0; mask < 1 << (k - 1); mask++) {
//            long sum = 0;
//            for (int i = 0; i < k; i++) {
//                if (((mask >> i) & 1) == 1) {
//                    sum -= answer[i];
//                } else {
//                    sum += answer[i];
//                }
//            }
//            if (sum != best[mask] * 2) {
//                System.out.println("mask = " + mask + ", " + sum + " " + 2 * best[mask]);
//                throw new AssertionError();
//            }
//        }
//        for (int i = 0; i < k; i++) {
//            if (i > 0) out.print(' ');
//            out.print(answer[i] * .25);
//        }
//        out.println();
        }
    }


    static class LinearSolver {

        static final double eps = 1e-10;
        static final double INF = 1e19;

        double[][][] buf;
        double[][] A;
        int n;

        public LinearSolver(int n, int m) {
            this.n = n;
            buf = new double[n + 1][m + 1][n + 1];
            A = buf[n];
            x = new double[n];
            u = new boolean[n];
        }

        double[] x;
        boolean[] u;

        void solve(int l, int m) {
            for (int i = 0; i + 1 < A.length; ++i) {
                int j = rand.nextInt(i + 1);
                double[] tmp = A[i + 1];
                A[i + 1] = A[j + 1];
                A[j + 1] = tmp;
            }
            solveIt(l, m);
        }

        /*
         *Solves this system in O(n) expected time
         *Read the following aloud before trying to understand why ;-)
         *
         *Darkness from twilight, crimson from blood that flows;
         *buried in the flow of time; in Thy great name, I pledge myself to darkness!
         *Those who oppose us shall be destroyed by the power you and I possess!
         */
        void solveIt(int l, int m) {
            double[][] A = buf[l];
            if (l == 0) return;

            for (int i = 0; i < n; ++i) {
                if (!u[i]) {
                    x[i] = Math.signum(A[0][i + 1]) * INF;
                }
            }

            for (int j = 1; j <= m; ++j) {
                double tv = A[j][0];
                for (int i = 0; i < n; ++i) {
                    if (!u[i]) {
                        tv += A[j][i + 1] * x[i];
                    }
                }
                if (tv < -eps) {
                    int mi = -1;
                    for (int i = 0; i < n; ++i) {
                        if (!u[i] && (mi == -1 || Math.abs(A[j][mi + 1]) < Math.abs(A[j][i + 1]))) {
                            mi = i;
                        }
                    }

                    double[][] B = buf[l - 1];
                    for (int i = 0; i < B[j].length; ++i) {
                        B[j][i] = -A[j][i] / A[j][mi + 1];
                    }


                    for (int k = 0; k < j; ++k) {
                        for (int i = 0; i < B[k].length; ++i) {
                            B[k][i] = A[k][i] + A[k][mi + 1] * B[j][i];
                        }
                    }

                    u[mi] = true;
                    solve(l - 1, j - 1);
                    x[mi] = B[j][0];
                    for (int i = 0; i < n; ++i) {
                        if (!u[i]) {
                            x[mi] += x[i] * B[j][i + 1];
                        }
                    }
                    u[mi] = false;
                }
            }
        }
        static Random rand = new Random(45743);
    }



}
