import java.io.*;
import java.util.*;

public class C {

	static void solve() throws IOException {
		int n = nextInt();
		int q = nextInt();
		int maximal = Integer.MIN_VALUE;
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = nextInt();
			maximal = Math.max(maximal, a[i]);
		}
		Query[] qs = new Query[q + 1];
		for (int i = 0; i < q; i++) {
			qs[i] = new Query(nextInt() - 1, nextInt() - 1, nextDouble());
		}
		qs[q] = new Query(-1, n, 0);
		++q;
		for (Query e : qs) {
			int cur = Integer.MIN_VALUE;
			for (int i = e.left; i <= e.right; i++) {
				if (i >= 0 && i < n)
					cur = Math.max(cur, a[i]);
			}
			e.maximum = cur;
		}
		Arrays.sort(qs, new Comparator<Query>() {
			@Override
			public int compare(Query o1, Query o2) {
				int c = Integer.compare(o1.left, o2.left);
				if (c != 0)
					return c;
				return -Integer.compare(o1.right, o2.right);
			}
		});
		{
			Query[] st = new Query[q];
			int cn = 0;
			for (int i = 0; i < q; i++) {
				while (cn > 0 && st[cn - 1].right < qs[i].left)
					--cn;
				if (cn > 0) {
					st[cn - 1].edges.add(qs[i]);
				}
				st[cn++] = qs[i];
			}
		}
		double[] probs = qs[0].solve();
		double ans = maximal;
		for (int i = 0; i < probs.length; i++) {
			ans += probs[i] * i;
		}
		out.println(ans);
	}

	static class Query {
		int left;
		int right;
		double probability;
		List<Query> edges;
		double[] probs;
		int maximum;

		public Query(int left, int right, double probability) {
			this.left = left;
			this.right = right;
			this.probability = probability;
			edges = new ArrayList<>();
		}

		double[] solve() {
			double[] val = new double[1];
			val[0] = 1.;
			for (Query e : edges) {
				double[] cur = e.solve();
				double[] nVal = new double[val.length + cur.length - 1];
				for (int i = 0; i < val.length; i++) {
					double val1 = val[i];
					if (val1 == 0)
						continue;
					int v1 = maximum + i;
					for (int j = 0; j < cur.length; j++) {
						double val2 = cur[j];
						if (val2 == 0)
							continue;
						int v2 = e.maximum + j;
						int where = Math.max(v1, v2) - maximum;
						if (where >= 0)
							nVal[where] += val1 * val2;
					}
				}
				val = nVal;
			}
			val = Arrays.copyOf(val, val.length + 1);
			for (int i = val.length - 1; i > 0; i--) {
				val[i] += val[i - 1] * probability;
				val[i - 1] = val[i - 1] * (1. - probability);
			}
			return probs = val;
		}

	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("c.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("c.out");
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
