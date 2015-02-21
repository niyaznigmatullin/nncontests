import java.io.*;
import java.util.*;

public class G {

	static Set<Integer> set;

	static int[] primes = { 2, 3, 5, 7 };

	static void go(int x, int c) {
		if (c == primes.length) {
			set.add(x);
			return;
		}
		go(x, c + 1);
		while ((long) x * primes[c] <= 1000000000) {
			x *= primes[c];
			go(x, c + 1);
		}
	}

	static void solve() throws IOException {
		set = new HashSet<>();
		go(1, 0);
		int[] a = new int[set.size()];
		int cn = 0;
		for (int i : set) {
			a[cn++] = i;
		}
		Arrays.sort(a);
		int t = nextInt();
		for (int ct = 0; ct < t; ct++) {
			int n = nextInt();
			boolean[] win = new boolean[a.length];
			for (int i = a.length - 1; i >= 0; i--) {
				if (a[i] >= n)
					continue;
				for (int j = 2; j < 10; j++) {
					long z = (long) j * a[i];
					if (z >= n || !win[Arrays.binarySearch(a, (int) z)]) {
						win[i] = true;
						break;
					}
				}
			}
			if (win[0]) {
				out.println(1);
			} else {
				out.println(2);
			}
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("g.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("g.out");
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
