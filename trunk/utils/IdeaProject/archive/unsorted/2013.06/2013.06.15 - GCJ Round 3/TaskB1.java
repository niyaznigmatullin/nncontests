package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.geometry.GeometryAlgorithms;
import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB1 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println("[" + testNumber + "]");
        out.print("Case #" + testNumber + ":");
        int n = in.nextInt();
        Point2DInteger[] p = new Point2DInteger[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Point2DInteger(in.nextInt(), in.nextInt());
        }
        int[] perm = new int[n];
        for (int i = 0; i < n; i++) {
            perm[i] = i;
        }
        int[] ans = null;
        long bestArea = 0;
        do {
            if (perm[0] != 0) break;
            boolean ok = true;
            Point2DInteger[] q = new Point2DInteger[n];
            for (int i = 0; i < n; i++) {
                q[i] = p[perm[i]];
            }
            all:
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j || (i + 1) % n == j || i == (j + 1) % n) {
                        continue;
                    }
                    if (GeometryAlgorithms.segmentsIntersect(q[i], q[(i + 1) % n], q[j], q[(j + 1) % n])) {
                        ok = false;
                        break all;
                    }
                }
            }
            if (ok) {
                long area = GeometryAlgorithms.signedDoubledArea(q);
                if (area > bestArea) {
                    bestArea = area;
                    ans = perm.clone();
                }
            }
        } while (ArrayUtils.nextPermutation(perm));
        for (int i : ans) out.print(" " + i);
        out.println();
    }
}
