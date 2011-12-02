import java.io.*;
import java.util.*;

public class D implements Runnable {
	public static void main(String[] args) {
		new Thread(new D()).start();
	}

	BufferedReader br;
	PrintWriter out;
	StringTokenizer st;
	boolean eof;

	String nextToken() {
		while (st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (Exception e) {
				eof = true;
				return "0";
			}
		}
		return st.nextToken();
	}

	public void run() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			solve();
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
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

	final double EPS = 1e-9;

	double qEq(double a, double b, double c) {
		double d = b * b - 4 * a * c;
		if (d < -EPS) {
			return Double.NaN;
		}
		return Math.max((-b + Math.sqrt(d)) / (2 * a), (-b - Math.sqrt(d))
				/ (2 * a));
	}

	void solve() {
		double a = nextDouble();
		double v = nextDouble();
		double d = nextDouble();
		double l = nextDouble();
		double w = nextDouble();
		double t1 = w / a;
		double d1 = a * t1 * t1 * .5;
		if (d1 >= l - EPS) {
			double t2 = v / a;
			double d2 = t2 * t2 * a * .5;
			if (d2 >= d - EPS) {
				out.println(Math.sqrt(2 * d / a));
			} else {
				out.println(t2 + (d - d2) / v);
			}
			return;
		}
		double v1 = Math.sqrt(w * w / 2 + l * a);
		// System.err.println((w / a) * (w / a) * a / 2 + 2
		// * (((v1 - w) / a) * ((v1 - w) / a) * a / 2) + (v1 - w) * w / a);
		if (v1 < v + EPS) {
			double t2 = 2 * (v1 - w) / a;
			double t3 = (v - w) / a;
			double d3 = t3 * t3 * a * .5 + w * t3;
			if (d3 >= d - l - EPS) {
				out.println(t1 + t2 + qEq(a * .5, w, l - d));
			} else {
				out.println(t1 + t2 + t3 + (d - l - d3) / v);
			}
		} else {
			double t2 = 2 * (v - w) / a;
			double t3 = (v - w) / a;
			double d2 = d1 + (a * t3 * t3 * .5 + w * t3) * 2;
			double d3 = t3 * t3 * a * .5 + w * t3;
			if (d3 >= d - l - EPS) {
				out.println(t1 + t2 + (l - d2) / v + qEq(a * .5, w, l - d));
			} else {
				out.println(t1 + t2 + (l - d2) / v + t3 + (d - l - d3) / v);
			}
		}
	}
}
