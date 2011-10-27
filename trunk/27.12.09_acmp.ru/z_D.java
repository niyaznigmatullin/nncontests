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
				st = new StringTokenizer(br.readLine(), " :");
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
		try {
			br = new BufferedReader(new FileReader(new File("input.txt")));
			out = new PrintWriter(new File("output.txt"));
			solve();
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(456);
		}
	}

	boolean isPal(String s) {
		return new StringBuilder(s).reverse().toString().equals(s);
	}

	void solve() {
		int time = nextInt() * 60 + nextInt();
		while (true) {
			time = (time + 1) % 1440;
			String s1 = time / 60 + "";
			String s2 = time % 60 + "";
			if (s1.length() < 2)
				s1 = "0" + s1;
			if (s2.length() < 2)
				s2 = "0" + s2;
			String s = s1 + ":" + s2;
			if (isPal(s)) {
				out.println(s);
				return;
			}
		}
	}
}