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

	private void dfs(int x) {
		was[x] = true;
		for (int e : G[x])
			if (p[x] != e) {
				p[e] = x;
				dfs(e);
				for (int i = 0; i < n; i++)
					if (go[e][i] != 0)
						go[x][i] = e;
				go[x][e] = e;
			}
	}

	private BigInteger calc(int x) {
		if (was[x]) {
			return ans[x];
		}
		was[x] = true;
		BigInteger noed = BigInteger.ONE;
		for (int e : G[x])
			if (p[x] != e) {
				noed = noed.multiply(calc(e));
			}
		ans[x] = noed;
		BigInteger[] back = new BigInteger[n];
		Arrays.fill(back, BigInteger.ZERO);
		for (int i = 0; i < n; i++) {
			if (go[x][i] != 0) {
				int j = x;
				BigInteger cur = BigInteger.ONE;
				while (true) {
					for (int e : G[j]) {
						if (e != p[j] && e != go[j][i])
							cur = cur.multiply(calc(e));
					}
					if (j == i)
						break;
					j = go[j][i];
				}
				back[i] = cur;
				if (go[x][i] != i)
					ans[x] = ans[x].add(back[i]);
			}
		}
		for (int i = 0; i < n; i++) if (go[x][i] != 0) {
			for (int j = i + 1; j < n; j++) if (go[x][j] != 0 && go[x][j] != go[x][i]) {
				ans[x] = ans[x].add(back[i].multiply(back[j]).divide(noed));
			}
		}
		return ans[x];
	}

	int n;
	boolean[] was;
	BigInteger[] ans;
	int[] p;
	int[][] go;
	ArrayList<Integer>[] G;

	private void solve() {
		n = nextInt();
		G = new ArrayList[n];
		for (int i = 0; i < n; i++)
			G[i] = new ArrayList<Integer>();
		for (int i = 0; i < n - 1; i++) {
			int x = nextInt() - 1, y = nextInt() - 1;
			G[x].add(y);
			G[y].add(x);
		}
		p = new int[n];
		go = new int[n][n];
		ans = new BigInteger[n];
		was = new boolean[n];
		dfs(0);		
		Arrays.fill(was, false);
		calc(0);
		out.println(ans[0]);
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