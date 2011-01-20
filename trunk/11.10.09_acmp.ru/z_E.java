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
	boolean eof = false, in_out = false, std = false;

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

	final double EPS = 5e-6;
	String INFILE = "cipher.in", OUTFILE = "cipher.out";

	private void solve() {
		int n = nextInt(), k = nextInt();
		long m = nextLong();
		long[][] dp = new long[k + 1][n + 1];
		dp[0][n] = 1;
		for (int i = 1; i <= k; i++)
			for (int j = 0; j < n; j++) {
				for (int t = j + 1; t <= n; t++)
					dp[i][j] += dp[i - 1][t];
			}
		StringBuilder sb = new StringBuilder();
		int cur = 0;
		for (int i = k; i >= 1; i--) {			
			while (dp[i][cur] < m) {
				m -= dp[i][cur];
				cur++;
			}
			sb.append((char)(cur + 'a'));
			cur++;
		}
		out.println(sb);
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
			solve();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(111);
		}
		// System.err.println(System.currentTimeMillis() - time);
		if (std)
			out.flush();
		else
			out.close();
	}
}