import java.io.*;
import java.util.*;

public class H {

	private static void solve() throws IOException {
		Map<String, String> values = new HashMap<String, String>();
		while (true) {
			String s = br.readLine();
			if (s == null) {
				break;
			}
			if (s.startsWith("GET")) {
				String var = s.substring(s.lastIndexOf(' ') + 1);
				String value = values.get(var);
				if (value != null) {
					out.println("VALUE " + var + " 0 " + value.length());
					out.println(value);
				}
				out.println("END");
			} else {
				StringTokenizer tok = new StringTokenizer(s);
				tok.nextToken();
				String name = tok.nextToken();
				tok.nextToken();
				tok.nextToken();
				int length = Integer.parseInt(tok.nextToken());
				String value = br.readLine();
				values.put(name, value);
				out.println("STORED");
			}
		}
	}

	public static void main(String[] args) {
		try {
			br = new BufferedReader(new FileReader("memcached.in"));
			out = new PrintWriter("memcached.out");
			solve();
			out.close();
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(239);
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static String nextToken() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null)
				return null;
			st = new StringTokenizer(line);
		}
		return st.nextToken();
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
}