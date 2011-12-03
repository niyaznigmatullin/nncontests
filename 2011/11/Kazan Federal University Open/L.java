import static java.util.Arrays.deepToString;
import static java.util.Arrays.fill;

import java.io.*;
import java.util.*;

public class L extends Thread {

	static void solve() throws Exception {
		String s = next();
		if (isC(s)) {
			out.println(makeJava(s));
			return;
		}
		if (isJava(s)) {
			out.println(makeC(s));
			return;
		}
		out.println("Wrong name :(");
	}

	static boolean isC(String s) {
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 'A' && c <= 'Z') {
				return false;
			}
		}
		if (s.charAt(0) == '_' || s.charAt(s.length() - 1) == '_'
				|| s.indexOf("__") >= 0) {
			return false;
		}
		return true;
	}

	static String makeJava(String s) {
		String[] t = s.split("_");
		StringBuilder sb = new StringBuilder();
		for (String e : t) {
			if (sb.length() == 0) {
				sb.append(e);
				continue;
			}
			char c = e.charAt(0);
			sb.append(Character.toUpperCase(c) + e.substring(1));
		}
		return sb.toString();
	}

	static boolean isJava(String s) {
		if (s.indexOf('_') >= 0) {
			return false;
		}
		if (s.charAt(0) >= 'A' && s.charAt(0) <= 'Z') {
			return false;
		}
		return true;
	}
	
	static String makeC(String s) {
		StringBuilder sb = new StringBuilder();
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 'A' && c <= 'Z') {
				ret.append(sb).append('_');
				sb.setLength(0);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		ret.append(sb);
		return ret.toString();
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static void debug(Object... a) {
		System.err.println(deepToString(a));
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null) {
				return null;
			}
			st = new StringTokenizer(line);
		}
		return st.nextToken();
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	public static void main(String[] args) {
		new Thread(null, new L(), "", 1 << 24).start();
	}

	public void run() {
		try {
			long time = System.currentTimeMillis();
			File file = new File("l.in");
			InputStream input = System.in;
			OutputStream output = System.out;
			if (file.canRead()) {
				input = (new FileInputStream(file));
				output = (new PrintStream("l.out"));
			}
			br = new BufferedReader(new InputStreamReader(input));
			out = new PrintWriter(output);
			solve();
			out.close();
			br.close();
			System.err.println("Runtime = "
					+ (System.currentTimeMillis() - time) + "ms");
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}