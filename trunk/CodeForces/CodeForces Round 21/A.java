import java.io.*;
import java.util.*;

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
	BufferedReader br;
	PrintWriter out;

	public void run() {
		Locale.setDefault(Locale.US);
		try {
			// sc = new FastScanner(System.in);
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			solve();
			// sc.close();
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

	boolean check(String userName) {
		boolean ans = userName.length() >= 1 && userName.length() <= 16;
		if (!ans || userName.split("[a-zA-Z0-9_]").length != 0) {
			return false;
		}
		return true;
	}

	void solve() throws IOException {
		String s = br.readLine();
		int at = s.indexOf("@");
		if (at == -1) {
			out.println("NO");
			return;
		}
		String userName = s.substring(0, at);
		if (!check(userName)) {
			out.println("NO");
			return;
		}
		s = s.substring(at + 1);
		System.err.println(s);
		System.err.println(s.split("\\.").length);
		String hostName = null;
		String resource = "";
		if (s.contains("/")) {
			int slash = s.indexOf("/");
			hostName = s.substring(0, slash);
			resource = s.substring(slash + 1);
		} else {
			hostName = s;
		}
		{
			if (hostName.length() < 1 || hostName.length() > 32
					|| hostName.charAt(0) == '.'
					|| hostName.charAt(hostName.length() - 1) == '.') {
				out.println("NO");
				return;
			}
			while (true) {
				int p = hostName.indexOf(".");
				if (p == -1) {
					if (!check(hostName)) {
						out.println("NO");
						return;
					}
					break;
				}
				String e = hostName.substring(0, p);
				if (!check(e)) {
					out.println("NO");
					return;
				}
				hostName = hostName.substring(p + 1);
			}
		}
		if (!resource.isEmpty() && !check(resource)) {
			out.println("NO");
			return;
		}
		out.println("YES");
		System.err.println(Arrays.toString("asdasf.asdasd".split(".")));
	}
}