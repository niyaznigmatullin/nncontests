import java.io.*;
import java.util.*;
import java.math.*;

public class Task6 implements Runnable {
	public static void main(String[] args) {
		new Task6().run();
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
		try {
			sc = new FastScanner("input.txt");
			out = new PrintWriter("output.txt");
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
		int x = nextInt();
		int y = nextInt();
		ArrayList<Integer> d = new ArrayList<Integer>();
		for (int i = 1; i * i <= y; i++) {
			if (y % i != 0) {
				continue;
			}
			d.add(i);
			if (i != y / i) {
				d.add(y / i);
			}
		}
		boolean ok = false;
		for (int i : d) {
			if (i == x) {
				ok = true;
			}
		}
		if (!ok) {
			out.println("Impossible");
			return;
		}
		Collections.sort(d);
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0; i < d.size(); i++) {
			hm.put(d.get(i), i);
		}
		int[] q = new int[d.size()];
		q[0] = d.size() - 1;
		int head = 0;
		int tail = 1;
		boolean[] was = new boolean[d.size()];
		int[] dist = new int[d.size()];
		while (head < tail) {
			int t = q[head++];
			int z = d.get(t);
			for (int i = 2; i * i <= z; i++) {
				if (z % i != 0) {
					continue;
				}
				int newOne = z / i;
				if (!hm.containsKey(newOne)) {				
					continue;
				}
				int num = hm.get(newOne);
				if (was[num]) {
					continue;
				}
				was[num] = true;
				dist[num] = dist[t] + 1;
				q[tail++] = num;
			}
		}
		if (!was[hm.get(x)]) {
			out.println("Impossible");
			return;
		}
		out.println(dist[hm.get(x)]);
	}
}