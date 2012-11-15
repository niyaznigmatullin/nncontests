package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class Cylinder {

    double volume(double d, double h) {
        return (h - d) * Math.PI * d * d * 0.25;
    }

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int w = in.nextInt();
        int h = in.nextInt();
        if (w == 0 && h == 0)
            throw new UnknownError();
        double ans = Math.max(volume(0, h), volume(w / Math.PI, h));
        double dMid = 2.0 * h / 3;
        if (dMid <= w / Math.PI) {
            ans = Math.max(ans, volume(dMid, h));
        }

        dMid = h / (Math.PI + 1);
        dMid = Math.min(dMid, w);
        ans = Math.max(ans, w * Math.PI * dMid * dMid * 0.25);

        out.printf("%.3f\n", ans);

	}
}
