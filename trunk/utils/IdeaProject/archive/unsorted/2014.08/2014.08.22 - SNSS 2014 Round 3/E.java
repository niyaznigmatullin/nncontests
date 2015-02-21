import java.io.*;
import java.util.*;

public class E {

	static void solve() throws IOException {
		int testCount = nextInt();
		for (int curTest = 0; curTest < testCount; curTest++) {
			int s = nextInt();
			int c = nextInt();
			int n = nextInt();
			int ans = -1;
			int[] g = new int[n];
			int[] l = new int[n];
			for (int i = 0; i < n; i++) {
				g[i] = nextInt();
				l[i] = nextInt();
			}
			for (int i = 0; i < n; i++) {
				if (i > 2) {
					s += l[i - 3];
					s += g[i - 3];
				}
				if (s < c) {
					ans = i;
					break;
				}
				s -= l[i];
				++c;
			}			
			if (ans < 0)
				out.println("Ok");
			else
				out.println(ans + 1);
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("e.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("e.out");
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
