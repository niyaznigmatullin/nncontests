package coding;

import com.sun.xml.internal.messaging.saaj.soap.ver1_2.SOAPPart1_2Impl;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.Polynomial;
import ru.ifmo.niyaz.math.Rational;

import java.math.BigInteger;

public class Task1948 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int t = in.nextInt();
        for (int i = 0; i < t; ++i) {
            Polynomial a = new Polynomial(new Rational(in.nextInt()));
            Polynomial b = new Polynomial(new Rational(in.nextInt()));
            Polynomial c = new Polynomial(new Rational(in.nextInt()));
            Polynomial k = new Polynomial(Rational.ZERO, Rational.ONE);
            Polynomial p1 = b.multiply(k);
            Polynomial p2 = a.multiply(k);
            Polynomial p3 = p2.multiply(k);
            Polynomial q1 = p1.add(p2).subtract(p3);
            q1 = q1.multiply(q1);
            Rational half = new Rational(1, 2);
            Polynomial p4 = c.multiply(k);
            Polynomial p7 = a.multiply(half);
            Polynomial km1 = k.subtract(new Polynomial(Rational.ONE));
            Polynomial p9 = k.multiply(Rational.ONE.add(Rational.ONE)).subtract(new Polynomial(Rational.ONE));
            Polynomial q2 = c.multiply(k).add(b.multiply(k).multiply(km1).multiply(half)).add(a.multiply(half).multiply(km1).multiply(p9).multiply(k));
            q2 = q2.multiply(a).multiply(k).multiply(new Rational(4));
            Polynomial q = q1.subtract(q2);
            BigInteger got = get(q.getCoef(4), q.getCoef(3), q.getCoef(2));
        }
    }

    static BigInteger get(Rational ra, Rational rb, Rational rc) {
        double a = ra.doubleValue();
        double b = rb.doubleValue();
        double c = rc.doubleValue();
        return null;
    }
}
