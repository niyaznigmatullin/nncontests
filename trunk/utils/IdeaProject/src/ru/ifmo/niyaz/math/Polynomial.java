package ru.ifmo.niyaz.math;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: niyaz.nigmatullin
 * Date: 28.08.13
 * Time: 12:35
 * To change this template use File | Settings | File Templates.
 */
public class Polynomial {
    Rational[] coef;

    public Polynomial(Rational... coef) {
        this.coef = coef;
    }

    public Polynomial(Rational a) {
        coef = new Rational[]{a};
    }

    public Rational getCoef(int x) {
        return coef[x];
    }

    public Rational[] getCoefficients() {
        Rational[] z = coef.clone();
        int i = z.length - 1;
        while (i >= 0 && z[i].signum() == 0) {
            --i;
        }
        return Arrays.copyOf(z, i + 1);
    }

    public Polynomial multiply(Polynomial b) {
        Rational[] c = new Rational[b.coef.length + coef.length];
        Arrays.fill(c, Rational.ZERO);
        for (int i = 0; i < coef.length; i++) {
            for (int j = 0; j < b.coef.length; j++) {
                c[i + j] = c[i + j].add(coef[i].multiply(b.coef[j]));
            }
        }
        return new Polynomial(c);
    }

    public Polynomial multiply(Rational b) {
        Rational[] c = coef.clone();
        for (int i = 0; i < c.length; i++) {
            c[i] = c[i].multiply(b);
        }
        return new Polynomial(c);
    }

    public Polynomial add(Polynomial b) {
        Rational[] c = new Rational[Math.max(coef.length, b.coef.length)];
        Arrays.fill(c, Rational.ZERO);
        for (int i = 0; i < c.length; i++) {
            if (i < coef.length) {
                c[i] = c[i].add(coef[i]);
            }
            if (i < b.coef.length) {
                c[i] = c[i].add(b.coef[i]);
            }
        }
        return new Polynomial(c);
    }

    public Polynomial subtract(Polynomial b) {
        Rational[] c = new Rational[Math.max(coef.length, b.coef.length)];
        Arrays.fill(c, Rational.ZERO);
        for (int i = 0; i < c.length; i++) {
            if (i < coef.length) {
                c[i] = c[i].add(coef[i]);
            }
            if (i < b.coef.length) {
                c[i] = c[i].subtract(b.coef[i]);
            }
        }
        return new Polynomial(c);
    }

    public static Polynomial interpolate(Rational[] x, Rational[] y) {
        Polynomial ans = new Polynomial();
        int n = x.length;
        for (int i = 0; i < n; i++) {
            Polynomial cur = new Polynomial(y[i]);
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    cur = cur.multiply(new Polynomial(x[j].negate(), Rational.ONE)).multiply(Rational.ONE.divide(x[i].subtract(x[j])));
                }
            }
            ans = ans.add(cur);
        }
        return ans;
    }
}
