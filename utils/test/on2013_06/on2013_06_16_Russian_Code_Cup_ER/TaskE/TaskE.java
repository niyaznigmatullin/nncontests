package lib.test.on2013_06.on2013_06_16_Russian_Code_Cup_ER.TaskE;



import ru.ifmo.niyaz.geometry.GeometryAlgorithms;
import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Point2DInteger[] p = new Point2DInteger[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Point2DInteger(in.nextInt(), in.nextInt());
        }
        long time = System.currentTimeMillis();
        Point2DInteger[][] vs = new Point2DInteger[n][n];
        double[][] len = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                vs[i][j] = p[j].subtract(p[i]);
                len[i][j] = vs[i][j].length();
            }
        }
        double[][][] angle = new double[n][n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                Point2DInteger v1 = vs[i][j];
                double[] aij = angle[i][j];
                for (int k = 0; k < n; k++) {
                    if (k == j) continue;
                    Point2DInteger v2 = vs[j][k];
                    double smul = v1.smul(v2);
                    smul /= len[i][j];
                    smul /= len[j][k];
                    if (smul > 1) smul = 1.;
                    if (smul < -1) smul = -1;
//                    angle[i][j][k] = Math.acos(smul);
                    aij[k] = smul;
//                    System.out.println(smul);
                }
            }
        }
        double l = -1;
        double r = 1;
        int[] q = new int[n * n];
        boolean[][] was = new boolean[n][n];
        for (int it = 0; it < 100; it++) {
            double mid = (l + r) * .5;
            int head = 0;
            int tail = 0;
            for (boolean[] e : was) {
                Arrays.fill(e, false);
            }
            for (int i = 1; i < n; i++) {
                q[tail++] = i * n;
                was[0][i] = true;
            }
            boolean ok = false;
            bfs: while (head < tail) {
                int v = q[head++];
                int first = v % n;
                int second = v / n;
                double[] angles = angle[first][second];
                boolean[] wass = was[second];
                for (int third = 0; third < n; third++) {
                    if (third == second || wass[third]) continue;
                    if (angles[third] < mid) {
                        continue;
                    }
                    wass[third] = true;
                    q[tail++] = second + third * n;
                    if (third == 0) {
                        ok = true;
                        break bfs;
                    }
                }
            }
//            System.out.println(mid + " " + ok);
            if (ok) l = mid; else r = mid;
        }
        out.println(Math.acos(l) / Math.PI * 180);
    }
}
