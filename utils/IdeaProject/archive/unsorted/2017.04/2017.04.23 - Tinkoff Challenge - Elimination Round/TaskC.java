package coding;

import ru.ifmo.niyaz.geometry.GeometryAlgorithms;
import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.DoubleComparator;

public class TaskC {

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int x1 = in.nextInt();
        int y1 = in.nextInt();
        int x2 = in.nextInt();
        int y2 = in.nextInt();
        double ansMin = Double.NEGATIVE_INFINITY;
        double ansMax = Double.POSITIVE_INFINITY;
        for (int i = 0; i < n; i++) {
            int sx = in.nextInt();
            int sy = in.nextInt();
            int vx = in.nextInt();
            int vy = in.nextInt();
            double leftX = Double.POSITIVE_INFINITY;
            double rightX = Double.NEGATIVE_INFINITY;
            if (vx != 0) {
                for (int x : new int[]{x1, x2}) {
                    double t = 1. * (x - sx) / vx;
                    leftX = Math.min(leftX, t);
                    rightX = Math.max(rightX, t);
                }
            } else if (sx <= x1 || sx >= x2) {
                out.println(-1);
                return;
            } else {
                leftX = Double.NEGATIVE_INFINITY;
                rightX = Double.POSITIVE_INFINITY;
            }
            double leftY = Double.POSITIVE_INFINITY;
            double rightY = Double.NEGATIVE_INFINITY;
            if (vy != 0) {
                for (int y : new int[]{y1, y2}) {
                    double t = 1. * (y - sy) / vy;
                    leftY = Math.min(leftY, t);
                    rightY = Math.max(rightY, t);
                }
            } else if (sy <= y1 || sy >= y2) {
                out.println(-1);
                return;
            } else {
                leftY = Double.NEGATIVE_INFINITY;
                rightY = Double.POSITIVE_INFINITY;
            }
            double left = Math.max(leftY, leftX);
            double right = Math.min(rightY, rightX);
            left = Math.max(left, 0);
            ansMin = Math.max(ansMin, left);
            ansMax = Math.min(ansMax, right);
        }
        if (ansMin + 1e-11 >= ansMax) {
            out.println(-1);
        } else {
            out.println(ansMin);
        }
    }
}
