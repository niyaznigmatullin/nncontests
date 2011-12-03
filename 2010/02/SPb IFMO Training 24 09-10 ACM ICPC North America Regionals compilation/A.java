import java.util.*;
import java.math.*;
import java.io.*;

public class A implements Runnable {
	public static void main(String[] args) {
		new Thread(new A()).start();
	}

	StringTokenizer st;
	PrintWriter out;
	BufferedReader br;
	boolean eof = false, in_out = false, std = false;

	String nextToken() {
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
		// long time = System.currentTimeMillis();
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			solve();
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(111);
		}
		// System.err.println(System.currentTimeMillis() - time);
	}

	void solve() {
		int q = 0;
		while (solve2(++q))
			;
	}

	HashSet<Integer>[] d;

	void go(int x) {
		if (d[x] != null)
			return;
		d[x] = new HashSet<Integer>();
		if ((x & (x - 1)) == 0) {
			d[x].add(a[Integer.numberOfTrailingZeros(x)]);
			return;
		}
		if (Integer.bitCount(x) == 4) {
			for (int i = 0; i < 4; i++) {
				if (((x >> i) & 1) == 0)
					continue;
				go(x ^ (1 << i));
				go(1 << i);
				for (int t1 : d[x ^ (1 << i)]) {
					for (int t2 : d[1 << i]) {
						d[x].add(t1 + t2);
						d[x].add(t1 - t2);
						d[x].add(t2 - t1);
						d[x].add(t1 * t2);
						if (t2 != 0 && t1 % t2 == 0) {
							d[x].add(t1 / t2);
						}
						if (t1 != 0 && t2 % t1 == 0) {
							d[x].add(t2 / t1);
						}
					}
				}
			}
			for (int i = 0; i < 4; i++) {
				if (((x >> i) & 1) == 0)
					continue;
				for (int j = i + 1; j < 4; j++) {
					if (((x >> j) & 1) == 0) {
						continue;
					}
					go(x ^ (1 << i) ^ (1 << j));
					go((1 << i) ^ (1 << j));
					for (int t1 : d[x ^ (1 << i) ^ (1 << j)]) {
						for (int t2 : d[(1 << i) ^ (1 << j)]) {
							d[x].add(t1 + t2);
							d[x].add(t1 - t2);
							d[x].add(t2 - t1);
							d[x].add(t1 * t2);
							if (t2 != 0 && t1 % t2 == 0) {
								d[x].add(t1 / t2);
							}
							if (t1 != 0 && t2 % t1 == 0) {
								d[x].add(t2 / t1);
							}
						}
					}
				}
			}
		} else if (Integer.bitCount(x) == 3) {
			for (int i = 0; i < 4; i++) {
				if (((x >> i) & 1) == 0)
					continue;
				go(x ^ (1 << i));
				go(1 << i);
				for (int t1 : d[x ^ (1 << i)]) {
					for (int t2 : d[1 << i]) {
						d[x].add(t1 + t2);
						d[x].add(t1 - t2);
						d[x].add(t2 - t1);
						d[x].add(t1 * t2);
						if (t2 != 0 && t1 % t2 == 0) {
							d[x].add(t1 / t2);
						}
						if (t1 != 0 && t2 % t1 == 0) {
							d[x].add(t2 / t1);
						}
					}
				}
			}
		} else if (Integer.bitCount(x) == 2) {
			for (int i = 0; i < 4; i++) {
				if (((x >> i) & 1) == 0)
					continue;
				go(x ^ (1 << i));
				go(1 << i);
				for (int t1 : d[x ^ (1 << i)]) {
					for (int t2 : d[1 << i]) {
						d[x].add(t1 + t2);
						d[x].add(t1 - t2);
						d[x].add(t2 - t1);
						d[x].add(t1 * t2);
						if (t2 != 0 && t1 % t2 == 0) {
							d[x].add(t1 / t2);
						}
						if (t1 != 0 && t2 % t1 == 0) {
							d[x].add(t2 / t1);
						}
					}
				}
			}
		}
	}

	int n;
	int[] a;

	boolean solve2(int test) {
		n = 4;
		a = new int[n];
		boolean zeroes = true;
		for (int i = 0; i < n; i++) {
			a[i] = nextInt();
			zeroes &= a[i] == 0;
		}
		if (zeroes)
			return false;
		d = new HashSet[1 << n];
		go(15);
		int[] b = new int[d[15].size()];
		int k = 0;
		for (int e : d[15]) {
			b[k++] = e;
		}
		Arrays.sort(b);
		int ans = 0, begin = Integer.MIN_VALUE;
		for (int i = 0; i < b.length;) {
			int j = i;
			while (j < b.length && j - i == b[j] - b[i]) {
				j++;
			}
			if (j - i >= ans) {
				ans = j - i;
				begin = i;
			}
			i = j;
		}
		out.print("Case " + test + ": ");
		out.println(b[begin] + " to " + b[begin + ans - 1]);
		return true;
	}
}