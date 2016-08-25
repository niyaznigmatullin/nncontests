package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        double[] max = new double[n];
        double[] min = new double[n];
        for (int i = 0; i < n; i++) {
            max[i] = in.nextDouble();
        }
        for (int i = 0; i < n; i++) {
            min[i] = in.nextDouble();
        }
        for (int i = 1; i < n; i++) {
            max[i] += max[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            min[i] += min[i + 1];
        }
        double[] a = new double[n];
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            double c = max[i];
            double d = 1 + c - (i + 1 == n ? 0 : min[i + 1]);
            double D = d * d - 4 * c;
            if (D < 0) D = 0;
            double sqrtD = Math.sqrt(D);
            a[i] = (d + sqrtD) * .5;
            b[i] = d - a[i];
        }
        for (int i = n - 1; i > 0; i--) {
            a[i] -= a[i - 1];
            b[i] -= b[i - 1];
        }
        for (int i = 0; i < n; i++) {
            if (i > 0) out.print(' ');
            out.print(a[i]);
        }
        out.println();
        for (int i = 0; i < n; i++) {
            if (i > 0) out.print(' ');
            out.print(b[i]);
        }
        out.println();
    }
}
