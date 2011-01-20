import java.util.*;
import java.math.*;
import java.io.*;

import static java.math.BigInteger.*;

public class Main implements Runnable {
	public static void main(String[] args) {
		new Thread(new Main()).start();
	}

	StringTokenizer st;
	PrintWriter out;
	BufferedReader br;
	boolean eof = false, in_out = false, std = false;

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

	long gcd(long x, long y) {
		if (x == 0)
			return y;
		return gcd(y % x, x);
	}

	long lcm(long x, long y) {
		return x / gcd(x, y) * y;
	}

	void solve() {
		int a = nextInt();
		int b = nextInt();
		int c = nextInt();
		int d = nextInt();
		long l = lcm(lcm(a, b), c);
		long da = l / a;
		long db = l / b;
		long dc = l / c;
		long dd = l - da - db - dc;
		if (dd <= 0 || (d * l) % dd != 0) {
			out.println(-1);
			return;
		}
		long n = (d * l) / dd;
		if (n % a != 0 || n % b != 0 || n % c != 0) {
			out.println(-1);
			return;
		}
		out.println(n);
	}

	public void run() {
		// long time = System.currentTimeMillis();
		try {
			br = new BufferedReader(new FileReader(new File("input.txt")));
			out = new PrintWriter(new File("output.txt"));
			solve();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(111);
		}
		// System.err.println(System.currentTimeMillis() - time);
		if (std)
			out.flush();
		else
			out.close();
	}
}