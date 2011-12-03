import java.util.*;
import java.math.*;
import java.io.*;

public class E implements Runnable {
	public static void main(String[] args) {
		new Thread(new E()).start();
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
			br = new BufferedReader(new FileReader(new File("dup.in")));
			out = new PrintWriter("dup.out");
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
		int n = nextInt();
		if (n == 0)
			return false;
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = nextInt();
		}
		for (int i = 0; i < n;) {
			int j = i;
			while (j < n && a[i] == a[j])
				j++;
			out.print(a[i] + " ");
			i = j;
		}
		out.println("$");
		return true;
	}
}