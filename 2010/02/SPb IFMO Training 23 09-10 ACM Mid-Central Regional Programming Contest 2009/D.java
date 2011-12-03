import java.util.*;
import java.math.*;
import java.io.*;

public class D implements Runnable {
	public static void main(String[] args) {
		new Thread(new D()).start();
	}

	StringTokenizer st;
	PrintWriter out;
	BufferedReader br;
	boolean eof = false, in_out = false, std = false;

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

	String nextLine() {
		String ret = "";
		try {
			ret = br.readLine();
		} catch (Exception e) {
			ret = "";
		}
		if (ret == null) {
			eof = true;
			return "$";
		}
		return ret;
	}

	String nextString() {
		return nextToken();
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

	public void run() {
		// long time = System.currentTimeMillis();
		try {
			br = new BufferedReader(new FileReader(new File("vienna.in")));
			out = new PrintWriter("vienna.out");
			solve();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(111);
		}
		// System.err.println(System.currentTimeMillis() - time);
		out.close();
	}

	void solve() {
		while (solve2())
			;
	}

	char[][] s;

	class E {
		int who, ans;
		char[] what;

		public E(int who, char[] what, int ans) {
			this.who = who - 1;
			this.ans = ans;
			this.what = what;
		}

		@Override
		public String toString() {
			return "E [ans=" + ans + ", what=" + Arrays.toString(what)
					+ ", who=" + who + "]";
		}

	}

	E[] inter;
	int[] has;
	boolean[] was;
	char[][] force;
	final int[] can = { 5, 5, 5, 3 };
	int got;

	void go(int x) {
		if (x == 18) {
			check();
			return;
		}
		if (was[x]) {
			go(x + 1);
			return;
		}
		for (int i = 0; i < 4; i++) {
			if (has[i] == can[i]) {
				continue;
			}
			force[i][has[i]++] = (char) (x + 'A');
			go(x + 1);
			has[i]--;
		}
	}

	void check() {
		if (Arrays.equals(s[3], force[3]))
			return;
		for (int i = 0; i < inter.length; i++) {
			int k = 0;
			for (char c1 : inter[i].what) {
				for (char c2 : force[inter[i].who]) {
					if (c1 == c2)
						k++;
				}
			}
			if (k != inter[i].ans) {
				got = Math.max(got, i);
				return;
			}
		}
		got = Math.max(got, inter.length);
	}

	boolean solve2() {
		int n = nextInt();
		if (n == 0) {
			return false;
		}
		s = new char[4][];
		for (int i = 0; i < 4; i++) {
			s[i] = nextToken().toCharArray();
		}
		inter = new E[n];
		for (int i = 0; i < n; i++) {
			inter[i] = new E(nextInt(), nextToken().toCharArray(), nextInt());
		}
		was = new boolean[18];
		has = new int[4];
		force = new char[4][];
		for (int i = 0; i < 3; i++) {
			force[i] = new char[5];
		}
		force[3] = new char[3];
		int ans = Integer.MAX_VALUE;
		for (int player = 0; player < 3; player++) {
			Arrays.fill(was, false);
			for (int i = 0; i < 5; i++) {
				was[s[player][i] - 'A'] = true;
				force[player][i] = s[player][i];
			}
			Arrays.fill(has, 0);
			has[player] = 5;
			got = 0;
			go(0);
			ans = Math.min(ans, got);
		}
		if (ans == inter.length) {
			out.println("?");
		} else {
			out.println(ans + 1);
		}
		return true;
	}

}