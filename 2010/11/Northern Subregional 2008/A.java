import java.io.*;
import java.util.*;
import java.math.*;

public class A implements Runnable {
	public static void main(String[] args) {
		new A().run();
	}

	class FastScanner {
		BufferedReader br;
		StringTokenizer st;
		boolean eof;
		String buf;

		public FastScanner(String fileName) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(fileName));
			nextToken();
		}

		public FastScanner(InputStream stream) {
			br = new BufferedReader(new InputStreamReader(stream));
			nextToken();
		}

		String nextToken() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (Exception e) {
					eof = true;
					break;
				}
			}
			String ret = buf;
			buf = eof ? "-1" : st.nextToken();
			return ret;
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

		void close() {
			try {
				br.close();
			} catch (Exception e) {

			}
		}

		boolean isEOF() {
			return eof;
		}
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
		Locale.setDefault(Locale.US);
		try {
			sc = new FastScanner("access.in");
			out = new PrintWriter("access.out");
			solve();
			sc.close();
			out.close();
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	int nextInt() {
		return sc.nextInt();
	}

	String nextToken() {
		return sc.nextToken();
	}

	long nextLong() {
		return sc.nextLong();
	}

	double nextDouble() {
		return sc.nextDouble();
	}

	BigInteger nextBigInteger() {
		return sc.nextBigInteger();
	}

	void solve() {
		int n = nextInt();
		HashMap<Long, Integer>[] hm = new HashMap[33];
		for (int i = 0; i < hm.length; i++) {
			hm[i] = new HashMap<Long, Integer>();
		}
		boolean[] allows = new boolean[n];
		for (int i = 0; i < n; i++) {
			String s = nextToken();
			nextToken();
			String ip = nextToken();
			if (ip.indexOf("/") == -1) {
				ip = ip + "/32";
			}
			allows[i] = s.equals("allow");
			int p = ip.indexOf("/");
			String s1 = ip.substring(0, p);
			String s2 = ip.substring(p + 1);
			int e = Integer.parseInt(s2);
			long r = ipToInteger(s1);
			r &= ~((1L << (32 - e)) - 1);
			if (!hm[e].containsKey(r))
				hm[e].put(r, i);
			// System.err.println("Query " + i + " = " + r);
		}
		int m = nextInt();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			String ip = nextToken();
			long r = ipToInteger(ip);
			int min = Integer.MAX_VALUE;
			// System.err.println("Cur = " + i);
			for (int j = 0; j <= 32; j++) {
				long e = r;
				e &= ~((1L << (32 - j)) - 1);
				// System.err.println(e + " " + j);
				if (hm[j].containsKey(e)) {
					min = Math.min(min, hm[j].get(e));
				}
			}
			if (min == Integer.MAX_VALUE || allows[min]) {
				sb.append("A");
			} else {
				sb.append("D");
			}
		}
		out.println(sb);
	}

	long ipToInteger(String s) {
		String[] t = s.split("\\.");
		long a = Long.parseLong(t[0]);
		long b = Integer.parseInt(t[1]);
		long c = Integer.parseInt(t[2]);
		long d = Integer.parseInt(t[3]);
		return (a << 24) | (b << 16) | (c << 8) | d;
	}
}