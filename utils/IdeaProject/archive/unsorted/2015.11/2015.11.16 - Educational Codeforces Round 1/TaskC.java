package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.Rational;

import java.util.Arrays;
import java.util.Comparator;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        Integer[] id = new Integer[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
            id[i] = i;
        }
        Arrays.sort(id, new Comparator<Integer>() {

            int get(int x, int y) {
                if (x > 0) {
                    return y > 0 ? 2 : y < 0 ? 8 : 1;
                } else if (x == 0) {
                    return y > 0 ? 3 : y < 0 ? 7 : 0;
                } else {
                    return y > 0 ? 4 : y < 0 ? 6 : 5;
                }
            }

            @Override
            public int compare(Integer o1, Integer o2) {
                int c = get(x[o1], y[o1]) - get(x[o2], y[o2]);
                if (c != 0) return c;
                return -Integer.signum(x[o1] * y[o2] - x[o2] * y[o1]);
            }
        });
        Rational angle = null;
        int ansi = 0;
        int ansj = 1;
        for (int it = 0; it < n; it++) {
            int i = id[it];
            int j = id[(it + 1) % n];
            int vmul = x[i] * y[j] - y[i] * x[j];
            int smul = x[i] * x[j] + y[i] * y[j];
            if (vmul <= 0) continue;
            int leni = x[i] * x[i] + y[i] * y[i];
            int lenj = x[j] * x[j] + y[j] * y[j];
            Rational z = new Rational((long) vmul * vmul, (long) leni * lenj);
            if (smul < 0) {
                z = Rational.ONE.add(Rational.ONE).subtract(z);
            }
            if (angle == null || angle.compareTo(z) > 0) {
                angle = z;
                ansi = i;
                ansj = j;
            }
        }
        out.println((1 + ansi) + " " + (1 + ansj));
    }
}
