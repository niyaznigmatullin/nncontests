import java.util.*;
import java.math.*;
import java.io.*;
import static java.math.BigInteger.*;

public class Main implements Runnable {
	public static void main(String[] args) {
		new Thread(new Main()).start();
	}

	StringTokenizer st;
	PrintWriter out;
	BufferedReader br;

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

	String INFILE = "choko.in", OUTFILE = "choko.out";
	boolean eof = false, in_out = false, std = false;

	private void solve() {
		int n = nextInt();
		double[] a = new double[n];
		for (int i = 0; i < n; i++)
			a[i] = nextDouble();
		double[][] dp = new double[2][n];
		dp[0][0] = a[0];
		dp[1][0] = (1 - a[0]);
		for (int i = 1; i < n; i++) {
			dp[0][i] = dp[1][i - 1] * (1 - a[i]) + dp[0][i - 1] * a[i];
			dp[1][i] = dp[0][i - 1] * (1 - a[i]) + dp[1][i - 1] * a[i];
		}
		out.println(dp[0][n - 1]);
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