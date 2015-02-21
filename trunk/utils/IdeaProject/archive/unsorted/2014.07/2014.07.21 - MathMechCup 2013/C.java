import java.io.*;
import java.util.*;

public class C {

	static void solve() throws IOException {
		int t = nextInt();
		for (int currentTest = 0; currentTest < t; currentTest++) {
			int x1 = nextInt();
			int y1 = nextInt();
			int x2 = nextInt();
			int y2 = nextInt();
			if (y1 < 0 || y2 < 0 || (y1 == 0 && y2 == 0)) {
				out.println(0);
				out.println();
				continue;
			}
			long[] ans = new long[2];
			int cn = 0;
			{
				long a = y2 - y1;
				long b = x1 - x2;
				long c = -a * x1 - b * y1;
				if (a != 0 && c % a == 0) {
					ans[cn++] = -c / a;
				}
			}
			if (y1 > 0 && y2 > 0) {
				long dx = x2 - x1;
				if (dx * y1 % (y1 + y2) == 0) {
					ans[cn++] = dx * y1 / (y1 + y2) + x1;
				}
			}
			if (cn == 2 && ans[0] == ans[1])
				--cn;
			out.println(cn);
			Arrays.sort(ans, 0, cn);
			for (int i = 0; i < cn; i++) {
				if (i > 0)
					out.print(' ');
				out.print(ans[i]);
			}
			out.println();
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("holy.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("holy.out");
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
