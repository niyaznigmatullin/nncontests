package math;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 17.01.12
 * Time: 17:37
 * To change this template use File | Settings | File Templates.
 */
import java.math.BigInteger;

public class Rational implements Comparable<Rational> {
	BigInteger num;
	BigInteger den;
	static Rational ZERO = new Rational(BigInteger.ZERO);
	static Rational ONE = new Rational(BigInteger.ONE);

	public Rational(long a) {
		num = BigInteger.valueOf(a);
		den = BigInteger.ONE;
		norm();
	}

	public Rational(long a, long b) {
		if (b == 0) {
			throw new Error("b == 0");
		}
		num = BigInteger.valueOf(a);
		den = BigInteger.valueOf(b);
		norm();
	}

	public Rational(BigInteger a) {
		if (a == null) {
			throw new NullPointerException();
		}
		num = a;
		den = BigInteger.ONE;
		norm();
	}

	public Rational(BigInteger num, BigInteger den) {
		if (num == null || den == null) {
			throw new NullPointerException();
		}
		if (den.equals(BigInteger.ZERO)) {
			throw new Error("den == 0");
		}
		this.num = num;
		this.den = den;
		norm();
	}

	private void norm() {
		if (den.compareTo(BigInteger.ZERO) < 0) {
			den = den.negate();
			num = num.negate();
		}
		BigInteger g = num.gcd(den);
		if (g.compareTo(BigInteger.ONE) > 0) {
			num = num.divide(g);
			den = den.divide(g);
		}
	}

	public Rational add(Rational r) {
		BigInteger a = num.multiply(r.den).add(r.num.multiply(den));
		BigInteger b = den.multiply(r.den);
		return new Rational(a, b);
	}

	public Rational subtract(Rational r) {
		BigInteger a = num.multiply(r.den).subtract(r.num.multiply(den));
		BigInteger b = den.multiply(r.den);
		return new Rational(a, b);
	}

	public Rational multiply(Rational r) {
		return new Rational(num.multiply(r.num), den.multiply(r.den));
	}

	public Rational divide(Rational r) {
		return new Rational(num.multiply(r.den), den.multiply(r.num));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((den == null) ? 0 : den.hashCode());
		result = prime * result + ((num == null) ? 0 : num.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rational other = (Rational) obj;
		if (den == null) {
			if (other.den != null)
				return false;
		} else if (!den.equals(other.den))
			return false;
		if (num == null) {
			if (other.num != null)
				return false;
		} else if (!num.equals(other.num))
			return false;
		return true;
	}

	static Rational valueOf(long a) {
		return new Rational(a);
	}

	static Rational valueOf(long a, long b) {
		return new Rational(a, b);
	}

	@Override
	public String toString() {
		return num.toString()
				+ (den.equals(BigInteger.ONE) ? "" : "/" + den.toString());
	}

    public int compareTo(Rational o) {
        return num.multiply(o.den).compareTo(den.multiply(o.num));
    }
}

