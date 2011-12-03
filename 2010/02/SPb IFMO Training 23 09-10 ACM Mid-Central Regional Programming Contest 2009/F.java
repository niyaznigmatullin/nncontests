import java.util.*;
import java.math.*;
import java.io.*;

public class F implements Runnable {
	public static void main(String[] args) {
		new Thread(new F()).start();
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
			br = new BufferedReader(new FileReader(new File("rps.in")));
			out = new PrintWriter("rps.out");
			solve();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(111);
		}
		// System.err.println(System.currentTimeMillis() - time);
		out.close();
	}

	void solve() {
		while (solve2())
			;
	}

	boolean solve2() {
		String s1 = nextToken();
		String s2 = nextToken();
		if (s1.equals("E") && s2.equals("E")) {
			return false;
		}
		int w1 = 0, w2 = 0;
		for (int i = 0; i < s1.length(); i++) {
			char c1 = s1.charAt(i);
			char c2 = s2.charAt(i);
			if (c1 == c2) {
				continue;
			}
			if (c1 == 'R') {
				if (c2 == 'S') {
					w1++;
				} else {
					w2++;
				}
			} else if (c1 == 'S') {
				if (c2 == 'R') {
					w2++;
				} else
					w1++;
			} else {
				if (c2 == 'R') {
					w1++;
				} else
					w2++;
			}
		}
		out.println("P1: " + w1);
		out.println("P2: " + w2);
		return true;
	}

}