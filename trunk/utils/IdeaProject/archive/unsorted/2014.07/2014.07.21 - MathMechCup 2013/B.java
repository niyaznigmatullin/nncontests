import java.io.*;
import java.util.*;

public class B {

	static void solve() throws IOException {
		int n = nextInt();
		int[] t = new int[n];
		String[] s = new String[n];
		for (int i = 0; i < n; i++) {
			t[i] = readTime();
			s[i] = next();
		}
		int q = nextInt();
		for (int i = 0; i < q; i++) {
			int ct = readTime();
			int best = -1;
			int bestD = Integer.MAX_VALUE;
			for (int j = 0; j < n; j++) {
				int d = t[j] - ct;
				if (d < 0)
					d += 1440;
				if (bestD > d) {
					best = j;
					bestD = d;
				}
			}
			out.println(String.format("%02d:%02d", bestD / 60, bestD % 60));
			out.println(s[best]);
		}
	}

	static int readTime() throws IOException {
		String s = next();
		return (s.charAt(0) * 10 + s.charAt(1) - 11 * '0') * 60
				+ (s.charAt(3) * 10 + s.charAt(4) - '0' * 11);
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("timetable.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("timetable.out");
		}
		br = new BufferedReader(new InputStreamReader(input));
		out = new PrintWriter(output);
		solve();
		out.close();
		br.close();
	}

	static boolean hasNext() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null) {
				return false;
			}
			st = new StringTokenizer(line);
		}
		return true;
	}

	static String next() throws IOException {
		return hasNext() ? st.nextToken() : null;
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}
}
