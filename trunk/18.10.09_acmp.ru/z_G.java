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
	boolean eof = false, in_out = false, std = false;

	private String nextToken() {
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

	@SuppressWarnings("unused")
	private String nextLine() {
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

	@SuppressWarnings("unused")
	private String nextString() {
		return nextToken();
	}

	private int nextInt() {
		return Integer.parseInt(nextToken());
	}

	@SuppressWarnings("unused")
	private long nextLong() {
		return Long.parseLong(nextToken());
	}

	@SuppressWarnings("unused")
	private double nextDouble() {
		return Double.parseDouble(nextToken());
	}

	@SuppressWarnings("unused")
	private BigInteger nextBigInteger() {
		return new BigInteger(nextToken());
	}

	String INFILE = "chaincode.in", OUTFILE = "chaincode.out";

	class Ans {
		int len;
		String[] a;

		Ans(int l, String[] b, String c) {
			a = new String[l];
			System.arraycopy(b, 0, a, 0, b.length);
			a[l - 1] = c;
			len = l;
		}

		Ans(int l, String... s) {
			a = s;
			len = l;
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int i = a.length - 1; i >= 0; i--) {
				sb.append(a[i]);
				sb.append(" ");
			}
			return sb.toString();
		}
	}

	ArrayList<Ans> ans;

	ArrayList<Ans> rec(int k, int n) {		
		ArrayList<Ans> ret = new ArrayList<Ans>();
		if (n <= 0) return new ArrayList<Ans>();
		if (k == 1) {
			if (n == 50) {
				ret.add(new Ans(1, "Bull"));
			}
			if (n % 2 == 0 && n / 2 <= 20) {
				ret.add(new Ans(1, "D" + (n / 2)));
			}
		} else {			 
			for (int i = 1; i < 21; i++) {
				if (n - i <= 0) break;
				ArrayList<Ans> t = rec(k - 1, n - i);
				for (Ans e : t) {
					ret.add(new Ans(e.len + 1, e.a, "" + i));
				}
			}	
			if (n < 25) {
				ArrayList<Ans> t = rec(k - 1, n - 25);
				for (Ans e : t) {
					ret.add(new Ans(e.len + 1, e.a, "25"));
				}
			}
			for (int i = 1; i < 21; i++) {
				if (n - 2 * i <= 0) break;
				ArrayList<Ans> t = rec(k - 1, n - 2 * i);
				for (Ans e : t) {
					ret.add(new Ans(e.len + 1, e.a, "D" + (i)));
				}
			}	
			if (n < 50) {
				ArrayList<Ans> t = rec(k - 1, n - 50);
				for (Ans e : t) {
					ret.add(new Ans(e.len + 1, e.a, "Bull"));
				}
			}
			for (int i = 1; i < 21; i++) {
				if (n - 3 * i <= 0) break;
				ArrayList<Ans> t = rec(k - 1, n - 3 * i);
				for (Ans e : t) {
					ret.add(new Ans(e.len + 1, e.a, "T" + (i)));
				}
			}
		}
		return ret;
	}
	private void solve() {
		int n = nextInt();
		ans = new ArrayList<Ans>();
		ans.addAll(rec(3, n));
		ans.addAll(rec(2, n));
		ans.addAll(rec(1, n));
		out.println(ans.size());
		for (Ans e : ans) {
			out.println(e);
		}
	}
//Дартс
	public void run() {
		// long time = System.currentTimeMillis();
		try {
			br = std ? new BufferedReader(new InputStreamReader(System.in))
					: new BufferedReader(new FileReader(new File(
							(in_out) ? INFILE : "input.txt")));
			out = std ? new PrintWriter(new OutputStreamWriter(System.out))
					: new PrintWriter(new File((in_out) ? OUTFILE
							: "output.txt"));
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