import java.io.*;
import java.util.*;

public class E implements Runnable {
	public static void main(String[] args) {
		new Thread(new E()).start();
	}

	BufferedReader br;
	PrintWriter out;
	StringTokenizer st;
	boolean eof;

	String nextToken() {
		while (st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (Exception e) {
				eof = true;
				return "0";
			}
		}
		return st.nextToken();
	}

	public void run() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			solve();
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
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

	class Man implements Comparable<Man> {
		int x, id;

		public Man(int x, int id) {
			super();
			this.x = x;
			this.id = id;
		}

		@Override
		public int compareTo(Man o) {
			if (x > o.x) {
				return -1;
			}
			if (x < o.x) {
				return 1;
			}
			return id - o.id;
		}
	}

	void solve() {
		int n = nextInt();
		Man[] men = new Man[n];
		for (int i = 0; i < n; i++) {
			men[i] = new Man(nextInt(), i);
		}
		Man[] copyMen = men.clone();
		Arrays.sort(men);
		TreeSet<Integer> m = new TreeSet<Integer>();
		m.add(men[0].id);
		int[] eqLeft = new int[n];
		int[] eqRight = new int[n];
		eqLeft[men[0].id] = eqRight[men[0].id] = 1;
		long ans = 0;
		for (int i = 1; i < n; i++) {
			Man s = men[i];
			int left;
			if (m.lower(s.id) != null) {
				left = m.lower(s.id);
			} else {
				left = m.last();
			}
			int right;
			if (m.higher(s.id) != null) {
				right = m.higher(s.id);
			} else {
				right = m.first();
			}
			if (left == right) {
				ans++;
				m.add(s.id);
				eqLeft[s.id] = eqRight[s.id] = 1;
				// System.err.println(s.x + " " + ans);
				continue;
			}
			ans += eqLeft[left];
			if (copyMen[left].x == copyMen[s.id].x) {
				eqLeft[s.id] = eqLeft[left] + 1;
			} else {
				eqLeft[s.id] = 1;
			}
			ans += eqRight[right];
			if (copyMen[right].x == copyMen[s.id].x) {
				eqRight[s.id] = eqRight[right] + 1;
			} else {
				eqRight[s.id] = 1;
			}
			m.add(s.id);
			// System.err.println(s.x + " " + ans);
		}
		out.println(ans);
	}
}
