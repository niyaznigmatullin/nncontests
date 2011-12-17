import static java.lang.Math.max;

import java.io.*;
import java.util.*;

public class J {

	static void solve() throws IOException {
		int tc = nextInt();
		while (tc-- > 0) {
			int n = nextInt();
			int addX = 0;
			int addY = 0;
			int k = (n - 1) / 3;
			int answer = 2 * k + 1;
			if (n % 3 == 0) {
				answer++;
				addX = 2;
				addY = 1;
			}
			out.println(answer);
			boolean first = true;
			if (n % 3 == 0) {
				out.print("1 1");
				first = false;
			}
			for (int i = 0; i <= k; i++) {
				if (!first) {
					out.print("  ");
				}
				first = false;
				out.print((k + 1 + i + addX) + " " + (2 * i + 1 + addY));
			}
			for (int i = 1; i <= k; i++) {
				if (!first) {
					out.print("  ");
				}
				first = false;
				out.print((2 * k + 1 + i + addX) + " " + (2 * i + addY));
			}
			out.println();
		}
		// for (int n = 2; n <= 100; n++) {
		// System.err.println(n + " " + get(n));
		// }
	}

	static int get(int n) {
		beatenY = new boolean[n + 1];
		beatenXminusY = new boolean[n + 1];
		bestAnswer = 0;
		go(n, 0);
		return bestAnswer;
	}

	static int[] x;
	static int[] y;

	static boolean[] beatenY;
	static boolean[] beatenXminusY;

	static int bestAnswer;

	static void go(int row, int curAnswer) {
		if (curAnswer + row <= bestAnswer) {
			return;
		}
		if (row == 0) {
			bestAnswer = curAnswer;
			return;
		}
		for (int col = 1; col <= row; col++) {
			if (beatenXminusY[row - col] || beatenY[col]) {
				continue;
			}
			beatenXminusY[row - col] = true;
			beatenY[col] = true;
			go(row - 1, curAnswer + 1);
			beatenXminusY[row - col] = false;
			beatenY[col] = false;
		}
		go(row - 1, curAnswer);
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("triangular.in"));
		out = new PrintWriter(System.out);
		solve();
		out.close();
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static String nextToken() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null) {
				return null;
			}
			st = new StringTokenizer(line);
		}
		return st.nextToken();
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
}