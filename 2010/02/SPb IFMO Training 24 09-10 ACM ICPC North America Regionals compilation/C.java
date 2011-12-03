import java.util.*;
import java.math.*;
import java.io.*;

public class C implements Runnable {
	public static void main(String[] args) {
		new Thread(new C()).start();
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
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			// br = new BufferedReader(new FileReader("input.txt"));
			// out = new PrintWriter("output.txt");
			solve();
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(111);
		}
		// System.err.println(System.currentTimeMillis() - time);
	}

	void solve() {
		int q = 0;
		while (solve2(++q))
			;
	}

	boolean solve2(int test) {
		String s = nextToken();
		int n = nextInt();
		if (eof)
			return false;
		ArrayList<String> dict = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			dict.add(nextToken());
		}
		int sz = dict.size() - 1;
		String last = null;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length();) {
			int j = i + (sz + "").length();
			String t = s.substring(i, j);
			int y = Integer.parseInt(t);
			if (y >= dict.size()) {
				if (last != null) {
					dict.add(last + last.charAt(0));
				}
				sb.append(dict.get(y));
				last = dict.get(y);
			} else {
				if (last != null) {
					dict.add(last + dict.get(y).charAt(0));
				}
				sb.append(dict.get(y));
				last = dict.get(y);
			}
			i = j;
			sz++;
		}
		out.println("Case " + test + ": " + sb);
		return true;
	}
}