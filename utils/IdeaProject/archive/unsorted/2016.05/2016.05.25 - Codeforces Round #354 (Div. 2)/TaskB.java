package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.Rational;

import java.util.Arrays;

public class TaskB {

    public void solve(int testNumber, FastScanner in, FastPrinter out) {

        int n = in.nextInt();
        int t = in.nextInt();
        double[][] a = new double[n][];
        for (int i = 0; i < n; i++) {
            a[i] = new double[i + 1];
        }
        for (int i = 0; i < t; i++) {
            a[0][0] += 1;
            for (int x = 0; x < n; x++) {
                for (int y = 0; y <= x; y++) {
                    double add = a[x][y] > 1 ? a[x][y] - 1 : 0;
                    a[x][y] -= add;
                    add *= .5;
                    if (x + 1 < n) {
                        a[x + 1][y] += add;
                        a[x + 1][y + 1] += add;
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (double z : a[i]) {
                if (z >= 1) {
                    ans++;
                }
            }
        }
        out.println(ans);
//
//        int n = in.nextInt();
//        int t = in.nextInt();
//        t = Math.min(t, 1500);
//        Rational[][] a = new Rational[n][];
//        for (int i = 0; i < n; i++) {
//            a[i] = new Rational[i + 1];
//            Arrays.fill(a[i], Rational.ZERO);
//        }
//        for (int i = 0; i < t; i++) {
//            a[0][0] = a[0][0].add(Rational.ONE);
//            for (int x = 0; x < n; x++) {
//                for (int y = 0; y <= x; y++) {
//                    Rational add = a[x][y].compareTo(Rational.ONE) > 0 ? a[x][y].subtract(Rational.ONE) : Rational.ZERO;
//                    a[x][y] = a[x][y].subtract(add);
//                    add = add.divide(Rational.valueOf(2));
//                    if (x + 1 < n) {
//                        a[x + 1][y] = a[x + 1][y].add(add);
//                        a[x + 1][y + 1] = a[x + 1][y + 1].add(add);
//                    }
//                }
//            }
//        }
//        int ans = 0;
//        for (int i = 0; i < n; i++) {
//            for (Rational z : a[i]) {
//                if (z.equals(Rational.ONE)) {
//                    ans++;
//                }
//            }
//        }
//        out.println(ans);
    }
}
