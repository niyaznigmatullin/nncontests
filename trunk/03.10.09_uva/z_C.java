import java.util.*;
import java.math.*;
import java.io.*;

public class Main implements Runnable {
	public static void main(String[] args) {
		new Thread(new Main()).start();
	}

	StringTokenizer st;
	PrintWriter out;
	BufferedReader br;
	boolean eof = false, in_out = false, std = true;

	private String nextToken() {
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

	private String nextLine() {
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

	private String nextString() {
		return nextToken();
	}

	private int nextInt() {
		return Integer.parseInt(nextToken());
	}

	private long nextLong() {
		return Long.parseLong(nextToken());
	}

	private double nextDouble() {
		return Double.parseDouble(nextToken());
	}

	private BigInteger nextBigInteger() {
		return new BigInteger(nextToken());
	}

	private static double EPS = 1e-12;
	String INFILE = "strings.in", OUTFILE = "strings.out";

	private void solve() {
		int T = nextInt();
		for (int www = 1; www <= T; www++) {
			int n = nextInt();
			int[] a = new int[n];
			for (int i = 0; i < n; i++) a[i] = nextInt();			
			int[][] dp = new int[1 << n][8];
			for (int i = 0; i < 1 << n; i++) Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
			dp[0][0] = 0;
			for (int i = 0; i < 1 << n; i++) {
				for (int j = 0; j <= 7; j++) {
					for (int k = 0; k < n; k++) if (((i >> k) & 1) == 0) {
						int p = a[k] - j;
						if (p <= 0) p = 1;
						if (dp[i ^ (1 << k)][p - 1] > dp[i][j] + p) {
							dp[i ^ (1 << k)][p - 1] = dp[i][j] + p;
//							System.err.println((i ^ (1 << k)) + " " + (p - 1));
						}
					}
				}
			}
			int ans = Integer.MAX_VALUE / 2;
			for (int i = 0; i <= 7; i++) {
				if (ans > dp[(1 << n) - 1][i]) ans = dp[(1 << n) - 1][i];
			}
			out.println(ans);
		}
	}

	public void run() {
		// long time = System.currentTimeMillis();
		try {
			br = std ? new BufferedReader(new InputStreamReader(System.in))
					: new BufferedReader(new FileReader(new File(
							(in_out) ? INFILE : "input.txt")));
			out = std ? new PrintWriter(new OutputStreamWriter(System.out))
					: new PrintWriter(new File((in_out) ? OUTFILE
							: "output.txt"));
		} catch (Exception e) {
			System.exit(111);
		}
		solve();
		// System.err.println(System.currentTimeMillis() - time);
		if (std)
			out.flush();
		else
			out.close();
	}
}