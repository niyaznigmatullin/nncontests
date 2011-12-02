import java.io.*;
import java.util.*;
import java.math.*;

public class I implements Runnable {
	public static void main(String[] args) {
		new I().run();
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
		Locale.setDefault(Locale.US);
		try {
			sc = new FastScanner(System.in);
			out = new PrintWriter(System.out);
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

	class State {
		HashSet<Integer>[] d;

		public State(HashSet<Integer>[] d) {
			this.d = d;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + Arrays.hashCode(d);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			State other = (State) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (!Arrays.equals(d, other.d))
				return false;
			return true;
		}

		private I getOuterType() {
			return I.this;
		}

		public State getState(int x, int y, int length) {
			if (length == d.length) {
				HashSet<Integer>[] e = new HashSet[d.length + 1];
				for (int i = 0; i < d.length; i++) {
					e[i] = new HashSet<Integer>(10);
					e[i].addAll(d[i]);
					if (i == x) {
						e[i].remove(y);
					}
				}
				e[d.length] = new HashSet<Integer>(10);
				e[d.length].add(y);
				Arrays.sort(e, byFirstHash);
				return new State(e);
			}
			if (d[x].size() == 1) {
				HashSet<Integer>[] e = new HashSet[d.length - 1];
				for (int i = 0, j = 0; i < d.length; i++) {
					if (i == x) {
						continue;
					}
					e[j] = new HashSet<Integer>(10);
					e[j].addAll(d[i]);
					if (i == length) {
						e[j].add(y);
					}
					j++;
				}
				Arrays.sort(e, byFirstHash);
				return new State(e);
			}
			HashSet<Integer>[] e = new HashSet[d.length];
			for (int i = 0; i < d.length; i++) {
				e[i] = new HashSet<Integer>(10);
				e[i].addAll(d[i]);
				if (i == x) {
					e[i].remove(y);
				}
			}
			e[length].add(y);
			Arrays.sort(e, byFirstHash);
			return new State(e);
		}

		@Override
		public String toString() {
			int[][] a = new int[d.length][];
			for (int i = 0; i < d.length; i++) {
				a[i] = new int[d[i].size()];
				int k = 0;
				for (int j : d[i]) {
					a[i][k++] = j;
				}
				Arrays.sort(a[i]);
			}
			Arrays.sort(a, byFirst);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < a.length; i++) {
				if (i > 0) {
					sb.append(",");
				}
				sb.append("{");
				for (int j = 0; j < a[i].length; j++) {
					if (j > 0) {
						sb.append(",");
					}
					sb.append(a[i][j] + 1);
				}
				sb.append("}");
			}
			return sb.toString();
		}
	}

	Comparator<int[]> byFirst = new Comparator<int[]>() {

		@Override
		public int compare(int[] o1, int[] o2) {
			return o1[0] - o2[0];
		}
	};

	Comparator<HashSet<Integer>> byFirstHash = new Comparator<HashSet<Integer>>() {

		@Override
		public int compare(HashSet<Integer> o1, HashSet<Integer> o2) {
			int min1 = Integer.MAX_VALUE;
			for (int i : o1) {
				min1 = Math.min(min1, i);
			}
			int min2 = Integer.MAX_VALUE;
			for (int i : o2) {
				min2 = Math.min(min2, i);
			}
			return min1 - min2;
		}
	};
	HashSet<State> was;
	ArrayList<State> stack;

	void go(int x, State last) {
		stack.add(last);
		was.add(last);
		if (x == all) {
			print();
		}
		HashSet<Integer>[] d = last.d;
		for (int i = 0; i < d.length; i++) {
			for (int j : d[i]) {
				if (d[i].size() != 1) {
					State newState = last.getState(i, j, d.length);
					if (!was.contains(newState)) {
						go(x + 1, newState);
					}
				}
				for (int k = d.length - 1; k >= 0; k--) {
					if (k == i) {
						continue;
					}
					State newState = last.getState(i, j, k);
					if (!was.contains(newState)) {
						go(x + 1, newState);
					}
				}
			}
		}
		stack.remove(stack.size() - 1);
		was.remove(last);
	}

	private void print() {
		for (State state : stack) {
			out.println(state);
		}
		out.close();
		System.exit(0);
	}

	int all;
	int n;

	void solve() {
		n = nextInt();
		HashSet<Integer> w = new HashSet<Integer>();
		for (int i = 0; i < n; i++) {
			w.add(i);
		}
		State start = new State(new HashSet[] { w });
		was = new HashSet<I.State>();
		stack = new ArrayList<I.State>();
		int[][] dp = new int[n + 1][n + 1];
		dp[0][0] = 1;
		for (int i = 1; i < dp.length; i++) {
			dp[i][1] = 1;
			for (int j = 2; j <= i; j++) {
				dp[i][j] = dp[i - 1][j] * j + dp[i - 1][j - 1];
			}
		}
		all = -1;
		for (int i = 0; i <= n; i++) {
			all += dp[n][i];
		}
		go(0, start);
	}
}