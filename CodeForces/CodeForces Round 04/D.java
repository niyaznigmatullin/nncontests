import java.io.*;
import java.util.*;

public class D implements Runnable {
	public static void main(String[] args) {
		new Thread(new D()).start();
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

	void genTest() {
		try {
			out = new PrintWriter("d.in");
		} catch (Exception e) {

		}
		Random rand = new Random();
		int n = rand.nextInt(5000);
		out.print(n + " ");
		out.println(rand.nextInt(1000000) + 1 + " "
				+ (rand.nextInt(1000000) + 1));
		for (int i = 0; i < n; i++) {
			out.println(rand.nextInt(1000000) + 1 + " "
					+ (rand.nextInt(1000000) + 1));
		}
		out.close();
	}

	public void run() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			// genTest();
			// br = new BufferedReader(new FileReader("d.in"));
			// out = new PrintWriter("d.out");
			solve();
			out.flush();
		} catch (Throwable e) {
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

	int[] dp, a, b;

	int dfs(int x) {
		if (dp[x] != -1) {
			return dp[x];
		}
		int ret = 1;
		for (int i = 0; i < n; i++) {
			if (a[x] < a[i] && b[x] < b[i]) {
				ret = Math.max(ret, dfs(i) + 1);
			}
		}
		return dp[x] = ret;
	}

	ArrayList<Integer> restored;

	void restore(int x) {
		restored.add(x + 1);
		if (dp[x] == 1) {
			return;
		}
		for (int i = 0; i < n; i++) {
			if (a[x] < a[i] && b[x] < b[i]) {
				if (dp[i] + 1 == dp[x]) {
					restore(i);
					return;
				}
			}
		}
		System.exit(1);
		// throw new AssertionError();
	}

	int n;

	void solve() {
		n = nextInt();
		int w = nextInt();
		int h = nextInt();
		a = new int[n];
		b = new int[n];
		for (int i = 0; i < n; ++i) {
			a[i] = nextInt();
			b[i] = nextInt();
		}
		dp = new int[n];
		Arrays.fill(dp, -1);
		int ans = 0, ansI = -1;
		for (int i = 0; i < n; ++i) {
			int g = dfs(i);
			if (w < a[i] && h < b[i]) {
				if (g > ans) {
					ans = g;
					ansI = i;
				}
			}
		}
		if (ansI == -1) {
			out.println(0);
			return;
		}
		restored = new ArrayList<Integer>();
		// for (int x = ansI;;) {
		// restored.add(x + 1);
		// if (dp[x] == 1) {
		// break;
		// }
		// for (int i = 0; i < n; i++) {
		// if (a[x] < a[i] && b[x] < b[i]) {
		// if (dp[i] + 1 == dp[x]) {
		// x = i;
		// break;
		// }
		// }
		// }
		// }
		restore(ansI);
		out.println(ans);
		if (restored.size() != ans) {
			System.exit(1);
		}
		for (int i = 1; i < restored.size(); i++) {
			if (a[restored.get(i) - 1] <= a[restored.get(i - 1) - 1]
					|| b[restored.get(i) - 1] <= b[restored.get(i - 1) - 1]) {
				System.err.println(ans);
				System.err.println(restored);
				int x = restored.get(i - 1) - 1;
				int y = restored.get(i) - 1;
				System.err.println(a[x] + " " + b[x] + " " + a[y] + " " + b[y]);
				System.exit(1);
			}
		}
		for (int i : restored) {
			out.print(i + " ");
		}
	}
}
