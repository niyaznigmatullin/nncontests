import java.io.*;
import java.util.*;

public class F {

	static long gcd(long a, long b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	static long lcm(long a, long b) {
		return a / gcd(a, b) * b;
	}

	static class Pair {
		long a;
		long mod;

		public Pair(long a, long mod) {
			this.a = a;
			this.mod = mod;
		}

	}

	static void solve() throws IOException {
		int n = nextInt();
		int e = nextInt();
		long lcm = 1;
		for (int i = 2; i <= n; i++) {
			lcm = lcm(lcm, i);
		}
		List<Pair> list = new ArrayList<>();
		list.add(new Pair(0, 1));
		for (int i = 0; i < n; i++) {
			int nMod = i + 1;
			int nCur = nextInt();
			List<Pair> nList = new ArrayList<>();
			for (Pair f : list) {
				long z = lcm(f.mod, nMod) / f.mod;
				for (int x = 0; x < z; x++) {
					if ((f.a + x * f.mod) % nMod == nCur) {
						nList.add(new Pair(f.a + x * f.mod, z * f.mod));
					}
				}
			}
			list = nList;
		}
		long ans = -1;
		for (Pair f : list) {
			long cur1, cur2;
			if (e < f.a) {
				cur1 = cur2 = f.a;
			} else {
				cur1 = (e - f.a) / f.mod * f.mod + f.a;
				cur2 = cur1 + f.mod;
			}
			if (cur1 == 0) cur1 += f.mod;
			if (cur2 == 0) cur2 += f.mod;
			if (ans < 0 || Math.abs(e - cur1) < Math.abs(ans - e)
					|| Math.abs(e - cur1) == Math.abs(ans - e) && cur1 < ans) {
				ans = cur1;
			}
			if (ans < 0 || Math.abs(e - cur2) < Math.abs(ans - e)
					|| Math.abs(e - cur2) == Math.abs(ans - e) && cur2 < ans) {
				ans = cur2;
			}
		}
		out.println(ans);
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("f.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("f.out");
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
