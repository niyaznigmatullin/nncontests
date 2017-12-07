package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskC {
    static double[] FACT = new double[123456];

    static {
        for (int i = 1; i < FACT.length; i++) {
            FACT[i] = Math.log(i) + FACT[i - 1];
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int m = in.nextInt();
        int[] x = in.readIntArray(n);
        int[] count = new int[41];
        int allHave = 0;
        for (int i : x) {
            count[i]++;
            allHave += i;
        }
        double[] prob = new double[m + 1];
        double sumProb = 0.0;
        for (int d = 1; d <= m; d++) {
            if (d < n) {
                prob[d] = 0;
                continue;
            }
            double log = 0;
            int have = d;
            for (int i : count) {
                log += FACT[have] - FACT[i] - FACT[have - i];
                have -= i;
            }
            log += FACT[allHave];
            for (int i : x) {
                log -= FACT[i];
            }
            log -= allHave * Math.log(d);
            prob[d] = Math.exp(log);
            sumProb += prob[d];
        }
        double ans = 0;
        for (int i = a; i <= b; i++) {
            ans += prob[i];
        }
        out.println(ans / sumProb);
    }
}
