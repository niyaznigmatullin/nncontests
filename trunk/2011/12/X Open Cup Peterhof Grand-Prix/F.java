import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.util.Arrays.fill;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class F {
	
	static BufferedReader in;
	static PrintWriter out;
	static StringTokenizer tok;
	
	static class Backtrack {
		final int i;
		final Backtrack prev;
		
		Backtrack(int i, Backtrack prev) {
			this.i = i;
			this.prev = prev;
		}
	}
	
	static void solve() throws Exception {
		while (true) {
			int n = nextInt();
			if (n == 0) {
				break;
			}
			int a[] = new int[2 * n];
			for (int i = 0; i < a.length; i++) {
				a[i] = nextInt() - 1;
			}
			int b[] = new int[2 * n];
			int bpos1[] = new int[n];
			int bpos2[] = new int[n];
			fill(bpos1, -1);
			fill(bpos2, -1);
			for (int i = 0; i < b.length; i++) {
				b[i] = nextInt() - 1;
				if (bpos1[b[i]] < 0) {
					bpos1[b[i]] = i;
				} else {
					bpos2[b[i]] = i;
				}
			}
			int tree[] = new int[2 * n + 1];
			Backtrack bt[] = new Backtrack[2 * n + 1];
			for (int i = 0; i < a.length; i++) {
				int num = a[i];
				for (int j: new int[] {bpos2[num], bpos1[num]}) {
					int pos = j;
					int ans = 0;
					Backtrack ansBt = null;
					while (pos >= 0) {
						if (ans < tree[pos]) {
							ans = tree[pos];
							ansBt = bt[pos];
						}
						pos &= pos + 1;
						--pos;
					}
					pos = j + 1;
					while (pos <= 2 * n) {
						if (tree[pos] < ans + 1) {
							tree[pos] = ans + 1;
							bt[pos] = new Backtrack(num, ansBt);
						}
						pos |= pos + 1;
					}
				}
			}
			int pos = 2 * n;
			int ans = 0;
			Backtrack ansBt = null;
			while (pos >= 0) {
				if (ans < tree[pos]) {
					ans = tree[pos];
					ansBt = bt[pos];
				}
				pos &= pos + 1;
				--pos;
			}
			out.println(ans);
			List<Integer> ansList = new ArrayList<Integer>();
			while (ansBt != null) {
				ansList.add(ansBt.i);
				ansBt = ansBt.prev;
			}
			Collections.reverse(ansList);
			for (int i: ansList) {
				out.print(i + 1 + " ");
			}
			out.println();
		}
	}

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new InputStreamReader(new FileInputStream("lcs.in")));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("lcs.out"))));
		solve();
		in.close();
		out.close();
	}
	
	static String next() throws IOException {
		while (tok == null || !tok.hasMoreTokens()) {
			tok = new StringTokenizer(in.readLine());
		}
		return tok.nextToken();
	}
	
	static char nextChar() throws IOException {
		String token = next();
		if (token.length() != 1) {
			throw new IllegalArgumentException("String \"" + token + "\" is not a single character");
		}
		return token.charAt(0);
	}
	
	static int nextInt() throws IOException {
		return parseInt(next());
	}
	
	static long nextLong() throws IOException {
		return parseLong(next());
	}
	
	static double nextDouble() throws IOException {
		return parseDouble(next());
	}
	
	static BigInteger nextBigInt() throws IOException {
		return new BigInteger(next());
	}
}