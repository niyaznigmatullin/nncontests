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

	int m, n;
	int MOD;
	
	private void solve() {
		m = nextInt();
		n = nextInt();
		int xx = nextInt() - 1;
		int yy = nextInt() - 1;
		int p = nextInt();
		MOD = p;
		int[][] a = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				a[i][j] = nextInt();
			}
		int[] c = a[xx];
		int[] d = new int[n];
		int[][] b = new int[n][n];
		for (int t = 1; t < m; t++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					b[i][j] = nextInt();
				}
			}
			for (int i = 0; i < n; i++) {
				d[i] = 0;
				for (int j = 0; j < n; j++) {
					d[i] += c[j] * b[j][i];
					d[i] %= MOD;
				}
			}
			{
				int[] temp = c;
				c = d;
				d = temp;
			}
		}
		out.println(c[yy]);
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