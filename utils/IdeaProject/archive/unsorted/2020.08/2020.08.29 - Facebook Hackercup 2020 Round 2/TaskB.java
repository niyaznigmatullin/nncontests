package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println("Test #" + testNumber);
        int n = in.nextInt();
        double p = in.nextDouble();
        double[][] f = new double[n][n];
        for (int stronger = 0; stronger < n; stronger++) {
            for (int weaker = 0; weaker < n; weaker++) {
                if (stronger == 0 && weaker == 0) {
                    f[stronger][weaker] = 0.0;
                    continue;
                }
                int all = stronger + weaker + 1;
                if (all > n) continue;
                double playStrongerWeaker = 2. * weaker * stronger / all / (all - 1);
                double current = 1.;
                if (stronger > 0) {
                    double playStrongers = 1. * stronger * (stronger - 1) / all / (all - 1);
                    double againstStronger = 2. * stronger / all / (all - 1);
                    current += f[stronger - 1][weaker] * (playStrongers + (playStrongerWeaker + againstStronger) * (1 - p));
                }
                if (weaker > 0) {
                    double againstWeaker = 2. * weaker / all / (all - 1);
                    double playWeakers = 1. * weaker * (weaker - 1) / all / (all - 1);
                    current += f[stronger][weaker - 1] * (playWeakers + (playStrongerWeaker + againstWeaker) * p);
                }
                f[stronger][weaker] = current;
            }
        }
        out.println("Case #" + testNumber + ":");
        for (int i = 0; i < n; i++) {
            out.println(f[n - i - 1][i]);
        }
    }
}
