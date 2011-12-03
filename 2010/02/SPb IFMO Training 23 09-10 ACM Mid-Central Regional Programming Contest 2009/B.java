import java.util.*;
import java.math.*;
import java.io.*;

public class B implements Runnable {
	public static void main(String[] args) {
		new Thread(new B()).start();
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

	public void run() {
		// long time = System.currentTimeMillis();
		try {
			br = new BufferedReader(new FileReader(new File("gnome.in")));
			out = new PrintWriter("gnome.out");
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
		out.println("Gnomes:");
		for (int i = 0; i < n; i++) {
			int a = nextInt();
			int b = nextInt();
			int c = nextInt();
			if (a <= b && b <= c) {
				out.println("Ordered");
			} else if (a >= b && b >= c) {
				out.println("Ordered");
			} else {
				out.println("Unordered");
			}
		}
	}

}