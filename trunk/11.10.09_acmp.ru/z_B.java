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

	int n;
	boolean[] was;
	int[] color, leave;	
	boolean[][] G, r;

	int kol = 0, col = 0;
	void dfs(int x) {
		was[x] = true;
		for (int i = 0; i < n; i++) if (!was[i] && G[x][i]){
			dfs(i);
		}
		leave[kol++] = x;
	}
	private void dfs2(int x, int c) {
		was[x] = true;
		color[x] = c;
		for (int i = 0; i < n; i++) if (!was[i] && G[i][x]) {
			dfs2(i, c);
		}
	}
	private void solve() {
		n = nextInt();
		G = new boolean[2 * n][2 * n];
		r = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			String s = nextLine();
			for (int j = 0; j < n; j++) {
				r[i][j] = s.charAt(j) == '1';
				if (r[i][j]) {
					G[i][j + n] = true;
				} else
					G[j + n][i] = true;
			}
		}
		n <<= 1;
		was = new boolean[n];
		leave = new int[n];
		for (int i = 0; i < n; i++) if (!was[i]) {
			dfs(i);
		}
		Arrays.fill(was, false);
		color = new int[n];		
		for (int i = kol - 1; i >= 0; i--) if (!was[leave[i]]) {
			dfs2(leave[i], col++);
		}
		boolean ok = true;		
		loop : for (int i = 0; i < n / 2; i++)
			for (int j = 0; j < n / 2; j++) if (r[i][j] !=  (color[i] <= color[j + n / 2])){
				ok = false;
				break loop;
			}
		
		if (ok) {
			out.println("YES");
			for (int i = 0; i < n / 2; i++) {
				out.print(color[i] + " ");
			}
			out.println();
			for (int i = n / 2; i < n; i++) {
				out.print(color[i] + " ");
			}
		} else {
			out.println("NO");
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