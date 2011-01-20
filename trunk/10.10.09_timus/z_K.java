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

	private static double EPS = 5e-6;
	String INFILE = "cipher.in", OUTFILE = "cipher.out";

	boolean[][] G;
	boolean[] was;
	int[] p, c, r;
	int n;

	private boolean dfs(int x) {
		if (was[x]) {
			return false;
		} else {
			was[x] = true;
			for (int i = 0; i < n; i++)
				if (G[x][i]) {
					if (r[i] != -1) {
						
					} else {
						p[i] = x;
						return true;
					}
				}
			return false;

		}
	}

	private void solve() {
		n = nextInt();
		String[] name = new String[n];
		c = new int[n];
		int[] rate = new int[n];
		for (int i = 0; i < n; i++) {
			name[i] = nextString();
			String cando = nextString();
			if (cando.equals("anything")) {
				c[i] = 3;
			} else if (cando.equals("statements")) {
				c[i] = 2;
			} else
				c[i] = 1;
			rate[i] = nextInt();
		}
		G = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				if (i != j && c[i] > 1 && c[j] % 2 == 1
						&& Math.abs(rate[i] - rate[j]) == 2) {
					G[i][j] = true;
				}
		}
		was = new boolean[n];
		p = new int[n];
		Arrays.fill(p, -1);
		for (int i = 0; i < n; i++)
			if (c[i] > 1) {
				Arrays.fill(was, false);
				if (p[i] != -1) {
					boolean ttt = dfs(p[i]);
					if (!ttt)
						continue;
					Arrays.fill(was, false);
					was[i] = true;
					p[i] = -1;
					for (int j = 0; j < n; j++)
						if (i != j && c[j] % 2 == 1 && G[i][j] && dfs(p[j])) {
							p[j] = i;
							break;
						}
				} else {
					dfs(i);
				}
			}
		ArrayList<Ans> ans = new ArrayList<Ans>();
		for (int i = 0; i < n; i++)
			if (p[i] != -1) {
				ans.add(new Ans(name[p[i]], name[i]));
			}
		out.println(ans.size());
		for (int i = 0; i < ans.size(); i++) {
			out.println(ans.get(i));
		}
	}

	class Ans {
		String s1, s2;

		Ans(String s3, String s4) {
			s1 = s3;
			s2 = s4;
		}

		@Override
		public String toString() {
			return s1 + " " + s2;
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