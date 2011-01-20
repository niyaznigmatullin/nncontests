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

	final double EPS = 1e-9, EPS2 = 1e-7, EPS3 = 1e-7;

	void solve() {
		String s1 = nextToken();
		String s2 = nextToken();
		int n = s1.length();
		int m = s2.length();
		if (n != m) {
			out.println("No");
			return;
		}
		char[] c = (s2 + "#" + s1).toCharArray();
		int k = -1;
		int[] p = new int[c.length];
		p[0] = -1;
		for (int i = 1; i < c.length; i++) {
			while (k > -1 && c[k + 1] != c[i])
				k = p[k];
			if (c[k + 1] == c[i])
				k++;
			p[i] = k;
		}
		int cur = 0;
		while (cur < n && s1.charAt(cur) == s2.charAt(n - cur - 1))
			cur++;
		int ans = Integer.MAX_VALUE;
		k = p[c.length - 1];
		while (k > -1) {
			if (k + 1 + cur >= n) {
				ans = Math.min(ans, n - k - 1);
			}
			k = p[k];
		}
		if (ans == Integer.MAX_VALUE) {
			out.println("No");
		} else {
			out.println("Yes\n" + ans);
		}
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
		// if (DEBUG) System.err.println(System.currentTimeMillis() - time);
		if (std)
			out.flush();
		else
			out.close();
	}
}