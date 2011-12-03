import static java.util.Arrays.deepToString;

import java.io.*;
import java.util.*;

public class C {

	static void solve() throws Exception {
		List<List<String>> all = new ArrayList<List<String>>();
		while (true) {
			List<String> paragraph = new ArrayList<String>();
			boolean end = false;
			while (true) {
				String s = br.readLine();
				if (s == null) {
					end = true;
					break;
				}
				s = s.trim();
				if (s.isEmpty()) {
					break;
				}
				paragraph.add(s);
			}
			if (!paragraph.isEmpty()) {
				all.add(paragraph);
			}
			if (end) {
				break;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (List<String> e : all) {
			String got = printParagraph(e);
			if (!got.isEmpty()) {
				if (sb.length() > 0) {
					sb.append('\n');
				}
				sb.append(got);
			}
		}
		out.print(sb);
	}

	static String printParagraph(List<String> all) {
		StringBuilder ans = new StringBuilder();
		for (String e : all) {
			String line = printLine(e);
			if (line.length() > 60) {
				continue;
			}
			ans.append(line).append('\n');
		}
		return ans.toString();
	}
	
	static String printLine(String s) {
		StringTokenizer st = new StringTokenizer(s);
		StringBuilder sb = new StringBuilder();
		while (st.hasMoreTokens()) {
			if (sb.length() > 0) {
				sb.append(' ');
			}
			sb.append(st.nextToken());
		}
		return sb.toString();
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
		try {
			long time = System.currentTimeMillis();
			File file = new File("c.in");
			InputStream input = System.in;
			OutputStream output = System.out;
			if (file.canRead()) {
				input = (new FileInputStream(file));
				output = (new PrintStream("c.out"));
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