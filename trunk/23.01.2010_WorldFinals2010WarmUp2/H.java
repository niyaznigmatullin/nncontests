import java.util.*;
import java.math.*;
import java.io.*;

public class Main implements Runnable {
	public static void main(String[] args) {
		new Thread(new Main()).start();
	}

	StringTokenizer st;
	PrintWriter out;
	BufferedReader br;
	boolean eof, std;

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

	String nextLine() {
		String ret = "";
		try {
			ret = br.readLine();
		} catch (Exception e) {
			ret = "";
		}
		if (ret == null) {
			eof = true;
			return "$";
		}
		return ret;
	}

	String nextString() {
		return nextToken();
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

	BigInteger nextBigInteger() {
		return new BigInteger(nextToken());
	}

	void genTest() {
		out.println(300);
		Random rand = new Random();
		for (int t = 0; t < 300; t++) {
			int x = rand.nextInt(2000000) + 1;
			int y = rand.nextInt(2000000) + 1;
			out
					.print(x / 10 + "." + x % 10 + " " + y / 10 + "." + y % 10
							+ " ");
			x = rand.nextInt(2000000) + 1;
			y = rand.nextInt(2000000) + 1;
			out.println(x / 10 + "." + x % 10 + " " + y / 10 + "." + y % 10);
		}
		out.close();
	}

	public void run() {
		// long time = System.currentTimeMillis();
		std = System.getProperty("ONLINE_JUDGE") != null;
		try {
			// out = new PrintWriter("input.txt");
			// genTest();
			// br = new BufferedReader(new FileReader(new File("input.txt")));
			// out = new PrintWriter(new File("output.txt"));
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			solve();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(111);
		}
		// System.err.println(System.currentTimeMillis() - time);
		out.close();
	}

	void solve() {
		int n = nextInt();
		for (int i = 0; i < n; i++) {
			solve2();
		}
	}

	int c, x, y;

	int gcd(int a, int b) {
		if (b == 0) {
			int t = a;
			a = b;
			b = t;
		}
		if (a == 0) {
			x = 0;
			if (c % b != 0) {
				return -1;
			}
			y = c / b;
			return b;
		}
		int g = gcd(((b % a) + a) % a, a);
		if (g == -1) {
			return -1;
		}
		int x1 = y - (b / a) * x;
		int y1 = x;
		x = x1;
		y = y1;
		return g;
	}

	int sign(int a) {
		if (a == 0)
			return 0;
		else if (a < 0)
			return -1;
		else
			return 1;
	}

	void solve2() {
		double x1 = nextDouble();
		double y1 = nextDouble();
		double x2 = nextDouble();
		double y2 = nextDouble();
		double la = y2 - y1;
		double lb = x1 - x2;
		double lc = x1 * la + y1 * lb;
		int a = (int) (la * 100);
		int b = (int) (lb * 100);
		c = (int) (lc * 100);
		if (b < 0) {
			a = -a;
			b = -b;
			c = -c;
		}
		// System.err.println(a + " " + b + " " + c);
		int g;
		int xI1 = (int) (x1 + .91);
		int xI2 = (int) x2;
		if ((g = gcd(Math.abs(a), Math.abs(b))) == -1) {
			out.println(0);
			return;
		}
		x *= sign(a);
		y *= sign(b);
		// System.err.println("GCD = " + g);
		// System.err.println(x + " " + y);
		// System.err.println("INT =" + xI1 + " " + xI2);
		int l = -1000000, r = 1000000;
		while (l < r - 1) {
			int m = (l + r) / 2;
			if (x + (long) b / g * m < xI1) {
				l = m;
			} else
				r = m;
		}
		int ans1 = r;
		l = -1000000;
		r = 1000000;
		while (l < r - 1) {
			int m = (l + r) / 2;
			if (x + (long) b / g * m > xI2) {
				r = m;
			} else {
				l = m;
			}
		}
		int ans2 = l;
		// System.err.println("Ans = " + ans1 + " " + ans2);
		out.println(Math.max(0, ans2 - ans1 + 1));
	}
}