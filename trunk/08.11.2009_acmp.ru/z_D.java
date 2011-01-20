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

	private String nextString() {
		return nextToken();
	}

	private int nextInt() {
		return Integer.parseInt(nextToken());
	}

	private long nextLong() {
		return Long.parseLong(nextToken());
	}

	private double nextDouble() {
		return Double.parseDouble(nextToken());
	}

	private BigInteger nextBigInteger() {
		return new BigInteger(nextToken());
	}

	String INFILE = "choko.in", OUTFILE = "choko.out";
	boolean eof = false, in_out = false, std = false;

	class E implements Comparable<E> {
		int a;
		int num;

		E(int aa, int nn) {
			a = aa;
			num = nn;
		}

		public int compareTo(E e) {
			if (a != e.a)
				return a - e.a;
			return num - e.num;
		}
	}

	double area(double a, double b, double c) {
		double p = (a + b + c) * .5;
		return Math.sqrt(p * (p - a) * (p - b) * (p - c));
	}

	private void solve() {
		int n = nextInt();
		E[] a = new E[n];
		for (int i = 0; i < n; i++) {
			a[i] = new E(nextInt(), i + 1);
		}
		Arrays.sort(a);
		double ar = Double.NEGATIVE_INFINITY;
		int id1 = -1, id2 = -1, id3 = -1;
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++)
				for (int k = j + 1; k < n; k++) {
					if (a[i].a < a[j].a + a[k].a) {
						double cc = area(a[i].a, a[j].a, a[k].a);
						if (ar < cc) {
							ar = cc;
							id1 = a[i].num;
							id2 = a[j].num;
							id3 = a[k].num;
						}
					}
				}
		if (id1 == -1)
			out.println(-1); else {
				out.println(ar);
				out.println(id1 + " " + id2 + " " + id3);
			}
	}

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