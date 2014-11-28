package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        if (k == 1) {
            return;
        }
        final int C = 100;
        double[] exp = new double[C + 1];
        double[] prob = new double[C + 1];
        prob[1] = 1;
        for (int i = 0; i < Math.min(C, n); i++) {
            double[] nExp = new double[C + 1];
            double[] nProb = new double[C + 1];
            for (int j = 0; j < C; j++) {
                if (prob[j] == 0)
                    continue;
                nExp[j] += exp[j] + prob[j] * j * .5;
                nProb[j] += prob[j] * j / (j + 1);
                nExp[j + 1] += (exp[j] + prob[j] * j) / (j + 1);
                nProb[j + 1] += prob[j] / (j + 1);
            }
            exp = nExp;
            prob = nProb;
            double ans = 0;
            for (double e : exp) {
                ans += e;
            }
            out.println(ans);
        }
        System.out.println(Arrays.toString(exp));
    }
}
