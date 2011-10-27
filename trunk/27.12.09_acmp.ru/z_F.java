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
	boolean eof = false, in_out = false, std = false;

	String nextToken() {
		while (st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(br.readLine(), " :");
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
		try {
			br = new BufferedReader(new FileReader(new File("input.txt")));
			out = new PrintWriter(new File("output.txt"));
			solve();
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(456);
		}
	}

	void solve() {
		int n = nextInt();
		int m = nextInt();
		int[][] a = new int[n][m];
		int col = -1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				a[i][j] = nextInt();
				if (a[i][j] == 1) {
					col = j;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (a[j][col] == i * m + 1) {
					int[] temp = a[i];
					a[i] = a[j];
					a[j] = temp;
					sb.append("R " + (i + 1) + " " + (j + 1) + "\n");
					ans++;
					break;
				}
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = i + 1; j < m; j++) {
				if (a[0][j] == i + 1) {
					int temp = a[0][j];
					a[0][j] = a[0][i];
					a[0][i] = temp;
					sb.append("C " + (i + 1) + " " + (j + 1) + "\n");
					ans++;
					break;
				}
			}
		}
		out.println(ans);
		out.print(sb);
	}
}