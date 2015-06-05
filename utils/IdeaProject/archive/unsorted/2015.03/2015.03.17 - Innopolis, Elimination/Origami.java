package coding;

import ru.ifmo.niyaz.geometry.Point2DDouble;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class Origami {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = 2 * in.nextInt();
        int x = 2 * in.nextInt();
        int y = 2 * in.nextInt();
        if (x == n && y == n) {
            out.println(n * n * .25);
            return;
        }
        int la = n - x;
        int lb = n - y;
        int lc = -la * (n + x) / 2 - lb * (n + y) / 2;
        List<Point2DDouble> p = new ArrayList<>();
        p.add(new Point2DDouble(0, 0));
        if (la * n + lc < 0) {
            p.add(new Point2DDouble(n, 0));
            p.add(new Point2DDouble(n, 1. * (-lc - la * n) / lb));
        } else {
            p.add(new Point2DDouble(1. * -lc / la, 0));
        }
        if (lb * n + lc < 0) {
            p.add(new Point2DDouble(1. * (-lc - lb * n) / la, n));
            p.add(new Point2DDouble(0, n));
        } else {
            p.add(new Point2DDouble(0, 1. * (-lc) / lb));
        }
        double ans = 0;
        for (int i = 0; i < p.size(); i++) {
            ans += p.get(i).vmul(p.get((i + 1) % p.size()));
        }
        out.println(Math.abs(ans) * .125);
    }
}
