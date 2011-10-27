import java.io.*;
import java.math.*;
import java.util.*;
import static java.math.BigInteger.*;

public class B {
	public static void main(String[] args) {
		new B().run();
	}

	class FastScanner {
		BufferedReader br;
		StringTokenizer st;
		boolean eof;
		String buf;

		public FastScanner(String fileName) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(fileName));
			nextToken();
		}

		public FastScanner(InputStream stream) {
			br = new BufferedReader(new InputStreamReader(stream));
			nextToken();
		}

		String nextToken() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (Exception e) {
					eof = true;
					break;
				}
			}
			String ret = buf;
			buf = eof ? "-1" : st.nextToken();
			return ret;
		}

		int nextInt() {
			return Integer.parseInt(nextToken());
		}

		long nextLong() {
			return Long.parseLong(nextToken());
		}

		double nextDouble() {
			return Double.parseDouble(nextToken());
		}

		void close() {
			try {
				br.close();
			} catch (Exception e) {

			}
		}

		boolean isEOF() {
			return eof;
		}
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
		Locale.setDefault(Locale.US);
		try {
			sc = new FastScanner(System.in);
			out = new PrintWriter(System.out);
			solve();
			sc.close();
			out.close();
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	long nextLong() {
		return sc.nextLong();
	}

	double nextDouble() {
		return sc.nextDouble();
	}

	int nextInt() {
		return sc.nextInt();
	}

	String nextToken() {
		return sc.nextToken();
	}

	void solve() {
		int t = nextInt();
		for (int i = 0; i < t; i++) {
			if (check()) {
				out.println("YES");
			} else {
				out.println("NO");
			}
		}
	}

	class MyPoint {
		long x, y;

		public MyPoint(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		boolean equals(MyPoint p) {
			return x == p.x && y == p.y;
		}

		BigInteger dist2(MyPoint p) {
			BigInteger X1 = valueOf(x);
			BigInteger X2 = valueOf(p.x);
			BigInteger Y1 = valueOf(y);
			BigInteger Y2 = valueOf(p.y);
			BigInteger A = X1.subtract(X2);
			BigInteger B = Y1.subtract(Y2);
			return A.multiply(A).add(B.multiply(B));
		}
	}

	boolean eq(MyPoint a1, MyPoint a2, MyPoint b1, MyPoint b2) {
		return a1.equals(b1) || a1.equals(b2) || b1.equals(a2) || b2.equals(a2);
	}

	boolean degree(MyPoint a1, MyPoint a2, MyPoint b1, MyPoint b2) {
		// long d = (a2.x - a1.x) * (b2.x - b1.x) + (a2.y - a1.y) * (b2.y -
		// b1.y);
		// double e = (double) d;
		// return (d <= 0 || Math.abs(e - a1.dist(a2) * b1.dist(b2)) < 1e-9);
		BigInteger a = valueOf(a2.x - a1.x);
		BigInteger b = valueOf(b2.x - b1.x);
		BigInteger c = valueOf(a2.y - a1.y);
		BigInteger d = valueOf(b2.y - b1.y);
		BigInteger e = a.multiply(b).add(c.multiply(d));
		BigInteger f = a.multiply(d).subtract(c.multiply(b));
		return e.compareTo(ZERO) < 0 || f.equals(ZERO);
	}

	long gcd(long x, long y) {
		x = Math.abs(x);
		y = Math.abs(y);
		return (x == 0 || y == 0 ? x + y : gcd(y, x % y));
	}

	boolean theSame(long t1, long t2, long g, long s1, long s2) {
		if (t1 > 0 && s1 <= 0 || t1 == 0 && s1 != 0 || t1 < 0 && s1 >= 0) {
			return false;
		}
		if (t2 > 0 && s2 <= 0 || t2 == 0 && s2 != 0 || t2 < 0 && s2 >= 0) {
			return false;
		}
		long y1 = t1 / g;
		long y2 = t2 / g;
		if ((y1 == 0 || s1 % y1 == 0) && (y2 == 0 || s2 % y2 == 0)) {

		} else {
			return false;
		}
		return Math.abs(t1) >= Math.abs(s1) && Math.abs(t2) >= Math.abs(s2);
	}

	boolean liesOn(MyPoint a1, MyPoint a2, MyPoint b) {
		// double d = (a1.x - b.x) * (a2.x - b.x) + (a1.y - b.y) * (a2.y - b.y);
		// return Math.abs(d + a1.dist(b) * b.dist(a2)) < 1e-9;
		long t1 = a2.x - a1.x;
		long t2 = a2.y - a1.y;
		long g = gcd(t1, t2);
		long s1 = b.x - a1.x;
		long s2 = b.y - a1.y;
		return theSame(t1, t2, g, s1, s2);
		// BigInteger a = valueOf(a1.x - b.x);
		// BigInteger c = valueOf(a2.x - b.x);
		// BigInteger e = valueOf(a1.y - b.y);
		// BigInteger f = valueOf(a2.y - b.y);
		// BigInteger d = a.multiply(c).add(e.multiply(f));
		// // System.err.println(d);
		// return d.compareTo(ZERO) < 0
		// && (d.multiply(d)).equals(a1.dist2(b).multiply(b.dist2(a2)));

	}

	boolean dividesWell(MyPoint a1, MyPoint a2, MyPoint b) {
		// BigInteger d1 = a1.dist2(b);
		// BigInteger d2 = a2.dist2(b);
		// if (d1.compareTo(d2) > 0) {
		// BigInteger temp = d1;
		// d1 = d2;
		// d2 = temp;
		// }
		// // System.err.println(d1 + " " + d2);
		// if (d2.divide(d1).compareTo(valueOf(16)) > 0) {
		// return false;
		// }
		// return true;
		long t1 = Math.abs(a2.x - b.x);
		long t2 = Math.abs(a2.y - b.y);
		long s1 = Math.abs(b.x - a1.x);
		long s2 = Math.abs(b.y - a1.y);
		if (t1 != 0) {
			if (t1 > s1) {
				return t1 / s1 <= 4;
			} else {
				return s1 / t1 <= 4;
			}
		} else {
			if (t2 > s2) {
				return t2 / s2 <= 4;
			} else {
				return s2 / t2 <= 4;
			}
		}
	}

	boolean check() {
		MyPoint a1 = new MyPoint(nextInt(), nextInt());
		MyPoint a2 = new MyPoint(nextInt(), nextInt());
		MyPoint b1 = new MyPoint(nextInt(), nextInt());
		MyPoint b2 = new MyPoint(nextInt(), nextInt());
		MyPoint c1 = new MyPoint(nextInt(), nextInt());
		MyPoint c2 = new MyPoint(nextInt(), nextInt());

		int t = 0, kol = 0;
		if (eq(a1, a2, b1, b2)) {
			t = 1;
			kol++;
		}
		if (eq(a1, a2, c1, c2)) {
			t = 2;
			kol++;
		}
		if (eq(c1, c2, b1, b2)) {
			t = 3;
			kol++;
		}
		if (kol != 1) {
			// System.err.println("kol");
			return false;
		}
		if (t == 2) {
			MyPoint temp;
			temp = c1;
			c1 = b1;
			b1 = temp;
			temp = c2;
			c2 = b2;
			b2 = temp;
		} else if (t == 3) {
			MyPoint temp;
			temp = c1;
			c1 = a1;
			a1 = temp;
			temp = c2;
			c2 = a2;
			a2 = temp;
		}
		if (a1.equals(b2)) {
			MyPoint temp;
			temp = b1;
			b1 = b2;
			b2 = temp;
		} else if (a2.equals(b1)) {
			MyPoint temp;
			temp = a1;
			a1 = a2;
			a2 = temp;
		} else if (a2.equals(b2)) {
			MyPoint temp;
			temp = a1;
			a1 = a2;
			a2 = temp;
			temp = b1;
			b1 = b2;
			b2 = temp;
		}
		if (degree(a1, a2, b1, b2)) {
			// System.err.println("1D");
			return false;
		}
		t = 0;
		kol = 0;
		if (liesOn(a1, a2, c1) && liesOn(b1, b2, c2)) {
			t = 1;
		}
		if (liesOn(a1, a2, c2) && liesOn(b1, b2, c1)) {
			t = 2;
		}
		if (t == 0) {
			// System.err.println("2D");
			return false;
		}
		if (t == 1) {
			return dividesWell(a1, a2, c1) && dividesWell(b1, b2, c2);
		} else {
			return dividesWell(a1, a2, c2) && dividesWell(b1, b2, c1);
		}

	}
}