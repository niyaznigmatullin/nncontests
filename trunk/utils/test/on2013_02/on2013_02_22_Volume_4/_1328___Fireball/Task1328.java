package lib.test.on2013_02.on2013_02_22_Volume_4._1328___Fireball;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Task1328 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int w = in.nextInt();
        int h = in.nextInt();
        int n = in.nextInt();
        int x1 = in.nextInt();
        int y1 = in.nextInt();
        int x2 = in.nextInt();
        int y2 = in.nextInt();
        double answer = Double.POSITIVE_INFINITY;
        int distance = Integer.MAX_VALUE;
        for (int s1 = -1; s1 < 2; s1 += 2) {
            for (int s2 = -1; s2 < 2; s2 += 2) {
                for (int ix = 0; ix <= n; ix++) {
                    int iy = n - ix;
                    int x3 = w * s1 * ix;
                    int y3 = h * s2 * iy;
                    x3 += (ix & 1) == 1 ? (w - x2) : x2;
                    y3 += (iy & 1) == 1 ? (h - y2) : y2;
                    int dx = x3 - x1;
                    int dy = y3 - y1;
                    double angle = Math.atan2(dy, dx);
                    if (angle < 0) {
                        angle += 2 * Math.PI;
                    }
                    angle = 180 * angle / Math.PI;
                    int curD = dx * dx + dy * dy;
                    if (curD < distance || curD == distance && answer > angle) {
                        answer = angle;
                        distance = curD;
                    }
                }
            }
        }
        out.println(answer);
    }
}
