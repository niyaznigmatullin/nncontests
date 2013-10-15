package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.Polynomial;
import ru.ifmo.niyaz.math.Rational;

import java.math.BigInteger;
import java.util.Arrays;

public class Divide {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Rational[] x = new Rational[n + 1];
        Rational[] y = new Rational[n + 1];
        for (int i = 0; i <= n; i++) {
            x[i] = Rational.valueOf(i);
            y[i] = Rational.valueOf(1 << i);
        }
        Polynomial poly = Polynomial.interpolate(x, y);
        Rational[] coef = poly.getCoefficients();
        out.println(coef.length - 1);
        for (int i = coef.length - 1; i >= 0; i--) {
            out.print(coef[i].getNum() + "/" + coef[i].getDen() + " ");
        }
    }
}
