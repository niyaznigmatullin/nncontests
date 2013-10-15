package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import sun.org.mozilla.javascript.internal.NativeGenerator;

import java.util.Arrays;

public class Estimate {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int t = in.nextInt();
        double allr = 0;
        for (int test = 0; test < t; test++) {
            int n = in.nextInt();
            double sum = 0;
            double[] a = new double[n];
            double max = 0;
            for (int j = 0; j < n; j++) {
                double v = in.nextDouble();
                a[j] = v;
                sum += v;
                max = Math.max(max, v);
            }
            Arrays.sort(a);
            double best = Double.POSITIVE_INFINITY;
            int ans = 0;
            for (int r = (int) max + 1; r <= 1000; r += 10) {
                double cur = 0;
                for (int i = 0; i + 1 < n; i++) {
                    double p1 = 1. * (i + 1) / n;
                    double p2 = (a[i] + a[i + 1]) * .5 / r;
                    cur += Math.abs(p1 - p2);
                }
                double disp = 0;
                for (int i = 0; i < n; i++) {
                    double dx = a[i] - r * .5;
                    disp += dx * dx;
                }
                disp /= n;
                cur = Math.abs(disp - 1. * r * r / 12) + Math.abs(r - sum / n * 2);
                if (cur < best) {
                    best = cur;
                    ans = r;
                }
            }
//            double pr = (ans + sum / n * 2) * .5;
//            out.println(pr);
            out.println(ans);
            allr += ans;
        }
        System.err.println(allr);
    }
}
