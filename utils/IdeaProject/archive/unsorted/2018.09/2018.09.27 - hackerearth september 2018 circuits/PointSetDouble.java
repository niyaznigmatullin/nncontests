package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.Rational;
import ru.ifmo.niyaz.math.SimplexSolver;

import java.util.Arrays;

public class PointSetDouble {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        long[] best = new long[1 << k];
        Arrays.fill(best, Long.MIN_VALUE);
        long[] values = new long[k];
        long[][] p = new long[n][k];
        final long MAX = 10000000000000000L;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) p[i][j] = values[j] = in.nextLong() + MAX;
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
        double left = 0;
        double right = 1e18;
        double[] answer = new double[k];
        double[][] a = new double[1 << k][k];
        for (int mask = 0; mask < (1 << k); mask++) {
            for (int i = 0; i < k; i++) {
                a[mask][i] = ((mask >> i) & 1) == 0 ? -1 : 1;
            }
        }
        double[] b = new double[1 << k];
        double[] c = new double[k];
        double[] x = new double[k];
        double[] y = new double[1 << k];
        for (int it = 0; it < 100; it++) {
            double middle = (left + right) * .5;
            for (int mask = 0; mask < (1 << k); mask++) {
                b[mask] = middle - best[mask];
            }
            if (SimplexSolver.simplexDoubleNoEps(k, 1 << k, a, b, c, x, y) != 0) {
                right = middle;
                for (int i = 0; i < k; i++) answer[i] = x[i] - MAX;
            } else {
                left = middle;
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
        }
        for (int i = 0; i < k; i++) {
            if (i > 0) out.print(' ');
            out.print(answer[i]);
        }
        out.println();
//        System.out.println(right.doubleValue());
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
        double max = 0;
        double maxCorrect = 0;
        for (int i = 0; i < n; i++) {
            double dist = 0;
            double distCorrect = 0;
            for (int j = 0; j < k; j++) {
                dist += Math.abs(p[i][j] - MAX - answer[j]);
//                distCorrect += Math.abs(p[i][j] - correct[j]);
            }
            max = Math.max(max, dist);
            maxCorrect = Math.max(maxCorrect, distCorrect);
        }
        out.println("max = " + max);
        out.println("maxCorrect = " + maxCorrect);
    }
}
